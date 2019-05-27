package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import sistema.Contacto;
import sistema.Login;
import sistema.Muro;
import sistema.Registrar;
import sistema.Union;
import sistema.Usuario;

public class ServidorTCP {
	public static final int OBTENER_ACCESO = 1;
	public static final int REGISTRARSE = 2;
	public static final int OBTENER_RESULTADOS = 3;
	public static final int VER_PERFIL = 4;
	public static final int VER_PERFIL_PROPIO = 5;
	public static final int VER_NOTIFICACIONES = 6;
	
	
	public static final int SERVER_PORT = 9000;
	
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	private Usuario nuevaCuenta;
	
	public ServidorTCP(Socket s) {
		this.socket = s;
	}
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		Socket s;
		
		while(true) {
			s = serverSocket.accept();
			new ServidorTCP(s).run();
		}
	}
	
	public void run() throws Exception {
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			int codigoServidor = ois.readInt();
			switch(codigoServidor) {
				case OBTENER_ACCESO:
					_obtenerAcceso(ois, oos);
					break;
				case REGISTRARSE:
					_registrarse(ois, oos);
					break;
				case OBTENER_RESULTADOS:
					_obtenerResultados(ois, oos);
					break;
				case VER_PERFIL:
					_verPerfil(ois, oos);
					break;
				case VER_PERFIL_PROPIO:
					_verPerfilPropio(ois, oos);
					break;
				case VER_NOTIFICACIONES:
					_verNotificaciones(ois, oos);
					break;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			if(oos != null) oos.close();
			if(ois != null) ois.close();
			if(socket != null) socket.close();
		}
	} 
	
	private void _obtenerAcceso(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			String correo = (String)ois.readObject();
			String password = (String)ois.readObject();
			Usuario usuario = Login.ingresar(correo, password);
			if(usuario != null) {
				oos.writeObject("Sesion iniciada");
				oos.writeObject(usuario);
			} else {
				oos.writeObject("Datos incorrectos");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	private void _registrarse(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			Usuario nuevaCuenta = (Usuario)ois.readObject();
			
			String codigo = (String)ois.readObject();
			
			boolean cuentaCreada = new Registrar().createAccount(nuevaCuenta, codigo);
			
			oos.writeBoolean(cuentaCreada);
			
			String correo = nuevaCuenta.getCor_usu();
			String password = nuevaCuenta.getPas_usu();
			
			Usuario iniciarSesion = Login.ingresar(correo, password);
			
			oos.writeObject(iniciarSesion);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	private void _obtenerResultados(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			Usuario usuario = (Usuario)ois.readObject();
			List<Usuario> usuarios = Muro.filtrarCuentas(usuario);
			oos.writeObject(usuarios);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void _verPerfil(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			Usuario perfil = (Usuario)ois.readObject();
			oos.writeObject(perfil.getNom_usu());
			oos.writeObject(perfil.getDes_usu());
			oos.writeObject(perfil.getFac_usu());
			oos.writeObject(perfil.getGen_usu());
			oos.writeObject(perfil.getIns_usu());
			oos.writeInt(perfil.getId());
			oos.writeObject(perfil.getCon_usu());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void _verPerfilPropio(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			Usuario perfil = (Usuario)ois.readObject();
			oos.writeObject(perfil.getCor_usu());
			oos.writeObject(perfil.getPas_usu());
			oos.writeInt(perfil.getTip_usu());
			oos.writeObject(perfil.getNom_usu());
			oos.writeObject(perfil.getGen_usu());
			oos.writeObject(perfil.getIns_usu());
			oos.writeObject(perfil.getFac_usu());
			oos.writeObject(perfil.getDes_usu());
			oos.writeBoolean(perfil.isDis_usu());
			oos.writeObject(perfil.getCon_usu());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void _verNotificaciones(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			Usuario perfil = (Usuario)ois.readObject();
			List<Usuario> contactos = Contacto.listarRemitentes(perfil.getId());
			oos.writeObject(contactos);
			List<Usuario> uniones = Union.listarRemitentes(perfil.getId());
			oos.writeObject(uniones);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}




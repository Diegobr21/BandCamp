package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import sistema.Login;
import sistema.Muro;
import sistema.Usuario;

public class ServidorTCP {
	public static final int OBTENER_ACCESO = 1;
	public static final int REGISTRARSE = 2;
	public static final int OBTENER_RESULTADOS = 3;
	public static final int VER_PERFIL = 4;
	
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
			String correo = (String)ois.readObject();
			nuevaCuenta.setCor_usu(correo);
			int tipo = ois.readInt();
			nuevaCuenta.setTip_usu(tipo);
			String nombre = (String)ois.readObject();
			nuevaCuenta.setNom_usu(nombre);
			String genero = (String)ois.readObject();
			nuevaCuenta.setGen_usu(genero);
			String instrumento = (String)ois.readObject();
			nuevaCuenta.setIns_usu(instrumento);
			String facultad = (String)ois.readObject();
			nuevaCuenta.setFac_usu(facultad);
			oos.writeObject(nuevaCuenta);
			String password = (String)ois.readObject();
			nuevaCuenta.setPas_usu(password);
			nuevaCuenta.setDes_usu("Hola");
			nuevaCuenta.setDis_usu(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void _obtenerResultados(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			Usuario usuario = (Usuario)ois.readObject();
			List<Usuario> usuarios = Muro.filtrarCuentas(usuario);
			for(int i = 0; i < usuarios.size(); i++) {
				oos.writeObject(usuarios.get(i));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}



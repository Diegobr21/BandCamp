package sistema;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

public class Server {
	
	public static final int OBTENER_ACCESO = 1;
	public static final int OBTENER_RESULTADOS = 2;
	public static final int VER_PERFIL = 3;
	public static final int AGREGAR_PERFIL = 4;
	
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public Server(Socket socket) {
		 this.socket = socket;
	}
	
	public static void main(String[] args) throws Exception {
		
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		Socket s = null;
		ServerSocket ss = new ServerSocket(5431);
		
		while(true) {
			
			try {
				
				s = ss.accept();
				
				System.out.println("Se conectaron desde la IP: " + s.getInetAddress());
				
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
				String nom = (String)ois.readObject();
				String saludo = "Hola Mundo ("+nom+") ";
				
				oos.writeObject(saludo);
				
				System.out.println("Saludo enviado...");
				
			} catch(Exception ex) {
				
				ex.printStackTrace();
				
			}
			
		}
	}
}

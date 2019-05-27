package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sistema.Credenciales;

public class Cliente {

	public static void main(String[] args) throws Exception {
		Socket s = new Socket(Credenciales.SERVER_IP, Credenciales.SERVER_PORT);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
		oos.writeInt(1);
		oos.writeObject("DanielSalfl12@gmail.com");
		oos.writeObject("@vion123");
		
		String respuesta = (String)ois.readObject();
		System.out.println(respuesta);
		
		oos.close();
		ois.close();
		s.close();
		
	}
	
}

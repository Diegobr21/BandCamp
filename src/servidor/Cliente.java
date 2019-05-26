package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost",8888);
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

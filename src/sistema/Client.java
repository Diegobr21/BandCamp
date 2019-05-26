package sistema;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 

public class Client {
	
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket s = null;
		
		try {
			s = new Socket("localhost",5431);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
			oos.writeObject("Daniel");
			
			String ret = (String)ois.readObject();
			
			System.out.println(ret);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}
}

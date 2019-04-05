package sistema;

import java.util.Scanner;

public class Login {
	
	//Metodo "ingresar", simplemente le pide al usuario que ingrese su correo electronico y contraseña, despues se verifica si estan correctas.
	public Usuario ingresar() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Ingresa tu correo electronico: ");
		String correo = scanner.nextLine();
		
		System.out.println("Ingresa tu contraseña: ");
		String contraseña = scanner.nextLine();
		
		//Validacion en base de datos
		// aquí se harán los queries para comprobar que el correo y la contraseña coinciden
//		if (cuenta.getCor_usu().equals(correo) && cuenta.getPas_usu().equals(contraseña)) {
//			System.out.println("Correo correcto y contraseña correcta");
//		}
		
		scanner.close();
		
		return null;
	}
}
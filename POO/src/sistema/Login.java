package sistema;

import java.util.Scanner;

public class Login extends Registrar {
	
	//Metodo "ingresar", simplemente le pide al usuario que ingrese su correo electronico y contraseña, despues se verifica si estan correctas.
	public void ingresar(Usuario cuenta) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa tu correo electronico: ");
		String correo = scanner.nextLine();
		System.out.println("Ingresa tu contraseña: ");
		String contraseña = scanner.nextLine();
		//Validacion en base de datos
		if (cuenta.getCor_usu().equals(correo) && cuenta.getPas_usu().equals(contraseña)) {
			System.out.println("Correo correcto y contraseña correcta");
		}
	}
}

package sistema;

import java.util.Scanner;

import javax.swing.JOptionPane;

import validaciones.MD5;
import validaciones.ValidPassword;

public class Registrar {
	//Metodo "capturar" permite al usuario registrar una nueva cuenta. Por cambiar: TODO.
	public Usuario capturar() {
		int id = 1;
		int tipo = 0;
		String tipo_usuario = "";
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Correo electrónico: ");
		String correo = scanner.nextLine();
		
		String password;
		while (true) {
			System.out.print("\nNueva contraseña: ");
			password = scanner.nextLine();
			if (!ValidPassword.isValidPassword(password)) {
				continue;
			}
			
			System.out.print("Verificar contraseña: ");
			String duplicate = scanner.nextLine();
			if (password.equals(duplicate)) {
				
				// se obtiene el hash de la contraseña
				password = MD5.hashPassword(password);
				if (password != null) {
					break;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		boolean bandera = true;
		//Ciclo while, permite al usuario reingresar los datos en caso de que no se ingrese un valor aceptable.
		while (bandera) {
			System.out.print("¿Artista (1) o Banda (2)?: ");
			tipo = Integer.parseInt(scanner.nextLine());
			
			//Switch, basicamente convierte el valor entero que ingreso el usuario para que se guarde como un string en otra variable.
			switch (tipo) { 
				case 1:
					tipo_usuario = "Artista";
					bandera = false;
					break;
				case 2:
					tipo_usuario = "Banda";
					bandera = false;
					break;
				default:
					System.out.println("Opción incorrecta.");
					break;
			}
		}
		
		System.out.print("Nombre de usuario: ");
		String nombre = scanner.nextLine();
		
		System.out.print("¿Qué género interpretas?: ");
		String genero = scanner.nextLine();
		
		System.out.print("¿Qué instrumento tocas?: ");
		String instrumento = scanner.nextLine();
		
		System.out.print("¿A cuál facultad perteneces?: ");
		String facultad = scanner.nextLine().toUpperCase();
		
		//Se declara e instancia un nuevo objeto de tipo Usuario, despues se utilizan los metodos de acceso
		// para obtener los datos e imprimirlos.
		// la variable password almacena el hash de la contraseña del usuario.
		return new Usuario(id, correo, password, tipo, nombre, genero, instrumento, facultad);
	}
	
	//Metodo "imprimir", hace lo que tiene que hacer, imprimir.
	public void imprimir(Usuario cuenta) {
		System.out.println(cuenta.getId());
		System.out.println(cuenta.getCor_usu());
		System.out.println(cuenta.getPas_usu());
		System.out.println(cuenta.getTip_usu());
		System.out.println(cuenta.getNom_usu());
		System.out.println(cuenta.getGen_usu());
		System.out.println(cuenta.getIns_usu());
		System.out.println(cuenta.getFac_usu());
	}
}
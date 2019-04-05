package sistema;

import java.util.Scanner;

public class Registrar {
	//Metodo "capturar" permite al usuario registrar una nueva cuenta. Por cambiar: TODO.
	public Usuario capturar() {
		int id = 1;
		int tipo = 0;
		String tipo_usuario = "";
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Ingresa tu correo electronico: ");
		String correo = scanner.nextLine();
		
		System.out.println("Ingresa tu contraseña: ");
		String contraseña = scanner.nextLine();
		
		boolean bandera = true;
		//Ciclo while, permite al usuario reingresar los datos en caso de que no se ingrese un valor aceptable.
		while (bandera) {
			System.out.println("Ingresa una opción: 1.- Artista 2.-Banda");
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
		
		System.out.println("Ingresa tu nombre de usuario: ");
		String nombre = scanner.nextLine();
		
		System.out.println("Ingresa el genero que interpretas: ");
		String genero = scanner.nextLine();
		
		System.out.println("Que instrumento tocas: ");
		String instrumento = scanner.nextLine();
		
		System.out.println("A que facultad perteneces: ");
		String facultad = scanner.nextLine();
		
		scanner.close();
		
		//Se declara e instancia un nuevo objeto de tipo Usuario, despues se utilizan los metodos de acceso para obtener los datos e imprimirlos.
		return new Usuario(id, correo, contraseña, tipo, nombre, genero, instrumento, facultad);
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
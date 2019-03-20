package sistema;

import java.util.Scanner;

public class Login {
	public static void main(String[] args) {
		Login lo = new Login();
		lo.registrar();
	}
	
	public Usuario registrar() {
		int id = 1;
		int tipo = 0;
		String tipo_usuario = "";
		boolean bandera = true;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Ingresa tu correo electrónico: ");
		String correo = scanner.nextLine();
		System.out.print("Ingresa tu contraseña: ");
		String contraseña = scanner.nextLine();
		
		//Ciclo while, permite al usuario reingresar los datos en caso de que no se ingrese un valor aceptable.
		while(bandera) {
			System.out.println("Ingresa una opcion: 1.- Artista 2.-Banda");
			tipo = Integer.parseInt(scanner.nextLine());
			
			//Switch, basicamente convierte el valor entero que ingreso el usuario para que se guarde como un string en otra variable.
			switch(tipo) { 
				case 1:
					tipo_usuario = "Artista";
					break;
				case 2:
					tipo_usuario = "Banda";
					break;
				default:
					System.out.println("Opcion incorrecta");
			}
		}
		
		System.out.print("Ingresa tu nombre de usuario: ");
		String nombre = scanner.nextLine();
		System.out.print("Ingresa el género que interpretas: ");
		String genero = scanner.nextLine();
		System.out.print("¿Qué instrumento tocas?: ");
		String instrumento = scanner.nextLine();
		System.out.print("¿A qué facultad perteneces?: ");
		String facultad = scanner.nextLine();
		
		//Se declara e instancia un nuevo objeto de tipo Usuario, despues se utilizan los metodos de acceso para obtener los datos e imprimirlos.
		Usuario cuenta = new Usuario(id, correo, contraseña, tipo, nombre, genero, instrumento, facultad);
		return cuenta;
	}
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

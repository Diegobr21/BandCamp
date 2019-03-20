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
		System.out.println("Ingresa tu correo electronico: ");
		String correo = scanner.nextLine();
		System.out.println("Ingresa tu contrase�a: ");
		String contrase�a = scanner.nextLine();
		//Ciclo while, permite al usuario reingresar los datos en caso de que no se ingrese un valor aceptable.
		while(bandera) {
		System.out.println("Ingresa una opcion: 1.- Artista 2.-Banda");
		tipo = Integer.parseInt(scanner.nextLine());
		//Switch, basicamente convierte el valor entero que ingreso el usuario para que se guarde como un string en otra variable.
		switch(tipo) { 
		case 1:
			tipo_usuario = "Artista";
			bandera = false;
			break;
		case 2:
			tipo_usuario = "Banda";
			bandera = false;
			break;
		default:
			System.out.println("Opcion incorrecta");
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
		//Se declara e instancia un nuevo objeto de tipo Usuario, despues se utilizan los metodos de acceso para obtener los datos e imprimirlos.
		Usuario cuenta = new Usuario(id, correo, contrase�a, tipo, nombre, genero, instrumento, facultad);
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
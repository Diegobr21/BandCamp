package sistema;

import java.util.Scanner;

public class Perfil {
	
	//Metodo "editar", recibe un objeto de tipo usuario le asigna el nombre de cuenta, retorna la cuenta. Por cambiar: Que el usuario seleccione que campo desea editar.
	public Usuario editar(Usuario cuenta) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa tu correo electronico: ");
		String correo = scanner.nextLine();
		cuenta.setCor_usu(correo);
		System.out.println("Ingresa tu contraseña: ");
		String contraseña = scanner.nextLine();
		cuenta.setPas_usu(contraseña);
		System.out.println("Ingresa tu nombre de usuario: ");
		String nombre = scanner.nextLine();
		cuenta.setNom_usu(nombre);
		System.out.println("Ingresa el genero musical que interpretas: ");
		String genero = scanner.nextLine();
		cuenta.setGen_usu(genero);
		System.out.println("Que instrumento tocas: ");
		String instrumento = scanner.nextLine();
		cuenta.setIns_usu(instrumento);
		System.out.println("A que facultad perteneces: ");
		String facultad = scanner.nextLine();
		cuenta.setFac_usu(facultad);
		return cuenta;
	}
}

package sistema;

import java.util.Scanner;

public class Inicio extends Perfil{

	public static void main(String[] args) {
		Registrar re = new Registrar();
		Login lo = new Login();
		Perfil pe = new Perfil();
		boolean bandera = true;
		Scanner scanner = new Scanner(System.in);
		while (bandera) {
		System.out.println("Que opcion desea realizar.");
		int opcion = Integer.parseInt(scanner.nextLine());
		switch (opcion) {
		case 1:
			re.capturar();
			break;
		case 2:
			lo.ingresar(cuenta);
			break;
		case 3:
			pe.editar(cuenta);
			break;
		default:
			re.imprimir(cuenta);
			bandera = false;
			}
		}
	}
}

package sistema;

import java.util.Scanner;

public class Inicio {

	public static void main(String[] args) {
		Usuario cuenta = null;
		
		Registrar registro = new Registrar();
		Login login = new Login();
		Perfil perfil = new Perfil();
		boolean bandera = true;
		Scanner scanner = new Scanner(System.in);
		
		while (bandera) {
			System.out.println("Que opcion desea realizar.");
			int opcion = Integer.parseInt(scanner.nextLine());
			
			switch (opcion) {
				case 1:
					// registro
					cuenta = registro.capturar();
					break;
				case 2:
					// incio de sesión
					cuenta = login.ingresar();
					break;
				case 3:
					// editar cuenta
					cuenta = perfil.editar(cuenta);
					break;
				default:
					registro.imprimir(cuenta);
					bandera = false;
					break;
			}
		}
		
		scanner.close();
	}
}

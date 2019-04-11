package sistema;

import java.sql.SQLException;
import java.util.Scanner;

public class Inicio {
	
	public static void main(String[] args) throws SQLException {
		Registrar re = new Registrar();
		Login lo = new Login();
		Perfil pe = new Perfil();
		Usuario cuenta = null;
		boolean bandera = true;
		Scanner scanner = new Scanner(System.in);
		
		while (bandera) {
			System.out.println("Que opcion desea realizar.");
			int opcion = Integer.parseInt(scanner.nextLine());
			switch (opcion) {
				case 1:
					cuenta = re.capturar();
					break;
				case 2:
					cuenta = lo.ingresar(cuenta);
					break;
				case 3:
					cuenta = pe.editar(cuenta);
					break;
				default:
					cuenta = re.imprimir(cuenta);
					bandera = false;
			}
		}
	}
}

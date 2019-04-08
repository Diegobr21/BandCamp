package sistema;

import javax.swing.JOptionPane;

import validaciones.ValidNum;

public class Inicio {
	public static void main(String[] args) {
		Usuario cuenta = null;
		//Registro registro = new Registro();
		Login login = new Login();
		Perfil perfil = new Perfil();
		
		boolean bandera = true;
		while (bandera) {
			int opcion = ValidNum.validInt("Opción: ", "Menú");
			
			switch (opcion) {
				case 0:
					return;
				case 1:
					// registro
					//cuenta = registro.capturar();
					break;
				case 2:
					// incio de sesión
					cuenta = login.ingresar();
					break;
				case 3:
					// editar cuenta
					cuenta = perfil.editar(cuenta);
					break;
				case 4:
					//registro.imprimir(cuenta);
					bandera = false;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Seleccionar solo opciones disponibles.",
							"Error", JOptionPane.WARNING_MESSAGE);
					break;
			}
		}
	}
}

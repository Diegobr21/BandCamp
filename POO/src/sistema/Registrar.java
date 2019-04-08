package sistema;

import javax.swing.JOptionPane;

import validaciones.ValidPassword;

public class Registrar {
	public void createAccount(String correo, String password, int tipo,
			String nombre, String genero, String instrumento, String facultad) {
		// modificar ID
		Usuario newAccount = new Usuario(0, correo, password, tipo, nombre, genero, instrumento, facultad);
		new Registrar().imprimir(newAccount);
		// enviar objeto newAccount a un registro de la base de datos
		return;
	}
	
	public boolean matchPasswords(String[] passwords) {
			if (!ValidPassword.isValidPassword(passwords[0])) {
				return false;
			}
			
			if (passwords[0].equals(passwords[1])) {
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			return false;
	}
	
	public boolean isFormComplete(String[] textFields) {
		for (String string : textFields) {
			if (string.equals("")) {
				JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos",
						"Formulario incompleto", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
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
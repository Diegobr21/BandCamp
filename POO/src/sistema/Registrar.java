package sistema;

import javax.swing.JOptionPane;

import validaciones.ValidPassword;

public class Registrar {
	/**
	 * Recibe los atributos de un objeto tipo {@link Usuario} para instanciar uno y enviarlo
	 * a un nuevo registro en la base de datos.
	 * @param correo <code>String</code> del correo electrónico.
	 * @param password <code>String</code> de la contraseña encriptada con MD5 (hash).
	 * @param tipo <code>int</code> del tipo de usuario (artista = 1 ; banda = 2).
	 * @param nombre <code>String</code> del nombre de usuario.
	 * @param genero <code>String</code> del género musical.
	 * @param instrumento <code>String</code> del instrumento.
	 * @param facultad <code>String</code> de la facultad.
	 * @see validaciones.MD5#hashPassword(String)
	 */
	public void createAccount(String correo, String password, int tipo,
			String nombre, String genero, String instrumento, String facultad) {
		// modificar ID
		Usuario newAccount = new Usuario(0, correo, password, tipo, nombre, genero, instrumento, facultad);
		imprimir(newAccount);
		// enviar objeto newAccount a un registro de la base de datos
		return;
	}
	
	/**
	 * Llama al método {@link validaciones.ValidPassword#isValidPassword} para verificar que la contraseña ingresada
	 * cumpla con los requisitos, después compara si ambas entradas coinciden.
	 * @param passwords Arreglo de tipo <code>String</code> que contiene las contraseñas ingresadas.
	 * @return <code>true</code> si la contraseña cumple con los requisitos y además ambas entradas
	 * coinciden. De lo contrario <code>false</code>.
	 */
	public boolean matchPasswords(String[] passwords) {
		if (ValidPassword.isValidPassword(passwords[0])) {
			if (passwords[0].equals(passwords[1])) {
				return true;
			}
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	/**
	 * Valida que todos los campos del formulario de registro se hayan llenado.
	 * @param textFields Arreglo de tipo <code>String</code> que contiene
	 * los valores de cada campo del formulario.
	 * @return <code>false</code> si algún campo está vacío. Si todos han sido llenados,
	 * retorna <code>true</code>.
	 */
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
	
	/**
	 * Método que imprime los atributos de un usuario.
	 * @param cuenta objeto de tipo <code>Usuario</code> que contiene los atributos a imprimir.
	 */
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
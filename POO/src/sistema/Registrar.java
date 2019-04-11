package sistema;

import javax.swing.JOptionPane;

import validaciones.MD5;
import validaciones.ValidPassword;

public class Registrar {
	/**
	 * Recibe los atributos de un objeto tipo {@link Usuario} desde la interfaz de registro 
	 * para validar la informaci�n, si es correcta instanciar uno y enviarlo 
	 * a un nuevo registro en la base de datos.
	 * @param correo <code>String</code> del correo electr�nico.
	 * @param password Arreglo tipo <code>String</code> de las contrase�as ingresadas.
	 * @param tipo <code>int</code> del tipo de usuario (artista = 1 ; banda = 2).
	 * @param nombre <code>String</code> del nombre de usuario.
	 * @param genero <code>String</code> del g�nero musical.
	 * @param instrumento <code>String</code> del instrumento.
	 * @param facultad <code>String</code> de la facultad.
	 * @return <code>true</code> si la cuenta fue creada, de lo contrario <code>false</code>.
	 * @see validaciones.MD5#hashPassword(String)
	 */
	public boolean createAccount(String correo, String[] passwords, int tipo,
			String nombre, String genero, String instrumento, String facultad) {
		
		if (isFormComplete(new String[] {correo, nombre, passwords[0], passwords[1]})) {
			if (matchPasswords(passwords)) {
				MD5 hasher = new MD5();
				passwords[1] = hasher.hashPassword(passwords[0]);
			}
			else return false;
		}
		else return false;
		
		// ID ?
		Usuario newAccount = new Usuario(correo, passwords[1], tipo, nombre, genero, instrumento, facultad);
		imprimir(newAccount);
		// enviar objeto newAccount a un registro de la base de datos
		return true;
	}
	
	/**
	 * Llama al m�todo {@link validaciones.ValidPassword#isValidPassword} para verificar que la contrase�a ingresada
	 * cumpla con los requisitos, despu�s compara si ambas entradas coinciden.
	 * @param passwords Arreglo de tipo <code>String</code> que contiene las contrase�as ingresadas.
	 * @return <code>true</code> si la contrase�a cumple con los requisitos y adem�s ambas entradas
	 * coinciden. De lo contrario <code>false</code>.
	 */
	private boolean matchPasswords(String[] passwords) {
		if (ValidPassword.isValidPassword(passwords[0])) {
			if (passwords[0].equals(passwords[1])) {
				return true;
			} 
			JOptionPane.showMessageDialog(null, "Debes introducir la misma contrase�a en ambos campos.",
					"Contrase�as no coinciden", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	/**
	 * Valida que todos los campos del formulario de registro se hayan llenado.
	 * @param textFields Arreglo de tipo <code>String</code> que contiene
	 * los valores de cada campo del formulario.
	 * @return <code>false</code> si alg�n campo est� vac�o. Si todos han sido llenados,
	 * retorna <code>true</code>.
	 */
	private boolean isFormComplete(String[] textFields) {
		for (String string : textFields) {
			if (string.equals("")) {
				JOptionPane.showMessageDialog(null, "Parece que dejaste alg�n campo vac�o.",
						"Formulario incompleto", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * M�todo que imprime los atributos de un usuario.
	 * @param cuenta objeto de tipo <code>Usuario</code> que contiene los atributos a imprimir.
	 */
	private void imprimir(Usuario cuenta) {
		System.out.println(cuenta.toString());
	}
}
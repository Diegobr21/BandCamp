package validaciones;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

/**
 * Contiene los métodos para validar el formulario de registro.
 */
public class ValidSignup {
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
			JOptionPane.showMessageDialog(null, "Debes introducir la misma contraseña en ambos campos.",
					"Contraseñas no coinciden", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Parece que dejaste algún campo vacío.",
						"Formulario incompleto", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Valida que el correo electrónico cumpla con el formato:
	 * <br> correo@dominio.ext
	 * @param correo {@code String} del correo electrónico.
	 * @return {@code true} solo si el correo cumple con el formato.
	 */
	public boolean validEmail(String correo) {
		try {
			InternetAddress email = new InternetAddress(correo);
			email.validate();
			return true;
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "El correo electrónico no cumple con un formato válido.",
					"Correo no válido" ,JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	
	/**
	 * 
	 * @param nombres {@code ResultSet} se le pasa el ResultSet realizado.
	 * @param editado {@code Usuario} con los atributos editados.
	 * @return {@code false} en caso de que el nombre de usuario no exista. 
	 * @throws SQLException
	 */
	public boolean usernameExists(ResultSet nombres, String nombre) throws SQLException {
		while (nombres.next()) {
			if (nombre.equals(nombres.getString("nom_usu"))) {
				return true;
			}
		}
		return false;
	}
}

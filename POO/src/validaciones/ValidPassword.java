package validaciones;

import javax.swing.JOptionPane;

/**
 * Clase para validar si la contraseña ingresada cumple con los requisitos mencionados en su método.
 */
public class ValidPassword {
	
	/**
	 * Método que valida mediante expresiones regulares si el <code>String</code> de entrada cumple con:
	 * <br> Tiene entre 8 y 16 caracteres.
	 * <br> Tiene al menos un dígito.
	 * <br> Tiene al menos un caracter especial.
	 * <br> No tiene espacios.
	 * @param input <code>String</code> a ser validado según los requisitos anteriores.
	 * @return <code>true</code> si la contraseña es válida, de lo contrario <code>false</code>.
	 */
	public static boolean isValidPassword(String input) {
		if (input.matches("(?=.*\\d)((?=.*[a-z])|(?=.*[A-Z]))(?=.*[!?_@#$%^&+=])(?=\\S+$).{8,16}")) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null,
					"La contraseña debe incluir al menos un número, al menos un caracter especial,\n"
					+ "y ser entre 8 y 16 caracteres de largo.",
					"Contraseña inválida", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}

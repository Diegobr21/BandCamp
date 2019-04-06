package validaciones;

import javax.swing.JOptionPane;

/**
 * Clase con métodos para validar si un <code>String</code> representa un tipo <code>int</code> válido.
 */
public class ValidNum {
	
	/**
	 * Método booleando que valida si un <code>String</code> representa un <code>int</code>.
	 * @param input <code>String</code> de entrada que será parseado a <code>int</code>.
	 * @return <code>true</code> si el <code>String</code> representa un <code>int</code> válido.
	 */
	private static boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException error) {
			return false;
		}
	}
	
	/**
	 * Pide una entrada de tipo <code>String</code> para luego
	 * ser validada como entero mediante un cuadro de diálogo 
	 * de <code>JOptionPane</code>.
	 * @param request mensaje de solicitud que aparecerá en el diálogo.
	 * @param title título del diálogo.
	 * @return el valor <code>int</code> de la entrada.
	 */
	public static int validInt(String request, String title) {
		String input = JOptionPane.showInputDialog(null, request, title, JOptionPane.QUESTION_MESSAGE);
		
		if (isInt(input)) {
			return Integer.parseInt(input);
		}
		else {
			JOptionPane.showMessageDialog(null, "Introducir enteros solamente.", "Error", JOptionPane.ERROR_MESSAGE);
			return validInt(request, title);
		}
	}
}

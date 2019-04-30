package validaciones;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Contiene los métodos para validar el formulario de registro.
 */
public class ValidUsername {
	
	/**
	 * Valida el nombre de usuario ingresado de acuerdo a lo siguiente:
	 * <br>No puede contener espacios.
	 * <br>No puede contener caracteres especiales.
	 * @param nombre {@code String} del nombre de usuario ingresado.
	 * @return {@code true} solo si cumple con los requisitos de arriba.
	 */
	public static boolean isValidName(String nombre) {
		if (nombre.matches(".*\\s.*")) {
			JOptionPane.showMessageDialog(null, "El nombre de usuario no puede contener espacios.\n",
					"Nombre de usuario incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (nombre.matches(".*[!?@#$%^&+=].*")) {
			JOptionPane.showMessageDialog(null, "El nombre de usuario no puede contener caracteres especiales.",
					"Nombre de usuario incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (nombre.matches(".{6,18}")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El nombre de usuario debe contener entre 6 y 12 caracteres.",
					"Nombre de usuario incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	/**
	 * Itera los nombres registrados en la base de datos para comprobar que el nombre ingresado no esté guardado ya.
	 * @param nombres {@code ResultSet} se le pasa el ResultSet realizado.
	 * @param nombre {@code String} del nombre de usuario ingresado.
	 * @return {@code false} en caso de que el nombre de usuario no exista. 
	 * @throws SQLException
	 */
	public static boolean usernameExists(ResultSet nombres, String nombre) throws SQLException {
		while (nombres.next()) {
			if (nombre.equals(nombres.getString("nom_usu"))) {
				JOptionPane.showMessageDialog(null, "Llegaste tarde, el nombre que has escogido ya está en uso.",
						"Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
				return true;
			}
		}
		return false;
	}
}

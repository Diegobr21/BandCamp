package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 * Clase que maneja la funcionalidad del contacto entre cuentas.
 */
public class Contacto {
	
	/**
	 * Crea un registro de notificación en la tabla <i>Notificaciones</i> de la base de datos.
	 * @param id_remitente {@code int} que almacena el ID de la sesión iniciada.
	 * @param id_destinatario {@code int} que almacena el ID del {@code Usuario} destino.
	 * @see {@link Usuario}
	 */
	public static void crearNotificacion(int id_remitente, int id_destinatario) {
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			System.out.println("\tconectado notif");
			
			String insertNotifQuery = "INSERT INTO Notificaciones VALUES(?, ?, 1);";
			try (PreparedStatement insertNotifStatement = connection.prepareStatement(insertNotifQuery)) {
				insertNotifStatement.setInt(1, id_remitente);
				insertNotifStatement.setInt(2, id_destinatario);
				
				int rowsAdded = insertNotifStatement.executeUpdate();
				if (rowsAdded == 0) {
					JOptionPane.showMessageDialog(null, "Hubo un error al contactar al usuario.", 
							"No se creó notificación", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (SQLException error) {
			error.printStackTrace();
			JOptionPane.showMessageDialog(null, "Hubo un error al contactar al usuario.",
					"Error de servidor", JOptionPane.ERROR_MESSAGE);
		}
	}
}
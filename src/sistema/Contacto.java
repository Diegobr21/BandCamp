package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 * Clase que maneja la funcionalidad del contacto entre cuentas.
 */
public class Contacto {
	
	/**
	 * Notificación rechazada.
	 */
	public static final short REJECT = 0;
	
	/**
	 * Notificación pendiente.
	 */
	public static final short PENDING = 1;
	
	/**
	 * Notificación aceptada.
	 */
	public static final short ACCEPT = 2;
	
	/**
	 * Crea un registro de notificación en la tabla <i>Notificaciones</i> de la base de datos.
	 * @param id_remitente {@code int} que almacena el ID de la sesión iniciada.
	 * @param id_destinatario {@code int} que almacena el ID del {@code Usuario} destino.
	 * @see {@link Usuario}
	 */
	public static void crearNotificacion(int id_remitente, int id_destinatario) {
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			System.out.println("conectado notif");
			
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
	
	/**
	 * Verifica si ya ha sido enviada una notificación para evitar duplicadas.
	 * @param id_remitente {@code int} del ID del {@code Usuario} que envió la notificación.
	 * @param id_destinatario {@code int} del ID del {@code Usuario} que la recibió.
	 * @return {@code true} si ya existía un contacto entre ambos usuarios.
	 * @see {@link Usuario}
	 */
	public static boolean alreadyContacted(int id_remitente, int id_destinatario) {
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			System.out.println("conectado notif");
			
			String selectNotifQuery = "SELECT * FROM Notificaciones WHERE orig_not = ? AND dest_not = ? AND est_not = 1;";
			try (PreparedStatement selectNotifStatement = connection.prepareStatement(selectNotifQuery)) {
				selectNotifStatement.setInt(1, id_remitente);
				selectNotifStatement.setInt(2, id_destinatario);
				
				try (ResultSet selectedNotifSet = selectNotifStatement.executeQuery()) {
					while (selectedNotifSet.next()) {
						return true;
					}
				}
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Modifica el estado en el registro de una notificación.
	 * @param id_remitente {@code int} del ID del {@code Usuario} que envió la notificación.
	 * @param id_destinatario {@code int} del ID del {@code Usuario} que la recibió.
	 * @param estado {@code short} que almacena el código del estado:
	 * @see {@link Contacto#REJECT}
	 * @see {@link Contacto#PENDING}
	 * @see {@link Contacto#ACCEPT}
	 */
	public static void replyContact(int id_remitente, int id_destinatario, short estado) {
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			System.out.println("conectado");
			
			String selectNotifQuery = "UPDATE Notificaciones SET est_not = ? "
									+ "WHERE orig_not = ? AND dest_not = ?;";
			try (PreparedStatement selectNotifStatement = connection.prepareStatement(selectNotifQuery)) {
				selectNotifStatement.setShort(1, estado);
				selectNotifStatement.setInt(2, id_remitente);
				selectNotifStatement.setInt(3, id_destinatario);
				
				int rowsUpdated = selectNotifStatement.executeUpdate();
				if (rowsUpdated == 0) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el contacto.", 
							"Contacto sin modificar", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException error) {
			error.printStackTrace();
			JOptionPane.showMessageDialog(null, "Hubo un error al modificar el contacto.", 
					"Error de servidor", JOptionPane.ERROR_MESSAGE);
		}
	}
}
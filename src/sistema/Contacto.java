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
				
				int rows_added = insertNotifStatement.executeUpdate();
				if (rows_added == 0) {
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
	 * @see {@link Usuario}
	 */
	public static void replyContact(int id_remitente, int id_destinatario, short estado) {
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			System.out.println("conectado");
			
			String updateNotifQuery = "UPDATE Notificaciones SET est_not = ? "
									+ "WHERE orig_not = ? AND dest_not = ?;";
			try (PreparedStatement updateNotifStatement = connection.prepareStatement(updateNotifQuery)) {
				updateNotifStatement.setShort(1, estado);
				updateNotifStatement.setInt(2, id_remitente);
				updateNotifStatement.setInt(3, id_destinatario);
				
				int rows_updated = updateNotifStatement.executeUpdate();
				if (rows_updated == 0) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error al responder el contacto.", 
							"Contacto sin modificar", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException error) {
			error.printStackTrace();
			JOptionPane.showMessageDialog(null, "Hubo un error al modificar el contacto.", 
					"Error de servidor", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Deshabilita ambas cuentas que aceptaron la notificación entre sí.
	 * @param id_cuenta1 {@code int} del ID de uno de los {@code Usuario}s.
	 * @param id_cuenta2 {@code int} del ID del otro.
	 */
	public static void deshabilitarCuentas(int id_cuenta1, int id_cuenta2) {
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			String updateUserString = "UPDATE Usuarios SET dis_usu = 0 "
									+ "WHERE id_usu IN (?, ?);";
			try (PreparedStatement updateUserStatement = connection.prepareStatement(updateUserString)) {
				updateUserStatement.setInt(1, id_cuenta1);
				updateUserStatement.setInt(2, id_cuenta2);
				
				int rows_updated = updateUserStatement.executeUpdate();
				if (rows_updated == 0) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error al deshabilitar las cuentas.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			rechazarNotifs(id_cuenta1, connection);
			rechazarNotifs(id_cuenta2, connection);
			
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	
	/**
	 * Rechaza todas las notificaciones pendientes que tenga el usuario.
	 * @param id_destinatario {@code int} del ID del {@code Usuario} destinatario de las notificaciones.
	 * @param connection {@code Connection} de la base de datos.
	 * @see {@link Usuario}
	 */
	static void rechazarNotifs(int id_destinatario, Connection connection) {
		String updateNotifsQuery = "UPDATE Notificaciones SET est_not = 0 "
								+ "WHERE dest_not = ? AND est_not = 1;";
		try (PreparedStatement updateNotifStatement = connection.prepareStatement(updateNotifsQuery)) {
			updateNotifStatement.setInt(1, id_destinatario);
			int rows_updated = updateNotifStatement.executeUpdate();

			System.out.println(rows_updated);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Ocurrió un problema al modificar las notificaciones.", 
					"Error de servidor", JOptionPane.ERROR_MESSAGE);
			error.printStackTrace();
		}
	}
}
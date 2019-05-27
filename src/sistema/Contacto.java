package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	 * Crea un registro de notificación en la tabla deseada de la base de datos.
	 * @param tabla {@code String} del nombre de la tabla.
	 * @param id_remitente {@code int} que almacena el ID de la sesión iniciada.
	 * @param id_destinatario {@code int} que almacena el ID del {@code Usuario} destino.
	 * @see {@link Usuario}
	 */
	public static void crearNotificacion(String tabla, int id_remitente, int id_destinatario) {
		try (Connection connection = DriverManager.getConnection(Credenciales.DB_URL, Credenciales.DB_USER, Credenciales.DB_PASSWORD)) {
			System.out.println("conectado notif");
			
			String insertNotifQuery = "INSERT INTO " + tabla + " VALUES(?, ?, 1, ?);";
			try (PreparedStatement insertNotifStatement = connection.prepareStatement(insertNotifQuery)) {
				insertNotifStatement.setInt(1, id_remitente);
				insertNotifStatement.setInt(2, id_destinatario);
				
				LocalDate fecha = LocalDate.now();
				insertNotifStatement.setObject(3, fecha);
				
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
	 * @param estado {@code short} del estado del contacto: {@code REJECT}, {@code PENDING} o {@code ACCEPT}.
	 * @return {@code true} si ya existía un contacto entre ambos usuarios.
	 * @see {@link Usuario}
	 */
	public static boolean alreadyContacted(int id_remitente, int id_destinatario, short estado) {
		try (Connection connection = DriverManager.getConnection(Credenciales.DB_URL, Credenciales.DB_USER, Credenciales.DB_PASSWORD)) {
			System.out.println("conectado notif");
			
			String selectNotifQuery = "SELECT * FROM Notificaciones "
					+ "WHERE orig_not = ? AND dest_not = ? AND est_not = ?;";
			try (PreparedStatement selectNotifStatement = connection.prepareStatement(selectNotifQuery)) {
				selectNotifStatement.setInt(1, id_remitente);
				selectNotifStatement.setInt(2, id_destinatario);
				selectNotifStatement.setShort(3, estado);
				
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
		try (Connection connection = DriverManager.getConnection(Credenciales.DB_URL, Credenciales.DB_USER, Credenciales.DB_PASSWORD)) {
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
	 * Lista los usuarios que han enviado una notificación a la sesión iniciada.
	 * @param id_destinatario {@code int} que almacena el id_usu de la sesión iniciada.
	 * @return {@code List} con los usuarios remitentes.
	 * @see {@link Usuario}
	 */
	public static List<Usuario> listarRemitentes (int id_destinatario) {
		String selectUserString = "SELECT * FROM Usuarios WHERE id_usu IN "
				+ "(SELECT orig_not FROM Notificaciones WHERE dest_not = ? AND est_not = 1) ;";
		try ( Connection connection = DriverManager.getConnection(Credenciales.DB_URL, Credenciales.DB_USER, Credenciales.DB_PASSWORD);
				PreparedStatement selectUserStatement = connection.prepareStatement(selectUserString) ) {
			System.out.println("Conectado");
			
			selectUserStatement.setInt(1, id_destinatario);
			
			List<Usuario> listRemitentes = new ArrayList<Usuario>();
			try (ResultSet selectedUsers = selectUserStatement.executeQuery()) {
				while (selectedUsers.next()) {
					Usuario remitente = new Usuario(selectedUsers);
					remitente.setCor_usu("");
					remitente.setPas_usu("");
					listRemitentes.add(remitente);
				}
			}
			
			return listRemitentes;
			
		} catch (SQLException error) {
			error.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Lo sentimos, no fue posible cargar las notificaciones.", 
				"Error de servidor", JOptionPane.ERROR_MESSAGE);
		
		return null;
	}
}
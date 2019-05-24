package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Union {
	
	/**
	 * Verifica si ya ha sido enviada una notificación para evitar duplicadas.
	 * @param id_remitente {@code int} del ID del {@code Usuario} que envió la notificación.
	 * @param id_destinatario {@code int} del ID del {@code Usuario} que la recibió.
	 * @param estado {@code short} del estado del contacto: {@code REJECT}, {@code PENDING} o {@code ACCEPT}.
	 * @return {@code true} si ya existía un contacto entre ambos usuarios.
	 * @see {@link Usuario}
	 */
	public static boolean alreadyContacted(int id_remitente, int id_destinatario, short estado) {
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			System.out.println("conectado notif");
			
			String selectNotifQuery = "SELECT * FROM Uniones "
					+ "WHERE orig_uni = ? AND dest_uni = ? AND est_uni = ?;";
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
		try (Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			System.out.println("conectado");
			
			String updateNotifQuery = "UPDATE Uniones SET est_uni = ? "
									+ "WHERE orig_uni = ? AND dest_uni = ?;";
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
		String updateNotifsQuery = "UPDATE Uniones SET est_uni = 0 "
								+ "WHERE (orig_uni = ? OR dest_uni = ?) AND est_uni = 1;";
		try (PreparedStatement updateNotifStatement = connection.prepareStatement(updateNotifsQuery)) {
			updateNotifStatement.setInt(1, id_destinatario);
			updateNotifStatement.setInt(2, id_destinatario);
			int rows_updated = updateNotifStatement.executeUpdate();

			System.out.println(rows_updated);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Ocurrió un problema al modificar las notificaciones.", 
					"Error de servidor", JOptionPane.ERROR_MESSAGE);
			error.printStackTrace();
		}
	}
	
	/**
	 * Lista los usuarios que han enviado una solicitud de unión a la sesión iniciada.
	 * @param id_destinatario {@code int} que almacena el id_usu de la sesión iniciada.
	 * @return {@code List} con los usuarios remitentes.
	 * @see {@link Usuario}
	 */
	public static List<Usuario> listarRemitentes (int id_destinatario) {
		String selectUserString = "SELECT * FROM Usuarios WHERE id_usu IN "
				+ "(SELECT orig_uni FROM Uniones WHERE dest_uni = ? AND est_uni = 1) ;";
		try ( Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
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

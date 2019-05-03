package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Maneja la funcionalidad de la interfaz del muro.
 */
public class Muro {
	
	/**
	 * Genera una lista de las cuentas filtradas.
	 * @param sesionIniciada {@code Usuario} de la sesión iniciada para filtrar las cuentas.
	 * @return lista de {@code Usuario}s que fueron filtrados.
	 */
	public static List<Usuario> filtrarCuentas(Usuario sesionIniciada) {
		String selectUserString = "SELECT * FROM Usuarios WHERE "
				+ "tip_usu = ? AND gen_usu = ? AND ins_usu = ? AND dis_usu = ?;";
		try( Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement selectUsersPreparedStatement = connection.prepareStatement(selectUserString) ) {
			
			System.out.println("Conectado");
			
			int tipo = 0;
			switch (sesionIniciada.getTip_usu()) {
				case 1:
					tipo = 2;
					break;
				case 2:
					tipo = 1;
					break;
			}
			
			selectUsersPreparedStatement.setInt(1, tipo);
			selectUsersPreparedStatement.setString(2, sesionIniciada.getGen_usu());
			selectUsersPreparedStatement.setString(3, sesionIniciada.getIns_usu());
			selectUsersPreparedStatement.setBoolean(4, true);
			
			List<Usuario> cuentasFiltradas = new ArrayList<Usuario>();
			
			try (ResultSet filteredUsers = selectUsersPreparedStatement.executeQuery()){
				while (filteredUsers.next()) {
					Usuario cuentaFiltrada = new Usuario(filteredUsers);
					cuentaFiltrada.setCor_usu("");
					cuentaFiltrada.setPas_usu("");
					
					if (cuentaFiltrada.getFac_usu().equals(sesionIniciada.getFac_usu())) {
						cuentasFiltradas.add(0, cuentaFiltrada);
					}
					else cuentasFiltradas.add(cuentaFiltrada);
				}
			}
			
			return cuentasFiltradas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "Ha ocurrido un error al iniciar el muro.", "Error de servidor", JOptionPane.ERROR_MESSAGE);
		return null;
	}
	
	/**
	 * Consulta todas las notificaciones registradas de la base de datos.
	 * @return cantidad de notificaciones aceptadas.
	 */
	public static int countMatches() {
		String selectAcceptedString = "SELECT * FROM Notificaciones WHERE est_not = ? ;";
		try ( Connection connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement selectAcceptedPreparedStatement = connection.prepareStatement(selectAcceptedString) ) {
			System.out.println("Conectado");
			
			selectAcceptedPreparedStatement.setInt(1, 2);
			
			int count = 0;
			try (ResultSet acceptedResultSet = selectAcceptedPreparedStatement.executeQuery()) {
				while (acceptedResultSet.next()) {
					++count;
				}
			}
			return count;
			
		} catch (SQLException error) {
			error.printStackTrace();
		}
		
		return 0;
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

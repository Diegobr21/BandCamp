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
		String selectUserString = "SELECT * FROM Usuarios WHERE"
				+ "tip_usu = ? AND gen_usu = ? AND ins_usu = ? AND dis_usu = 1;";
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
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al iniciar el muro.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return null;
	}
}

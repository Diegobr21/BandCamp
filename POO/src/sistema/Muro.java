package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Maneja la funcionalidad de la interfaz del muro.
 */
public class Muro {
	
	/**
	 * Genera una lista de las cuentas filtradas.
	 * @param sesionIniciada {@code Usuario} de la sesión iniciada para filtrar las cuentas.
	 * @return lista de {@code Usuario}s que fueron filtrados.
	 */
	public List<Usuario> filtrarCuentas(Usuario sesionIniciada) {
		
		String selectUserString = "";
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(DBInfo.url, DBInfo.usuario, DBInfo.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

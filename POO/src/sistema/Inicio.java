package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import interfaces.UserLogin;

/**
 * Primera clase en ejecutarse al iniciar el sistema.
 * Hace una conexión inicial a la base de datos para verificar que se pueda seguir usando el sistema.
 * Redirige al inicio de sesión.
 */
class Inicio {
	public static void main(String[] args) {
		
		// test de conexión al servidor
		try {
			Connection connection = DriverManager.getConnection(DBInfo.url, DBInfo.usuario, DBInfo.password);
			System.out.println("Conexión establecida");
			
			UserLogin logIn = new UserLogin();
			logIn.setLocationRelativeTo(null);
			logIn.setVisible(true);
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lo sentimos, parece que nuestros servidores fallaron.\n¡Inténtalo en unos minutos!",
					"Error de servidor", JOptionPane.ERROR_MESSAGE);
		}
	}
}

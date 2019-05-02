package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import validaciones.MD5;

public class Login {
	
	/**
	 * Verifica que los datos ingresados coincidan con la cuenta deseada para iniciar.
	 * @param correo <code>String</code> del correo electrónico.
	 * @param password <code>String</code> de la contraseña (no encriptada).
	 * @return Si los datos son correctos retorna un instancia de <code>Usuario</code>,
	 * si no coinciden retorna <code>null</code>.
	 */
	public static Usuario ingresar(String correo, String password) {
		String selectUsuarios = "SELECT * FROM Usuarios WHERE cor_usu = ? ;";
		
		try ( 	Connection con = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement selectUsu = con.prepareStatement(selectUsuarios) ) {
			
			System.out.println("Conexión establecida");
			
			selectUsu.setString(1, correo);
			try ( ResultSet usuarios = selectUsu.executeQuery() ) {
				while (usuarios.next()) {
					String hashedPswd = MD5.hashPassword(password);
					if (hashedPswd.equals(usuarios.getString("pas_usu"))) {
						return new Usuario(usuarios);

					} else {
						JOptionPane.showMessageDialog(null, "El correo y la contraseña no coinciden con nuestros registros.",
							"Correo y contraseña no concuerdan", JOptionPane.ERROR_MESSAGE);
						return null;
					}
				}
				
				JOptionPane.showMessageDialog(null, "El correo que ingresaste no se encuentra en nuestros servidores.\n¡Prueba a registrarte!",
						"Correo sin registrar", JOptionPane.ERROR_MESSAGE);
			}
						
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "Lo sentimos, no es posible iniciar sesión.", "Error de servidor", JOptionPane.ERROR_MESSAGE);
		return null;
	}
}
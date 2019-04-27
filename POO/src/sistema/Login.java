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
	public Usuario ingresar(String correo, String password) {
		MD5 hasher = new MD5();
		String hashedPswd = hasher.hashPassword(password);
		
		Connection con = null;
		PreparedStatement selectCor = null;
		PreparedStatement selectPas = null;
		PreparedStatement selectUsu = null;
		ResultSet correos = null;
		ResultSet passwords = null;
		ResultSet usuarios = null;
		
		String selectCorreos = "SELECT cor_usu FROM Usuarios";
		String selectPasswords = "SELECT pas_usu FROM Usuarios";
		String selectUsuarios = "SELECT * FROM Usuarios WHERE cor_usu = '" + correo + "'";
		
		try {
			con = DriverManager.getConnection(DBInfo.url, DBInfo.usuario, DBInfo.password);
			System.out.println("Conexion establecida");
			selectCor = con.prepareStatement(selectCorreos);
			selectPas = con.prepareStatement(selectPasswords);
			selectUsu = con.prepareStatement(selectUsuarios);
			
			boolean correoValido = false;
			boolean passwordValido = false;
			
			correos = selectCor.executeQuery();
			
			while (correos.next()) {
				if (correo.equals(correos.getString("cor_usu"))) {
					correoValido = true;
					break;
				}
			}
			
			passwords = selectPas.executeQuery();
			
			while (passwords.next()) {
				if (hashedPswd.equals(passwords.getString("pas_usu"))) {
					passwordValido = true;
					break;
				}
			}
			
			usuarios = selectUsu.executeQuery();
			
			if (usuarios.next() && correoValido && passwordValido) {
				return new Usuario(usuarios);
			} else {
				JOptionPane.showMessageDialog(null, "No pudimos encontrar el correo y la contraseña que ingresaste.\n¡Prueba a registrarte!",
						"Correo y contraseña no coinciden", JOptionPane.ERROR_MESSAGE);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
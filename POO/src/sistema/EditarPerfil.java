package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import validaciones.ValidSignup;

public class EditarPerfil {

	/**
	 * Edita los atributos que el usuario haya decidido cambiar.
	 * @param original {@code Usuario} con los atributos sin editar.
	 * @param editado {@code Usuario} con los atributos editados.
	 * @return {@code true} si el registro se actualizó correctamente en la BD, de lo contrario {@code false}.
	 * @throws SQLException 
	 */
	public boolean editarCuenta(Usuario original, Usuario editado) throws SQLException {
		if (editado.getNom_usu().equals("")) {
			JOptionPane.showMessageDialog(null, "¡Necesitas un nombre!", "Sin nombre", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		Connection con = null;
		PreparedStatement selectNom = null;
		PreparedStatement insertNom = null;
		ResultSet nombres = null;
		
		
		String sql = "SELECT nom_usu FROM Usuarios";
		String insert = "UPDATE Usuarios SET nom_usu = ?, gen_usu = ?, ins_usu = ?, fac_usu = ?, des_usu = ? WHERE id_usu = ?";
		try {
			con = DriverManager.getConnection(DBInfo.url, DBInfo.usuario, DBInfo.password);
			System.out.println("Conexion establecida");
			selectNom = con.prepareStatement(sql);
			insertNom = con.prepareStatement(insert);
		} catch (SQLException e) {
			e.getMessage();
		}
		
		nombres = selectNom.executeQuery();
		ValidSignup editName = new ValidSignup();
		if (editName.usernameExists(nombres, editado.getNom_usu())) {
			JOptionPane.showMessageDialog(null, "Llegaste tarde, el nombre que has escogido ya está en uso.",
					"Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			insertNom.setString(1, editado.getNom_usu());
			insertNom.setString(2, editado.getGen_usu());
			insertNom.setString(3, editado.getIns_usu());
			insertNom.setString(4, editado.getFac_usu());
			insertNom.setString(5, editado.getDes_usu());
			insertNom.setInt(6, editado.getId());
		}
		
		int rsu = insertNom.executeUpdate();
		if (rsu == 0) {
			JOptionPane.showMessageDialog(null, "0 filas afectadas", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
}

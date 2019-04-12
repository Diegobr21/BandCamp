package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class EditarPerfil {
	
	/**
	 * 
	 * @param nombres {@code ResultSet} se le pasa el ResultSet realizado.
	 * @param editado {@code Usuario} con los atributos editados.
	 * @return {@code false} en caso de que el nombre de usuario no exista. 
	 * @throws SQLException
	 */
	private boolean usernameExists(ResultSet nombres, Usuario editado) throws SQLException {
		while (nombres.next()) {
			if (editado.equals(nombres.getString("nom_usu"))) {
				break;
			} else {
				return false;
			}
		}
		return true;
	}
	/**
	 * Edita los atributos que el usuario haya decidido cambiar.
	 * @param original {@code Usuario} con los atributos sin editar.
	 * @param editado {@code Usuario} con los atributos editados.
	 * @return {@code true} si el registro se actualizó correctamente en la BD, de lo contrario {@code false}.
	 * @throws SQLException 
	 */
	public boolean editarCuenta(Usuario original, Usuario editado) throws SQLException {
		Connection con = null;
		PreparedStatement selectNom = null;
		PreparedStatement insertNom = null;
		ResultSet nombres = null;
		ResultSet nombreEditado = null;
		
		String usuario = "DanielSal";
		String pass = "Avion123";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Server";
		String sql = "SELECT nom_usu FROM Usuarios";
		String insert = "UPDATE Usuarios SET tip_usu = ";
		
		try {
			con = DriverManager.getConnection(url, usuario, pass);
			con.setAutoCommit(false);
			System.out.println("Conexion establecida");
			selectNom = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.getMessage();
		}
		
		nombres = selectNom.executeQuery();

		if (editado.getNom_usu().equals("")) {
			JOptionPane.showMessageDialog(null, "¡Necesitas un nombre!", "Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
		} 
		else {
//			if ( (original.getNom_usu() != editado.getNom_usu()) && yaExiste() ) {
//				JOptionPane.showMessageDialog(null, "Llegaste tarde, el nombre que has escogido ya está en uso.",
//						"Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//			else {
				// query a la BD para cambiar los campos que hayan sido editados
//			}
			if (usernameExists(nombres, editado)) {
				JOptionPane.showMessageDialog(null, "Llegaste tarde, el nombre que has escogido ya está en uso.",
						"Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
			}
			else {
				insert += editado.getTip_usu() + ", ";
				insert += "nom_usu = " + ("\'" + editado.getNom_usu() + "\', ");
				insert += "gen_usu = " + ("\'" + editado.getGen_usu() + "\', ");
				insert += "ins_usu = " + ("\'" + editado.getIns_usu() + "\', ");
				insert += "fac_usu = " + ("\'" + editado.getFac_usu() + "\' ");
				insert += "WHERE nom_usu = ";
				insert += ("\'" + original + "\'");
				insertNom = con.prepareStatement(insert);
			}
			int rsu = insertNom.executeUpdate();
			if (rsu > 0) {
				JOptionPane.showMessageDialog(null, "Perfil editado", "Cambios guardados!", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "0 filas afectadas", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return true;
	}
	
}

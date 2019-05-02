package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import validaciones.ValidUsername;

public class EditarPerfil {

	/**
	 * Edita los atributos que el usuario haya decidido cambiar.
	 * @param original {@code Usuario} con los atributos sin editar.
	 * @param editado {@code Usuario} con los atributos editados.
	 * @return {@code true} si el registro se actualizˇ correctamente en la BD, de lo contrario {@code false}.
	 */
	public static boolean editarCuenta(Usuario original, Usuario editado) {
		if (editado.getNom_usu().equals("")) {
			JOptionPane.showMessageDialog(null, "íNecesitas un nombre!", "Sin nombre", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String sql = "SELECT nom_usu FROM Usuarios";
		String insert = "UPDATE Usuarios SET nom_usu = ?, gen_usu = ?, ins_usu = ?, "
				+ "fac_usu = ?, des_usu = ?, dis_usu = ? WHERE id_usu = ?";
		
		try ( Connection con = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement selectNom = con.prepareStatement(sql);
				PreparedStatement insertNom = con.prepareStatement(insert) ) {
			
			System.out.println("Conexion establecida");
			
			String nuevoNombre = editado.getNom_usu();
			if ( !original.getNom_usu().equals(nuevoNombre) ) {
				if (ValidUsername.isValidName(nuevoNombre)) {
					try (ResultSet nombres = selectNom.executeQuery()) {
						if (ValidUsername.usernameExists(nombres, nuevoNombre)) {
							return false;
						}
					}
				} else {
					return false;
				}
			}
			
			insertNom.setString(1, nuevoNombre);
			insertNom.setString(2, editado.getGen_usu());
			insertNom.setString(3, editado.getIns_usu());
			insertNom.setString(4, editado.getFac_usu());
			insertNom.setString(5, editado.getDes_usu());
			insertNom.setBoolean(6, editado.isDis_usu());
			insertNom.setInt(7, editado.getId());
			
			int rsu = insertNom.executeUpdate();
			if (rsu == 0) {
				JOptionPane.showMessageDialog(null, "0 filas afectadas", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return false;
	}
	
}

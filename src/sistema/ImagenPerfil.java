package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImagenPerfil {

	public static String asignarImagen() throws SQLException{
		Connection con = DriverManager.getConnection(Credenciales.DB_URL, Credenciales.DB_USER, Credenciales.DB_PASSWORD);
		String Imagen1 = "SELECT * FROM Imagenes WHERE id_img = 6";
		PreparedStatement selectImagenes = con.prepareStatement(Imagen1);
		ResultSet imagenes = selectImagenes.executeQuery();
		System.out.println(imagenes.getString("path_img"));
		return imagenes.getString("path_img");
	}

}

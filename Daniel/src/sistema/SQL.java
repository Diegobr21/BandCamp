package sistema;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class SQL {
	
	public static void connect() {
	Connection con = null;
	PreparedStatement ptsm= null;
	ResultSet rs = null;
	String usuario = "DanielSal";
	String password = "Avion123";
	String url = "jdbc:sqlserver://localhost:1433;databaseName=Server";
	try {
		con = DriverManager.getConnection(url, usuario, password);
		System.out.println("Connection established");
		String sql = "SELECT id, cor_usu, pas_usu, tip_usu, nom_usu, gen_usu, ins_usu, fac_usu FROM dbo.Usuarios";
		ptsm = con.prepareStatement(sql);
		rs = ptsm.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id"));
			System.out.println(rs.getString("cor_usu"));
			System.out.println(rs.getString("pas_usu"));
			System.out.println(rs.getInt("tip_usu"));
			System.out.println(rs.getString("nom_usu"));
			System.out.println(rs.getString("gen_usu"));
			System.out.println(rs.getString("ins_usu"));
			System.out.println(rs.getString("fac_usu"));
			
		}
	} catch(SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		connect();
	}
}

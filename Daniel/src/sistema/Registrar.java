package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Registrar {
	
	//Metodo "capturar" permite al usuario registrar una nueva cuenta. Por cambiar: TODO.
	public Usuario capturar() throws SQLException {
		String tipoUsuario = " ";
		int tipo = 0;
		boolean valido = false;
		boolean bandera = true;
		Scanner scanner = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String usuario = "DanielSal";
		String password = "Avion123";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Server";
		String sql = "INSERT INTO Usuarios(cor_usu, pas_usu, tip_usu, nom_usu, gen_usu, ins_usu, fac_usu)";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?)";
		String sqlSelect = "SELECT cor_usu FROM Usuarios";
		String sqlSelectNom = "SELECT nom_usu FROM Usuarios";
		
		try {
			con = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexion establecida");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		pstm = con.prepareStatement(sql);
		
		Usuario cuenta = new Usuario(0, null, null, 0, null, null, null, null);
		
		int id = 1;
		cuenta.setId(id);
		
		System.out.println("Ingresa tu correo electronico: ");
		String correo = scanner.nextLine();
		
		try {
			PreparedStatement statement = con.prepareStatement(sqlSelect);
			ResultSet rscorreo = statement.executeQuery();
			while (rscorreo.next() && bandera) {
				if (correo.equals(rscorreo.getString("cor_usu"))) {
					System.out.println("Error: Correo invalido " + correo);
					bandera = false;
				} else {
					InternetAddress email = new InternetAddress(correo);
					email.validate();
					valido = true;
					cuenta.setCor_usu(correo);
					pstm.setString(1, correo);
				}
			}
		} catch (AddressException e) {
			System.out.println("Error: Correo invalido " + correo);
		}
		
		bandera = true;
		
		System.out.println("Ingresa tu contraseña: ");
		String contraseña = scanner.nextLine();
		cuenta.setPas_usu(contraseña);
		pstm.setString(2, contraseña);
		
		//Ciclo while, permite al usuario reingresar los datos en caso de que no se ingrese un valor aceptable.
		while (bandera) {
			System.out.println("Ingresa una opcion: 1.- Artista 2.-Banda");
			tipo = Integer.parseInt(scanner.nextLine());
			//Switch, basicamente convierte el valor entero que ingreso el usuario para que se guarde como un string en otra variable.
			switch (tipo) { 
				case 1:
					tipoUsuario = "Artista";
					bandera = false;
					break;
				case 2:
					tipoUsuario = "Banda";
					bandera = false;
					break;
				default:
					System.out.println("Opcion incorrecta");
			}
		}
		
		bandera = true;
		
		cuenta.setTip_usu(tipo);
		pstm.setInt(3, tipo);
		
		System.out.println("Ingresa tu nombre de usuario: ");
		String nombre = scanner.nextLine();
		cuenta.setNom_usu(nombre);
		pstm.setString(4, nombre);
		
		System.out.println("Ingresa el genero que interpretas: ");
		String genero = scanner.nextLine();
		cuenta.setGen_usu(genero);
		pstm.setString(5, genero);
		
		System.out.println("Que instrumento tocas: ");
		String instrumento = scanner.nextLine();
		cuenta.setIns_usu(instrumento);
		pstm.setString(6, instrumento);
		
		System.out.println("A que facultad perteneces: ");
		String facultad = scanner.nextLine();
		cuenta.setFac_usu(facultad);
		pstm.setString(7, facultad);
		
		int rtdo = pstm.executeUpdate();
		
		if (rtdo == 0) {
			System.out.println("No se pudo crear un nuevo usuario, ninguna columna afectada");
		}
		
		//Se declara e instancia un nuevo objeto de tipo Usuario, despues se utilizan los metodos de acceso para obtener los datos e imprimirlos.
		return cuenta;
	}
	
	//Metodo "imprimir", hace lo que tiene que hacer, imprimir.
	public Usuario imprimir(Usuario cuenta) {
		System.out.println(cuenta.toString());
		return cuenta;
	}
}


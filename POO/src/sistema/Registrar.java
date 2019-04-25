package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import validaciones.MD5;
import validaciones.ValidSignup;

public class Registrar {
	
	/**
	 * Recibe un objeto tipo {@link Usuario} instanciado desde {@link interfaces.Registro} 
	 * para validar sus atributos.
	 * @param nuevaCuenta {@code Usuario} enviado desde la interfaz de {@code Registro}.
	 * @param passwords Arreglo tipo <code>String</code> de las contraseñas ingresadas.
	 * @return <code>true</code> si la cuenta fue creada, de lo contrario <code>false</code>.
	 */
	public boolean checkAccount(Usuario nuevaCuenta, String[] passwords) {
		String correo = nuevaCuenta.getCor_usu(),
				nombre = nuevaCuenta.getNom_usu();
				
		ValidSignup validSignup = new ValidSignup();
		if (validSignup.isFormComplete(new String[] {correo, nombre, passwords[0], passwords[1]})) {
			if (! (validSignup.isValidName(nombre) && validSignup.validEmail(correo) && validSignup.matchPasswords(passwords)) ) {
				return false;
			}
		}
		else return false;
		
		try {
			Connection con = DriverManager.getConnection(DBInfo.url, DBInfo.usuario, DBInfo.password);
			System.out.println("Conexion establecida");
			
			String selectCorreos = "SELECT cor_usu FROM Usuarios";
			PreparedStatement select = con.prepareStatement(selectCorreos);
			ResultSet correos = select.executeQuery();
			while (correos.next()) {
				if ( correo.equals(correos.getString("cor_usu")) ) {
					JOptionPane.showMessageDialog(null, "El correo ingresado ya está registrado.",
							"Correo en uso", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			
			String selectNombres = "SELECT nom_usu FROM Usuarios";
			PreparedStatement selNom = con.prepareStatement(selectNombres);
			ResultSet nombres = selNom.executeQuery();
			if (validSignup.usernameExists(nombres, nombre)) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		imprimir(nuevaCuenta);
		
		return true;
	}
	
	/**
	 * Realiza una conexión a la base de datos para agregar un registro a la tabla. 
	 * @param nuevaCuenta {@code Usuario} con los atributos de la cuenta para ejecutar la actualización.
	 * @param emailCode {@code String} del código de verificación enviado al correo electrónico ingresado.
	 * @return {@code true} si la cuenta se guardó en la base de datos.
	 */
	public boolean createAccount(Usuario nuevaCuenta, String emailCode) {
		// si el código de verificación no coincide, regresar false
		
		try {
			String insertDatos = "INSERT INTO Usuarios(cor_usu, pas_usu, tip_usu, nom_usu, gen_usu, ins_usu, fac_usu, des_usu)";
			insertDatos += "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			Connection con = DriverManager.getConnection(DBInfo.url, DBInfo.usuario, DBInfo.password);
			System.out.println("Conexion establecida");
			PreparedStatement statement = con.prepareStatement(insertDatos);
			String hashed = new MD5().hashPassword(nuevaCuenta.getPas_usu());
			
			statement.setString(1, nuevaCuenta.getCor_usu());
			statement.setString(2, hashed);
			statement.setInt(3, nuevaCuenta.getTip_usu());
			statement.setString(4, nuevaCuenta.getNom_usu());
			statement.setString(5, nuevaCuenta.getGen_usu());
			statement.setString(6, nuevaCuenta.getIns_usu());
			statement.setString(7, nuevaCuenta.getFac_usu());
			statement.setString(8, nuevaCuenta.getDes_usu());
			
			int resultSetRow = statement.executeUpdate();
			if (resultSetRow == 0) {
				JOptionPane.showMessageDialog(null, "Lo sentimos, no fue posible crear tu cuenta.\nVuelve a intentarlo en unos minutos.",
						"Error de servidor", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lo sentimos, no fue posible crear tu cuenta.\nVuelve a intentarlo en unos minutos.",
					"Error de servidor", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		imprimir(nuevaCuenta);
		
		return true;
	}
	
	/**
	 * Método que imprime los atributos de un usuario.
	 * @param cuenta objeto de tipo <code>Usuario</code> que contiene los atributos a imprimir.
	 */
	private void imprimir(Usuario cuenta) {
		System.out.println(cuenta.toString());
	}
}
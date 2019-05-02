package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

import validaciones.MD5;
import validaciones.ValidPassword;
import validaciones.ValidUsername;

public class Registrar {
	
	/**
	 * Recibe un objeto tipo {@link Usuario} instanciado desde {@link interfaces.Registro} 
	 * para validar sus atributos.
	 * @param nuevaCuenta {@code Usuario} enviado desde la interfaz de {@code Registro}.
	 * @param passwords Arreglo tipo <code>String</code> de las contraseñas ingresadas.
	 * @return <code>true</code> si la cuenta fue creada, de lo contrario <code>false</code>.
	 */
	public static boolean checkAccount(Usuario nuevaCuenta, String[] passwords) {
		String correo = nuevaCuenta.getCor_usu();
		String nombre = nuevaCuenta.getNom_usu();
		
		if (isFormComplete(new String[] {correo, nombre, passwords[0], passwords[1]})) {
			if (! (ValidUsername.isValidName(nombre) && validEmail(correo) && matchPasswords(passwords)) ) {
				return false;
			}
		}
		else return false;
		
		String selectCorreos = "SELECT cor_usu FROM Usuarios;";
		String selectNombres = "SELECT nom_usu FROM Usuarios;";
		try ( Connection con = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement select = con.prepareStatement(selectCorreos); 
				PreparedStatement selNom = con.prepareStatement(selectNombres) ) {
			
			System.out.println("Conexion establecida");

			try(ResultSet correos = select.executeQuery()) {
				while (correos.next()) {
					if ( correo.equals(correos.getString("cor_usu")) ) {
						JOptionPane.showMessageDialog(null, "El correo ingresado ya está registrado.",
								"Correo en uso", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}	
			}
			
			try (ResultSet nombres = selNom.executeQuery()) {
				if (ValidUsername.usernameExists(nombres, nombre)) {
					return false;
				}
			}
			
			System.out.println(nuevaCuenta.toString());
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		errorPopup();
		return false;
	}
	
	/**
	 * Realiza una conexión a la base de datos para agregar un registro a la tabla. 
	 * @param nuevaCuenta {@code Usuario} con los atributos de la cuenta para ejecutar la actualización.
	 * @param emailCode {@code String} del código de verificación enviado al correo electrónico ingresado.
	 * @return {@code true} si la cuenta se guardó en la base de datos.
	 */
	public boolean createAccount(Usuario nuevaCuenta, String emailCode) {
		// si el código de verificación no coincide, regresar false
		
		String insertDatos = "INSERT INTO Usuarios(cor_usu, pas_usu, tip_usu, nom_usu, gen_usu, ins_usu, fac_usu, des_usu, dis_usu)";
		insertDatos += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, 1)";
		try ( Connection con = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement statement = con.prepareStatement(insertDatos) ) {
			
			System.out.println("Conexion establecida");
			
			String hashed = MD5.hashPassword(nuevaCuenta.getPas_usu());
			
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
			
			System.out.println(nuevaCuenta.toString());
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		errorPopup();
		return false;
	}
	
	/**
	 * Llama al método {@link validaciones.ValidPassword#isValidPassword} para verificar que la contraseña ingresada
	 * cumpla con los requisitos, después compara si ambas entradas coinciden.
	 * @param passwords Arreglo de tipo <code>String</code> que contiene las contraseñas ingresadas.
	 * @return <code>true</code> si la contraseña cumple con los requisitos y además ambas entradas
	 * coinciden. De lo contrario <code>false</code>.
	 */
	private static boolean matchPasswords(String[] passwords) {
		if (ValidPassword.isValidPassword(passwords[0])) {
			if (passwords[0].equals(passwords[1])) {
				return true;
			} 
			JOptionPane.showMessageDialog(null, "Debes introducir la misma contraseña en ambos campos.",
					"Contraseñas no coinciden", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	/**
	 * Valida que todos los campos del formulario de registro se hayan llenado.
	 * @param textFields Arreglo de tipo <code>String</code> que contiene
	 * los valores de cada campo del formulario.
	 * @return <code>false</code> si algún campo está vacío. Si todos han sido llenados,
	 * retorna <code>true</code>.
	 */
	private static boolean isFormComplete(String[] textFields) {
		for (String string : textFields) {
			if (string.equals("")) {
				JOptionPane.showMessageDialog(null, "Parece que dejaste algún campo vacío.",
						"Formulario incompleto", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Valida que el correo electrónico cumpla con el formato:
	 * <br> correo@dominio.ext
	 * @param correo {@code String} del correo electrónico.
	 * @return {@code true} solo si el correo cumple con el formato.
	 */
	private static boolean validEmail(String correo) {
		try {
			InternetAddress email = new InternetAddress(correo);
			email.validate();
			return true;
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "El correo electrónico no cumple con un formato válido.",
					"Correo no válido" ,JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	private static void errorPopup() {
		JOptionPane.showMessageDialog(null, "Lo sentimos, no fue posible crear tu cuenta.\nVuelve a intentarlo en unos minutos.",
				"Error de servidor", JOptionPane.ERROR_MESSAGE);
	}
}
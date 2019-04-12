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

public class Registrar {
	/**
	 * Recibe los atributos de un objeto tipo {@link Usuario} desde la interfaz de registro 
	 * para validar la información, si es correcta instanciar uno y enviarlo 
	 * a un nuevo registro en la base de datos.
	 * @param correo <code>String</code> del correo electrónico.
	 * @param password Arreglo tipo <code>String</code> de las contraseñas ingresadas.
	 * @param tipo <code>int</code> del tipo de usuario (artista = 1 ; banda = 2).
	 * @param nombre <code>String</code> del nombre de usuario.
	 * @param genero <code>String</code> del género musical.
	 * @param instrumento <code>String</code> del instrumento.
	 * @param facultad <code>String</code> de la facultad.
	 * @return <code>true</code> si la cuenta fue creada, de lo contrario <code>false</code>.
	 * @throws SQLException 
	 * @see validaciones.MD5#hashPassword(String)
	 */
	public boolean createAccount(int id, String correo, String[] passwords, int tipo,
			String nombre, String genero, String instrumento, String facultad) throws SQLException {
		
		Usuario cuenta = new Usuario(id, correo, passwords[0], tipo, nombre, genero, instrumento, facultad, "");
		
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		String usuario = "DanielSal";
		String password = "Avion123";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Server";
		String insertDatos = "INSERT INTO Usuarios(cor_usu, pas_usu, tip_usu, nom_usu, gen_usu, ins_usu, fac_usu)";
		insertDatos += "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexion establecida");
			statement = con.prepareStatement(insertDatos);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		String selectCorreos = "SELECT cor_usu FROM Usuarios";
		
		try {
			PreparedStatement select = con.prepareStatement(selectCorreos);
			ResultSet correos = select.executeQuery();
			
			InternetAddress email = new InternetAddress(correo);
			email.validate();
			
			while (correos.next()) {
				if (correo.equals(correos.getString("cor_usu"))) {
					JOptionPane.showMessageDialog(null, "Correo invalido",
							"El correo que usted ingreso ya esta registrado", JOptionPane.ERROR_MESSAGE );
					break;
				}
				cuenta.setCor_usu(correo);
				statement.setString(1, correo);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR!",
					"La fila no fue afectada", JOptionPane.ERROR_MESSAGE );
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "Correo invalido",
					"El correo que usted ingreso no cumple con las caracteristicas", JOptionPane.ERROR_MESSAGE );
		}
		
		if (isFormComplete(new String[] {correo, nombre, passwords[0], passwords[1]})) {
			if (matchPasswords(passwords)) {
				MD5 hasher = new MD5();
				passwords[1] = hasher.hashPassword(passwords[0]);
				cuenta.setPas_usu(passwords[0]);
				statement.setString(2, passwords[0]);
			}
			else return false;
		}
		else return false;
		
		cuenta.setTip_usu(tipo);
		statement.setInt(3, tipo);
		
		cuenta.setNom_usu(nombre);
		statement.setString(4, nombre);
		
		cuenta.setGen_usu(genero);
		statement.setString(5, genero);
		
		cuenta.setIns_usu(instrumento);
		statement.setString(6, instrumento);
		
		cuenta.setFac_usu(facultad);
		statement.setString(7, facultad);
		
		// query para obtener ID más reciente
		
		int resultSetRow = statement.executeUpdate();
		
		if (resultSetRow == 0) {
			JOptionPane.showMessageDialog(null, "0 filas afectadas", "ERROR", JOptionPane.ERROR_MESSAGE );
		}
		imprimir(cuenta);
		// enviar objeto newAccount a un registro de la base de datos
		return true;
	}
	
	/**
	 * Llama al método {@link validaciones.ValidPassword#isValidPassword} para verificar que la contraseña ingresada
	 * cumpla con los requisitos, después compara si ambas entradas coinciden.
	 * @param passwords Arreglo de tipo <code>String</code> que contiene las contraseñas ingresadas.
	 * @return <code>true</code> si la contraseña cumple con los requisitos y además ambas entradas
	 * coinciden. De lo contrario <code>false</code>.
	 */
	private boolean matchPasswords(String[] passwords) {
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
	private boolean isFormComplete(String[] textFields) {
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
	 * Método que imprime los atributos de un usuario.
	 * @param cuenta objeto de tipo <code>Usuario</code> que contiene los atributos a imprimir.
	 */
	private void imprimir(Usuario cuenta) {
		System.out.println(cuenta.toString());
	}
}
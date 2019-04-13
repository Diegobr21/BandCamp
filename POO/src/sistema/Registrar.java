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
		
		ValidSignup validSignup = new ValidSignup();
		if (validSignup.isFormComplete(new String[] {correo, nombre, passwords[0], passwords[1]})) {
			if (validSignup.validEmail(correo) && validSignup.matchPasswords(passwords)) {
				MD5 hasher = new MD5();
				passwords[0] = hasher.hashPassword(passwords[0]);
			}
			else return false;
		}
		else return false;
		
		Connection con = null;
		PreparedStatement statement = null;
		
		String insertDatos = "INSERT INTO Usuarios(cor_usu, pas_usu, tip_usu, nom_usu, gen_usu, ins_usu, fac_usu)";
		insertDatos += "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DriverManager.getConnection(DBInfo.url);
			System.out.println("Conexion establecida");
			statement = con.prepareStatement(insertDatos);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		String selectCorreos = "SELECT cor_usu FROM Usuarios";
		try {
			PreparedStatement select = con.prepareStatement(selectCorreos);
			ResultSet correos = select.executeQuery();
			
			while (correos.next()) {
				if ( correo.equals(correos.getString("cor_usu")) ) {
					JOptionPane.showMessageDialog(null, "El correo ingresado ya esta registrado.",
							"Correo en uso", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR!",
					"La fila no fue afectada", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		statement.setString(1, correo);
		statement.setString(2, passwords[0]);
		statement.setInt(3, tipo);
		statement.setString(4, nombre);
		statement.setString(5, genero);
		statement.setString(6, instrumento);
		statement.setString(7, facultad);
		
		int resultSetRow = statement.executeUpdate();
		if (resultSetRow == 0) {
			JOptionPane.showMessageDialog(null, "0 filas afectadas", "ERROR", JOptionPane.ERROR_MESSAGE );
		}
		
		imprimir(cuenta);
		
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
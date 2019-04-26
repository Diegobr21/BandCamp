package sistema;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contiene los atributos que se muestran en el muro y en el perfil de la cuenta filtrada.
 */
public class CuentaFiltrada {
	private int tipo;
	private String nombre, genero, instrumento, facultad, descripcion;
	
	/**
	 * Construye la {@code CuentaFiltrada} con cada atributo especificado.
	 */
	public CuentaFiltrada(int tipo, String nombre, String genero, String instrumento, String facultad, String descripcion) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.genero = genero;
		this.instrumento = instrumento;
		this.facultad = facultad;
		this.descripcion = descripcion;
	}
	
	/**
	 * Construye la {@code CuentaFiltrada} a partir de el resultado de una consulta a la base de datos.
	 * @param cuenta {@code ResultSet} del cual se usarán solo los atributos que se mostrarán.
	 * @throws SQLException 
	 */
	public CuentaFiltrada(ResultSet cuenta) throws SQLException {
		this.tipo = cuenta.getInt("tip_usu");
		this.nombre = cuenta.getString("nom_usu");
		this.genero = cuenta.getString("gen_usu");
		this.instrumento = cuenta.getString("ins_usu");
		this.facultad = cuenta.getString("fac_usu");
		this.descripcion = cuenta.getString("des_usu");
	}
	
	/**
	 * Construye la {@code CuentaFiltrada} a partir de una instancia de {@code Usuario}.
	 * @param usuario {@code Usuario} con los atributos que se copiaran a la instancia de {@code CuentaFiltrada}.
	 * @see {@link Usuario}.
	 */
	public CuentaFiltrada(Usuario usuario) {
		this.tipo = usuario.getTip_usu();
		this.nombre = usuario.getNom_usu();
		this.genero = usuario.getGen_usu();
		this.instrumento = usuario.getIns_usu();
		this.facultad = usuario.getFac_usu();
		this.descripcion = usuario.getDes_usu();
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

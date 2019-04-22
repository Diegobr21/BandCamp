package sistema;

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
	 * Construye la {@code CuentaFiltrada} a partir de una instancia de tipo {@code Usuario}.
	 * @param cuenta {@code Usuario} del cual se usarán solo los atributos de la clase {@code CuentaFiltrada}.
	 */
	public CuentaFiltrada(Usuario cuenta) {
		this.tipo = cuenta.getTip_usu();
		this.nombre = cuenta.getNom_usu();
		this.genero = cuenta.getGen_usu();
		this.instrumento = cuenta.getIns_usu();
		this.facultad = cuenta.getFac_usu();
		this.descripcion = cuenta.getDes_usu();
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

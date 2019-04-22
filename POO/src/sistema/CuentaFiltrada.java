package sistema;

/**
 * Contiene los atributos que se muestran en el muro y en el perfil de la cuenta filtrada.
 */
public class CuentaFiltrada {
	public int tipo;
	public String nombre, genero, instrumento, facultad;
	
	/**
	 * Construye la {@code CuentaFiltrada} con cada atributo especificado.
	 */
	public CuentaFiltrada(int tipo, String nombre, String genero, String instrumento, String facultad) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.genero = genero;
		this.instrumento = instrumento;
		this.facultad = facultad;
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
	}
}

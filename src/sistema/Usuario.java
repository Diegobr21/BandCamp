package sistema;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene los atributos de una cuenta. Cuenta con métodos de acceso.
 */
public class Usuario {
	private int id;
	private String cor_usu;
	private String pas_usu;
	private int tip_usu;
	private String nom_usu;
	private String gen_usu;
	private String ins_usu;
	private String fac_usu;
	private String des_usu;
	
	public Usuario(int id ,String cor_usu, String pas_usu, int tip_usu, String nom_usu, String gen_usu, String ins_usu, String fac_usu, String des_usu) {
		this.id = id;
		this.cor_usu = cor_usu;
		this.pas_usu = pas_usu;
		this.tip_usu = tip_usu;
		this.nom_usu = nom_usu;
		this.gen_usu = gen_usu;
		this.ins_usu = ins_usu;
		this.fac_usu = fac_usu;
		this.des_usu = des_usu;
	}
	
	/**
	 * Construye un {@code Usuario} a partir del resultado de una consulta a la tabla de la base de datos.
	 * @param resultSet {@code ResultSet} que almacena la respuesta de la consulta, de la cual se obtendrán los datos de cada campo.
	 * @throws SQLException
	 */
	Usuario(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("id_usu");
		this.cor_usu = resultSet.getString("cor_usu");
		this.pas_usu = resultSet.getString("pas_usu");
		this.tip_usu = resultSet.getInt("tip_usu");
		this.nom_usu = resultSet.getString("nom_usu");
		this.gen_usu = resultSet.getString("gen_usu");
		this.ins_usu = resultSet.getString("ins_usu");
		this.fac_usu = resultSet.getString("fac_usu");
		this.des_usu = resultSet.getString("des_usu");
	}
	
	public String toString(){
		return id + " / " + cor_usu + " / " + pas_usu + " / " + tip_usu + " / " + nom_usu + " / " +
				gen_usu + " / " + ins_usu + " / " + fac_usu + " / " + des_usu;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCor_usu() {
		return cor_usu;
	}
	public void setCor_usu(String cor_usu) {
		this.cor_usu = cor_usu;
	}
	
	public String getPas_usu() {
		return pas_usu;
	}
	public void setPas_usu(String pas_usu) {
		this.pas_usu = pas_usu;
	}
	
	public int getTip_usu() {
		return tip_usu;
	}
	public void setTip_usu(int tip_usu) {
		this.tip_usu = tip_usu;
	}
	
	public String getNom_usu() {
		return nom_usu;
	}
	public void setNom_usu(String nom_usu) {
		this.nom_usu = nom_usu;
	}
	
	public String getGen_usu() {
		return gen_usu;
	}
	public void setGen_usu(String gen_usu) {
		this.gen_usu = gen_usu;
	}
	
	public String getIns_usu() {
		return ins_usu;
	}
	public void setIns_usu(String ins_usu) {
		this.ins_usu = ins_usu;
	}
	
	public String getFac_usu() {
		return fac_usu;
	}
	public void setFac_usu(String fac_usu) {
		this.fac_usu = fac_usu;
	}
	
	public String getDes_usu() {
		return des_usu;
	}
	public void setDes_usu(String des_usu) {
		this.des_usu = des_usu;
	}
}

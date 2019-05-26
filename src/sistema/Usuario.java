package sistema;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que contiene los atributos de una cuenta. Cuenta con m�todos de acceso.
 */
public class Usuario implements Serializable {
	private int id;
	private String cor_usu;
	private String pas_usu;
	private int tip_usu;
	private String nom_usu;
	private String gen_usu;
	private String ins_usu;
	private String fac_usu;
	private String des_usu;
	private boolean dis_usu;
	private String con_usu;
//	private int img_usu;
	
	public Usuario(int id, String cor_usu, String pas_usu, int tip_usu, String nom_usu, String gen_usu, String ins_usu,
		String fac_usu, String des_usu, boolean dis_usu, String con_usu) {
	this.id = id;
	this.cor_usu = cor_usu;
	this.pas_usu = pas_usu;
	this.tip_usu = tip_usu;
	this.nom_usu = nom_usu;
	this.gen_usu = gen_usu;
	this.ins_usu = ins_usu;
	this.fac_usu = fac_usu;
	this.des_usu = des_usu;
	this.dis_usu = dis_usu;
	this.con_usu = con_usu;
//	this.img_usu = img_usu;
}

	/**
	 * Construye un {@code Usuario} a partir del resultado de una consulta a la tabla de la base de datos.
	 * @param resultSet {@code ResultSet} que almacena la respuesta de la consulta, de la cual se obtendr�n los datos de cada campo.
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
		this.dis_usu = resultSet.getBoolean("dis_usu");
		this.con_usu = resultSet.getString("con_usu");
//		this.img_usu = resultSet.getInt("img_usu");
	}
	
	public String toString() {
		return "Usuario [id=" + id + ", cor_usu=" + cor_usu + ", pas_usu=" + pas_usu + ", tip_usu=" + tip_usu
				+ ", nom_usu=" + nom_usu + ", gen_usu=" + gen_usu + ", ins_usu=" + ins_usu + ", fac_usu=" + fac_usu
				+ ", des_usu=" + des_usu + ", dis_usu=" + dis_usu + ", con_usu=" + con_usu + "]";
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

	public boolean isDis_usu() {
		return dis_usu;
	}
	public void setDis_usu(boolean dis_usu) {
		this.dis_usu = dis_usu;
	}

	public String getCon_usu() {
		return con_usu;
	}

	public void setCon_usu(String con_usu) {
		this.con_usu = con_usu;
	}

//	public int getImg_usu() {
//		return img_usu;
//	}
//
//	public void setImg_usu(int img_usu) {
//		this.img_usu = img_usu;
//	}
}

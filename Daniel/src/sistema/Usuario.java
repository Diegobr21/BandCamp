package sistema;

public class Usuario {
	private int id;
	private String cor_usu;
	private String pas_usu;
	private int tip_usu;
	private String nom_usu;
	private String gen_usu;
	private String ins_usu;
	private String fac_usu;
	
	public Usuario(int id, String cor_usu, String pas_usu, int tip_usu, String nom_usu, String gen_usu, String ins_usu, String fac_usu) {
		this.id = id;
		this.cor_usu = cor_usu;
		this.pas_usu = pas_usu;
		this.tip_usu = tip_usu;
		this.nom_usu = nom_usu;
		this.gen_usu = gen_usu;
		this.ins_usu = ins_usu;
		this.fac_usu = fac_usu;
	}
	
	public String toString(){
		return id + " / " + cor_usu + " / " + pas_usu + " / " + tip_usu + " / " + nom_usu + " / " + gen_usu + " / " + ins_usu + " / " + fac_usu;
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
	
}

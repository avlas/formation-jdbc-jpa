package fr.codevallee.formation.tp1.model;

public class Plat {

	private int id;
	private String name;
	private int tarif;
	
	
	
	/**
	 * @param name
	 * @param tarif
	 */
	public Plat(String name, int tarif) {
		super();
		this.name = name;
		this.tarif = tarif;
	}

	/**
	 * @param name
	 * @param tarif
	 */
	public Plat(int id, String name, int tarif) {
		super();
		this.id = id;
		this.name = name;
		this.tarif = tarif;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTarif() {
		return tarif;
	}
	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

	@Override
	public String toString() {
		return "Plat [name=" + name + ", tarif=" + tarif + "]";
	}	
	
}

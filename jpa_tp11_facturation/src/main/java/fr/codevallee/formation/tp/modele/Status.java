package fr.codevallee.formation.tp.modele;

public enum Status {
	PAYED("payed"), 
	NOT_PAYED("not payed");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
}

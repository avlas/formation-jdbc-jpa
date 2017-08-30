package fr.formation.jpa.tp11.model;

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

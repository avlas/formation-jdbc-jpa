package fr.codevallee.formation.tp.modele;

public enum AddressType {
	BILLING("billing"), 
	DELIVERY("delivery");

	private String value;

	private AddressType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
}

package fr.formation.jpa.tp11.model;

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

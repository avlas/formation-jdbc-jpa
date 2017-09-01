package fr.formation.jpa.tp11.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private Integer number;

	@Column
	private String street;

	@Column
	private Integer postalCode;

	@Column
	private AddressType type;

	// @OneToMany
	// private Set<Facture> facture;
	//
	// @OneToMany
	// private Set<Client> client = new HashSet<Client>();

	public Address() {}
	
	/**
	 * @param number
	 * @param street
	 * @param postalCode
	 * @param type
	 */
	public Address(Integer number, String street, Integer postalCode, AddressType type) {
		super();
		this.number = number;
		this.street = street;
		this.postalCode = postalCode;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}
	// public Client getClient() {
	// return client;
	// }
	// public void setClient(Client client) {
	// this.client = client;
	// }
	// public Facture getFacture() {
	// return facture;
	// }
	// public void setFacture(Facture facture) {
	// this.facture = facture;
	// }

	@Override
	public String toString() {
		return "[id=" + this.getId() + ", number=" + this.getNumber() + ", street=" + this.getStreet() + ", postalCode="
				+ this.getPostalCode() + ", type=" + this.getType().getValue() + "]";
	}

}

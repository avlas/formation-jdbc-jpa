package fr.codevallee.formation.tp.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private Integer number;
	
	private String street;
	
	private Integer postalCode;
	
	private AddressType type;
	
//	@OneToMany
//	private Set<Facture> facture;
//		
//	@OneToMany
//	private Set<Client> client = new HashSet<Client>();
	
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
//	public Client getClient() {
//		return client;
//	}
//	public void setClient(Client client) {
//		this.client = client;
//	}
//	public Facture getFacture() {
//		return facture;
//	}
//	public void setFacture(Facture facture) {
//		this.facture = facture;
//	}

}

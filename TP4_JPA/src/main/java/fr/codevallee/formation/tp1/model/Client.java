package fr.codevallee.formation.tp1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String firstname;
	private String lastname;
	
	/**
	 * @param firstname
	 * @param lastname
	 */
	public Client(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setTarif(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Plat [firstname=" + firstname + ", lastname=" + lastname + "]";
	}	
	
}

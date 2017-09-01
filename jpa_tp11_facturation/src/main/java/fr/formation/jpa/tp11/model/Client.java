package fr.formation.jpa.tp11.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_CLIENT_BILLING_ADDRESS_ID"))
	private Address billingAddress;

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_CLIENT_DELIVERY_ADDRESS_ID"))
	private Address deliveryAddress;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<Bill> bills;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirtname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		String strClient = "[id=" + this.getId() + 
				", firstname=" + this.getFirtname() + 
				", lastname=" + this.getLastname() + 
				", billingAddress=" + this.getBillingAddress() + 
				", deliveryAddress=" + this.getDeliveryAddress();
		
//		if (this.getBills() != null) {
//			for (Bill bill : this.getBills()) {
//				strClient += ", \nbill=" + bill.toString();
//			}
//		}
		strClient += "]";

		return strClient;
	}
}

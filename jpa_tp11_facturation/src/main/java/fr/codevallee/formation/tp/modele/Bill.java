package fr.codevallee.formation.tp.modele;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany
	private Set<BillLine> billLines = new HashSet<BillLine>();

	@ManyToOne
	private Client client;

	private Date invoiceDate;

	private Status status;
	
	public Integer calculateBillTotal() {	
		int billTotal = 0;
		for (BillLine billLine : billLines) {
			billTotal += billLine.calculateLineTotal();
		}
		return Integer.valueOf(billTotal);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<BillLine> getBillLines() {
		return billLines;
	}

	public void setBillLines(Set<BillLine> billLines) {
		this.billLines = billLines;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}

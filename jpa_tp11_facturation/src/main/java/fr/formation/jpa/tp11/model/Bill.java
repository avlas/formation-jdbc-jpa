package fr.formation.jpa.tp11.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Bill.findByStatus", query = "SELECT b FROM Bill b WHERE b.status = :status") })
@Entity
@Table(name = "bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_BILL_BILLLINES_ID"))
	private Set<BillLine> billLines = new HashSet<BillLine>();

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_BILL_CLIENT_ID"))
	private Client client;

	@Column
	private Date invoiceDate;

	@Column
	private Status status;

	public Bill() {}	
	
	/**
	 * @param billLines
	 * @param client
	 * @param invoiceDate
	 * @param status
	 */
	public Bill(Date invoiceDate, Status status, Client client, Set<BillLine> billLines) {
		super();
		this.invoiceDate = invoiceDate;
		this.status = status;
		this.client = client;
		this.billLines = billLines;	
	}
	
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

	@Override
	public String toString() {
		String billStr = "\n [id=" + this.getId() +
						", invoiceDate=" + this.getInvoiceDate() + 
						", status=" + this.getStatus().getValue() +
						", total=" + this.calculateBillTotal() +
					    ", \n client=" + this.getClient();

		if (this.getBillLines() != null) {
			for (BillLine billLine : this.getBillLines()) {
				billStr += ", \n billLines=" + billLine.toString();
			}
		}
		billStr += "]";

		return billStr;
	}
}

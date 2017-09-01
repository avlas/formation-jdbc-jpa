package fr.formation.jpa.tp11.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private Integer price;
	
	@Column
	private String reference;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="FK_ARTICLE_DESCRTIPTION_ID"))
	public Description description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[id=" + this.getId() + 
			 ", price=" + this.getPrice() + 
			 ", reference=" + this.getReference() + 
			 ", description=" + this.getDescription() + 
			 "]";
	}
	
}

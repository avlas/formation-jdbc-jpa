package fr.formation.jpa.tp11.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bill_line")
public class BillLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name="FK_BILLLINE_ARTICLE_ID"))
	private Article article;

	@Column
	private Integer numberOfArticles;

	// @ManyToOne
	// private Bill bill;

	public Integer calculateLineTotal() {
		return (numberOfArticles * article.getPrice());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getNumberOfArticles() {
		return numberOfArticles;
	}

	public void setNumberOfArticles(Integer numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	@Override
	public String toString() {
		return "[id=" + this.getId() + 
			 ", article=" + this.getArticle() + 
			 ", numberOfArticles=" + this.getNumberOfArticles() + 
			 ", total line=" + this.calculateLineTotal() + "]";
	}

}

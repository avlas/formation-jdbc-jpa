package fr.codevallee.formation.tp.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BillLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	private Article article;

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
		return "BillLine [id=" + id + ", article=" + article + ", numberOfArticles=" + numberOfArticles + ", total line=" + this.calculateLineTotal() + "]";
	}

}

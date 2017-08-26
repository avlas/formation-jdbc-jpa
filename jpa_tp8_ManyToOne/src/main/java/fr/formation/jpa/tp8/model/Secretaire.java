package fr.formation.jpa.tp8.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="secretaire")
public class Secretaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="FK_SECRETAIRE_MAIRE_ID"))
	private Maire maire ;

	public Long getId() {
		return id;
	}

	public Maire getMaire() {
		return maire;
	}

	public void setMaire(Maire maire) {
		this.maire = maire;
	}

}

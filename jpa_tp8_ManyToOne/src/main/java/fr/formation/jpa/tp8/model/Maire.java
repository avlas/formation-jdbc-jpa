package fr.formation.jpa.tp8.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Maire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 40)
	private String nom;

	@OneToOne(mappedBy="maire")
	@JoinColumn(foreignKey = @ForeignKey(name="FK_MAIRE_COMMUNE_ID"))
	private Commune commune;
	
	@OneToMany
	@JoinColumn(foreignKey = @ForeignKey(name="FK_MAIRE_ELU_ID"))
	private Set<Elu> elu = new HashSet<Elu>() ;

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	public Set<Elu> getElu() {
		return elu;
	}

	public void setElu(Set<Elu> elu) {
		this.elu = elu;
	}

}

package fr.formation.jpa.tp9.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="elu")
public class Elu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 40)
	private String nom;
	
	@ManyToMany
	private Set <Projet> projets;
	
	@ManyToOne
	private Maire maire;
	
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Set<Projet> projets) {
		this.projets = projets;
	}
	
}

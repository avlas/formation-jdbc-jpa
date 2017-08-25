package fr.formation.jpa.tp4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerInstance {
	
	public static EntityManager getInstance() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("election");
		return entityManagerFactory.createEntityManager();
	}	
}

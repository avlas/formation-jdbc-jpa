package fr.codevallee.formation.tp1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.codevallee.formation.tp1.model.Client;

public class EntityManagerInstance {
	
	public static EntityManager getInstance() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("formation");
		return entityManagerFactory.createEntityManager();
	}		
	
	public void insert(Client client) {
		EntityManager entityManager = EntityManagerInstance.getInstance();
		
		entityManager.getTransaction().begin();
		entityManager.persist(client);		// <- il prend l'object => il trouve auto-increment sur l'Obj -> il auto-increment l'index
		entityManager.getTransaction().commit();
	}
}

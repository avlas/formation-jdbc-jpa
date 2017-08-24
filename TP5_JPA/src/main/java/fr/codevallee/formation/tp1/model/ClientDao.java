package fr.codevallee.formation.tp1.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.codevallee.formation.tp1.model.Client;

public class ClientDao {
	private static EntityManager entityManager = getInstance();
	
	public static EntityManager getInstance() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("formation");
		return entityManagerFactory.createEntityManager();
	}

	public List<Client> findAll() {
		TypedQuery<Client> query = entityManager.createQuery("from Client", Client.class);

		if (!query.getResultList().isEmpty()) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	public void insert(Client client) {		
		entityManager.getTransaction().begin();
		entityManager.persist(client); // <- il prend l'object => il trouve
										// auto-increment sur l'Obj -> il
										// auto-increment l'index
		entityManager.getTransaction().commit();
	}

	public void update(int id, String firstname, String lastname, int age) {
		Client client = entityManager.find(Client.class, Integer.valueOf(id));

		entityManager.getTransaction().begin();
		client.setFirstname(firstname);
		client.setLastname(lastname);
		client.setAge(age);

		entityManager.getTransaction().commit();
	}

	public void delete(int id) {
		Client client = entityManager.find(Client.class, Integer.valueOf(id));
	
		entityManager.getTransaction().begin();
		entityManager.remove(client);
		entityManager.getTransaction().commit();
	}
}

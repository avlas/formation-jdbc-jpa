package fr.formation.jpa.tp11.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class FacturationRepository {

	@PersistenceContext(unitName = "facturation")
	private EntityManager entityManager;

	public static EntityManager getEMInstance() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("facturation");
		return entityManagerFactory.createEntityManager();
	}	
	
	@Transactional
	public <T> void insert(T t) {
	//	entityManager.getTransaction().begin();
		entityManager.persist(t);
	//	entityManager.getTransaction().commit();
	}

	public <T> T findbyId(Class<T> model, int id) {
		return entityManager.find(model, Integer.valueOf(id));
	}

	public <T> List<T> findAll(String stmt, Class<T> model) {
		TypedQuery<T> query = entityManager.createQuery(stmt, model);
		return query.getResultList();
	}

	public <T> void delete(Class<T> model, int id) {
		T t = entityManager.find(model, Integer.valueOf(id));

		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	public <T> void update(Class<T> model, int id, T toUpdate) {
		T t = entityManager.find(model, Integer.valueOf(id));

		// entityManager.getTransaction().begin();
		//
		//// if(toUpdate.instanceOf(Client.class)) {
		//// t.setFirstname(toUpdate);
		//// t.setLastname(lastname);
		//// t.setAge(age);
		//// }
		// entityManager.merge(t);
		// entityManager.getTransaction().commit();
	}

}

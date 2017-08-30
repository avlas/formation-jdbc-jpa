package fr.codevallee.formation.tp.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class FacturationRepository implements IRepository {

	@PersistenceContext(unitName = "facturation")
	private EntityManager entityManager;

	@Override
	public <T> void insert(T t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public <T> T findbyId(Class<T> model, int id) {
		return entityManager.find(model, Integer.valueOf(id));
	}

	@Override
	public <T> List<T> findAll(String stmt, Class<T> model) {
		TypedQuery<T> query = entityManager.createQuery(stmt, model);
		return query.getResultList();
	}

	@Override
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

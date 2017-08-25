package fr.codevallee.formation.tp.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import fr.codevallee.formation.tp.modele.Mairie;

@Repository
public class MairieRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Mairie mairie) {
		entityManager.persist(mairie);
		System.out.println( "MairieRepository = " + TransactionSynchronizationManager.isActualTransactionActive());
	}

}

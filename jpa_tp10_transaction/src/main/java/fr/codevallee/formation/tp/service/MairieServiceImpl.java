package fr.codevallee.formation.tp.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import fr.codevallee.formation.tp.modele.Mairie;
import fr.codevallee.formation.tp.repositories.MairieRepository;

@Component
public class MairieServiceImpl {

	@Autowired
	private MairieRepository mairieRepository;

	@Transactional // JPA
	//@org.springframework.transaction.annotation.Transactional() // Spring
	public void ajouterMairie(Map<String, Object> attributes) {

		Mairie mairie = new Mairie();
		mairie.setNom("transaction");

		mairieRepository.save(mairie);

		// int i = 1 / 0;

		System.out.println("MairieServiceImpl = " + TransactionSynchronizationManager.isActualTransactionActive());

	}

}

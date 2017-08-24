package fr.codevallee.formation.tp1.repositories;

import java.util.List;

import fr.codevallee.formation.tp1.model.Client;

public interface IClientRepository {

	List<Client> findAll();
	
	void insert(Client client);
	
	void update(int id, String firstname, String lastname, int age);
	
	void delete(int id);
}

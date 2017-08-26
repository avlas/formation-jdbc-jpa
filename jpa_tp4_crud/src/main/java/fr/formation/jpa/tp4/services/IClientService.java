package fr.formation.jpa.tp4.services;

import java.util.List;

import fr.formation.jpa.tp4.model.Client;

public interface IClientService {

	List<Client> findAll();
	
	void insert(Client client);
	
	void update(int id, String firstname, String lastname, int age);
	
	void delete(int id);
}

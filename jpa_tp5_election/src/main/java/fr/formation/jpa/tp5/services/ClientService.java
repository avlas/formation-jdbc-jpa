package fr.formation.jpa.tp5.services;

import java.util.List;

import fr.formation.jpa.tp5.model.Client;
import fr.formation.jpa.tp5.repositories.ClientRepository;

public class ClientService implements IClientService {

	private static ClientRepository clientRepository = new ClientRepository();
	
	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public void insert(Client client) {
		clientRepository.insert(client);
	}

	@Override
	public void update(int id, String firstname, String lastname, int age) {
		clientRepository.update(id, firstname, lastname, age);		
	}

	@Override
	public void delete(int id) {
		clientRepository.delete(id);	
	}

}

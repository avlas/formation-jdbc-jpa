package fr.codevallee.formation.tp1.repositories;

import java.util.List;

import fr.codevallee.formation.tp1.dao.Client;
import fr.codevallee.formation.tp1.dao.ClientDao;

public class ClientRepository implements IClientRepository {

	private static ClientDao clientDao = new ClientDao();
	
	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

	@Override
	public void insert(Client client) {
		clientDao.insert(client);
	}

	@Override
	public void update(int id, String firstname, String lastname, int age) {
		clientDao.update(id, firstname, lastname, age);		
	}

	@Override
	public void delete(int id) {
		clientDao.delete(id);	
	}

}

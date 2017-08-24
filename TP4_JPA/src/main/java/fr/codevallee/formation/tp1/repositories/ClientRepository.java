package fr.codevallee.formation.tp1.repositories;

import java.util.List;

import fr.codevallee.formation.tp1.dao.Client;
import fr.codevallee.formation.tp1.dao.ClientDao;

public class ClientRepository implements IClientRepository {

	private static ClientDao entityManagerDao = new ClientDao();
	
	@Override
	public List<Client> findAll() {
		return entityManagerDao.findAll();
	}

	@Override
	public void insert(Client client) {
		entityManagerDao.insert(client);
	}

	@Override
	public void update(int id, String firstname, String lastname, int age) {
		entityManagerDao.update(id, firstname, lastname, age);		
	}

	@Override
	public void delete(int id) {
		entityManagerDao.delete(id);	
	}

}

package fr.formation.jpa.tp11.repositories;

import java.util.List;

public interface IRepository {
	
	<T> List<T> findAll (String stmt, Class<T> model);
	
	<T> T findById (Class<T> model, int id);
	
	<T> void insert(T t);
	
	<T> void update(Class<T> model, int id, T t);
	
	<T> void delete(Class<T> model, int id);
}

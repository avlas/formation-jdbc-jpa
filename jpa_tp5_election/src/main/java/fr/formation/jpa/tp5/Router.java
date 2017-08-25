package fr.formation.jpa.tp5;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.formation.jpa.tp5.model.Client;
import fr.formation.jpa.tp5.model.Commune;
import fr.formation.jpa.tp5.model.Maire;
import fr.formation.jpa.tp5.repositories.ClientRepository;
import fr.formation.jpa.tp5.services.ClientService;
import fr.formation.jpa.tp5.services.IClientService;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {
	IClientService repo = new ClientService();

	public static List<Client> clients = new ArrayList<Client>();
	Map<String, Object> attributes = new HashMap<>();
		
	public void init() {

		get("/persist", (request, response) -> {
			
			Maire maire1 = new Maire();
			maire1.setNom("Maire1");
			
			Commune commune1 = new Commune();
			commune1.setNom("Commune1");
			commune1.setMaire(maire1);
			
			EntityManager em = ClientRepository.getInstance();
			em.getTransaction().begin();
			em.persist(commune1); 
			em.getTransaction().commit();
			
			TypedQuery<Commune> query = em.createQuery("from Communes", Commune.class);
		//	TypedQuery<Maire> query = em.createQuery("from Maire", Maire.class);
			
			Map<String, Object> attributes = new HashMap<>();		
			attributes.put("communes", query.getResultList());
			
//			for (Maire maire : query.getResultList()) {
//				System.out.println(maire.getNom() + ", "+ maire.getCommune() );
//			}
			
			return new ModelAndView(attributes, "listCommunes.ftl");
		}, getFreeMarkerEngine());
		
		// http://localhost:9999/list
		get("/list", (request, response) -> {
			
			attributes = getClientsMap(); 	
			return new ModelAndView(attributes, "listClients.ftl");
		}, getFreeMarkerEngine());

		// http://localhost:9999/add
		post("/addClient", (request, response) -> {
			return new ModelAndView(attributes, "add.ftl");	
		}, getFreeMarkerEngine());

		// http://localhost:9999/added?firstname=?&lastname=?&age=?
		post("/add", (request, response) -> {
			String firstname = request.queryParams("firstname");
			String lastname = request.queryParams("lastname");
			int age = Integer.valueOf(request.queryParams("age"));

			Client client = new Client(firstname, lastname, age);
			repo.insert(client);

			attributes = getClientsMap();				
			return new ModelAndView(attributes, "listClients.ftl");
			
		}, getFreeMarkerEngine());

		// http://localhost:9999/update?id=?
		get("/update", (request, response) -> {
			int id = Integer.valueOf(request.queryParams("id"));
			
			attributes.clear();
			attributes.put("idUser", id);	
			
			return new ModelAndView(attributes, "update.ftl");
		}, getFreeMarkerEngine());

		// http://localhost:9999/updated?id=?&firstname=?&lastname=?&age=?
		get("/updated", (request, response) -> {
			int id = Integer.valueOf(request.queryParams("id"));			
			String firstname = request.queryParams("firstname");
			String lastname = request.queryParams("lastname");
			int age = Integer.valueOf(request.queryParams("age"));

			repo.update(id, firstname, lastname, age);

			attributes = getClientsMap();	
			
			return new ModelAndView(attributes, "listClients.ftl");
		}, getFreeMarkerEngine());
		
		// http://localhost:9999/delete?id=?
		get("/delete", (request, response) -> {
			int id = Integer.valueOf(request.queryParams("id"));
			
			repo.delete(id);
			
			attributes = getClientsMap();

			return new ModelAndView(attributes, "listClients.ftl");
		}, getFreeMarkerEngine());

	}

	protected Map<String, Object> getClientsMap() {
		attributes.clear();
		
		clients = repo.findAll();
		
		if (!clients.isEmpty()) {
			attributes.put("clients", clients);
		} else {
			System.out.println("Empty listClients !!!!! ");
		}
		
		return attributes;
	}
	
	private FreeMarkerEngine getFreeMarkerEngine() {
		FreeMarkerEngine engine = new FreeMarkerEngine();
		Configuration configuration = new Configuration(new Version(2, 3, 23));
		configuration.setTemplateUpdateDelayMilliseconds(Long.MAX_VALUE);
		configuration.setClassForTemplateLoading(FreeMarkerEngine.class, "");
		engine.setConfiguration(configuration);
		return engine;
	}

}
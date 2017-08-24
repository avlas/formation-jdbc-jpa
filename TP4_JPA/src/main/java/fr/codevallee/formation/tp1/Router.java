package fr.codevallee.formation.tp1;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.codevallee.formation.tp1.dao.Client;
import fr.codevallee.formation.tp1.repositories.ClientRepository;
import fr.codevallee.formation.tp1.repositories.IClientRepository;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {
	IClientRepository repo = new ClientRepository();
	
	public static List<Client> clients = new ArrayList<Client>();
	Map<String, Object> attributes = new HashMap<>();
	
	public void init() {

		// http://localhost:9999/list
		get("/list", (request, response) -> {
			
			attributes = getClientsMap(); 			
			return new ModelAndView(attributes, "list.ftl");
			
		}, getFreeMarkerEngine());

		// http://localhost:9999/addClient
		post("/addClient", (request, response) -> {	
			
			return new ModelAndView(attributes, "add.ftl");	
			
		}, getFreeMarkerEngine());

		// http://localhost:9999/add 			-> firstname=? & lastname=? & age=?
		post("/add", (request, response) -> {
			
			String firstname = request.queryParams("firstname");
			String lastname = request.queryParams("lastname");
//			try {
				int age = Integer.parseInt(request.queryParams("age"));
//			} catch(NumberFormatException e) {
//				System.err.println("!!! Give a number !!! "); 
//			}

			Client client = new Client(firstname, lastname, age);
			repo.insert(client);

			attributes = getClientsMap();			
			return new ModelAndView(attributes, "list.ftl");
			
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
			return new ModelAndView(attributes, "list.ftl");
			
		}, getFreeMarkerEngine());
		
		// http://localhost:9999/delete?id=?
		get("/delete", (request, response) -> {
			
			int id = Integer.valueOf(request.queryParams("id"));
			
			repo.delete(id);
			
			attributes = getClientsMap();
			return new ModelAndView(attributes, "list.ftl");
			
		}, getFreeMarkerEngine());

	}

	protected Map<String, Object> getClientsMap() {
		attributes.clear();
		
		clients = repo.findAll();
		
		if (!clients.isEmpty()) {
			attributes.put("clients", clients);
		} else {
			System.out.println("Empty list !!!!! ");
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
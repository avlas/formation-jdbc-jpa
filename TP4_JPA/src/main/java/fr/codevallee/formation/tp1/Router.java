package fr.codevallee.formation.tp1;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import fr.codevallee.formation.tp1.model.Client;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {
	EntityManagerInstance emi = new EntityManagerInstance();

	public void init() {

		// http://localhost:9999/list
		get("/list", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();

			if (!emi.findAll().isEmpty()) {
				attributes.put("clients", emi.findAll());
			} else {
				System.out.println("Empty list !!!!! ");
			}

			return new ModelAndView(attributes, "list.ftl");
		}, getFreeMarkerEngine());

		// http://localhost:9999/add
		post("/add", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "add.ftl");
		}, getFreeMarkerEngine());

		// http://localhost:9999/added?firstname=?&lastname=?&age=?
		get("/added", (request, response) -> {
			String firstname = request.queryParams("firstname");
			String lastname = request.queryParams("lastname");
			int age = Integer.valueOf(request.queryParams("age"));

			Client client = new Client(firstname, lastname, age);
			emi.insert(client);

			Map<String, Object> attributes = new HashMap<>();
			attributes.put("firstname", firstname);
			attributes.put("lastname", lastname);
			attributes.put("age", age);

			if (!emi.findAll().isEmpty()) {
				attributes.put("clients", emi.findAll());
			} else {
				System.out.println("Empty list !!!!! ");
			}
			
			return new ModelAndView(attributes, "list.ftl");
		}, getFreeMarkerEngine());

		// http://localhost:9999/update?id=?
		get("/update", (request, response) -> {
			int id = Integer.valueOf(request.queryParams("id"));
			
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("idUser", id);
			return new ModelAndView(attributes, "update.ftl");
		}, getFreeMarkerEngine());

		// http://localhost:9999/updated?id=?&firstname=?&lastname=?&age=?
		get("/updated", (request, response) -> {
			int id = Integer.valueOf(request.queryParams("id"));			
			String firstname = request.queryParams("firstname");
			String lastname = request.queryParams("lastname");
			int age = Integer.valueOf(request.queryParams("age"));

			emi.update(id, firstname, lastname, age);

			Map<String, Object> attributes = new HashMap<>();
			attributes.put("firstname", firstname);
			attributes.put("lastname", lastname);
			attributes.put("age", age);

			if (!emi.findAll().isEmpty()) {
				attributes.put("clients", emi.findAll());
			} else {
				System.out.println("Empty list !!!!! ");
			}
			
			return new ModelAndView(attributes, "list.ftl");
		}, getFreeMarkerEngine());
		
		// http://localhost:9999/delete?id=?
		get("/delete", (request, response) -> {
			int id = Integer.valueOf(request.queryParams("id"));
			
			emi.delete(id);
			
			Map<String, Object> attributes = new HashMap<>();
			
			if (!emi.findAll().isEmpty()) {
				attributes.put("clients", emi.findAll());
			} else {
				System.out.println("Empty list  !!!!! ");
			}

			return new ModelAndView(attributes, "list.ftl");
		}, getFreeMarkerEngine());

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
package fr.codevallee.formation.tp1;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	public void init() {

		get("/modifier", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "modifier.ftl");
		}, getFreeMarkerEngine());

		get("/resultat", (request, response) -> {
			String nom = request.queryParams("nom");
			String prenom = request.queryParams("prenom");
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("nom", nom);
			attributes.put("prenom", prenom);
			return new ModelAndView(attributes, "resultat.ftl");
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
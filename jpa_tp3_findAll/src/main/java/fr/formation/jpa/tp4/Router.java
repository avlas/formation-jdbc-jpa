package fr.formation.jpa.tp4;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.jpa.tp4.model.Demo;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	public void init() {

		final Logger logger = LoggerFactory.getLogger(Router.class);

		// http://localhost:9999/demo/list
		get("/demo/list", (request, response) -> {
			logger.debug("start");

			EntityManager entityManager = EntityManagerInstance.getInstance();

			TypedQuery<Demo> query = entityManager.createQuery("from Demo", Demo.class);
			
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("objets", query.getResultList());
			
			entityManager.close();

			return new ModelAndView(attributes, "home.ftl");
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
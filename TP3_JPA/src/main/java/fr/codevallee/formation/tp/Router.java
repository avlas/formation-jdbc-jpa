package fr.codevallee.formation.tp;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.codevallee.formation.tp.modele.Demo;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	public void init() {

		final Logger logger = LoggerFactory.getLogger(Router.class);

		
		get("/exemple1", (request, response) -> {

			logger.debug("start");

			Map<String, Object> attributes = new HashMap<>();

			EntityManager entityManager = new EntityManagerInstance().getInstance();
			
			// J'ajoute un métier :
			Demo metier = new Demo();
			metier.setNom("exemple1");

			entityManager.getTransaction().begin();
			entityManager.persist(metier);		// <- il prend l'object => il trouve auto-increment sur l'Obj -> il auto-increment l'index
			entityManager.getTransaction().commit();

			TypedQuery<Demo> query = entityManager.createQuery("from Demo", Demo.class);
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
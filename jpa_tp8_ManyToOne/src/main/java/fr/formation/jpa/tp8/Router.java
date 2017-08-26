package fr.formation.jpa.tp8;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.formation.jpa.tp8.model.Commune;
import fr.formation.jpa.tp8.model.Elu;
import fr.formation.jpa.tp8.model.Maire;
import fr.formation.jpa.tp8.model.Secretaire;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {
	
	public void init() {

		get("/home", (request, response) -> {			
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("election");
			EntityManager em = entityManagerFactory.createEntityManager();

			Commune commune1 = new Commune();
			commune1.setNom("Commune1");
					
			Set<Elu> elus = new HashSet<Elu>();
			Elu elu1 = new Elu();
			elu1.setNom("Elu1");
			elus.add(elu1);
			
			Elu elu2 = new Elu();
			elu1.setNom("Elu2");
			elus.add(elu2);
			
			Maire maire1 = new Maire();
			maire1.setNom("Maire1");			
			maire1.setElu(elus);
			commune1.setMaire(maire1);
			
			// commune1 is NULL in DB
			// maire1.setCommune(commune1);
			
			Secretaire secretaire1 = new Secretaire();
			secretaire1.setMaire(maire1);
						
			em.getTransaction().begin();
			em.persist(elu1);
			em.persist(elu2);
			em.persist(maire1);
			em.persist(secretaire1);
			em.persist(commune1);
			em.getTransaction().commit();
			
			Map<String, Object> attributes = new HashMap<>();
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
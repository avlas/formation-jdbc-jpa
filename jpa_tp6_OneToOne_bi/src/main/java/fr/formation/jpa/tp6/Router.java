package fr.formation.jpa.tp6;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.formation.jpa.tp6.model.Commune;
import fr.formation.jpa.tp6.model.Maire;
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

			Maire maire1 = new Maire();
			maire1.setNom("Maire1");
			commune1.setMaire(maire1);
			
			em.getTransaction().begin();
			em.persist(maire1);
			em.persist(commune1);
			em.getTransaction().commit();
			
			TypedQuery<Commune> communes = em.createQuery("from Commune", Commune.class);
			for (Commune commune : communes.getResultList()) {
				System.out.println("Commune name : " + commune.getNom() + ", Maire : "+ commune.getMaire() );
			}
			
			TypedQuery<Maire> maires = em.createQuery("from Maire", Maire.class);
			for (Maire maire : maires.getResultList()) {
				System.out.println("Maire name : " + maire.getNom() + ", Commune : "+ maire.getCommune() );
			}
			
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
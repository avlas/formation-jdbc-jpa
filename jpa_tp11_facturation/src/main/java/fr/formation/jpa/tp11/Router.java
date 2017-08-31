package fr.formation.jpa.tp11;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.formation.jpa.tp11.model.Article;
import fr.formation.jpa.tp11.model.Bill;
import fr.formation.jpa.tp11.model.Client;
import fr.formation.jpa.tp11.model.Status;
import fr.formation.jpa.tp11.repositories.FacturationRepository;
import fr.formation.jpa.tp11.services.FacturationServiceImpl;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	 FacturationServiceImpl facturationServiceImpl = null;

	public void init() {

		facturationServiceImpl = new FacturationServiceImpl();

		get("/init", (request, response) -> {
			facturationServiceImpl.initDatabase();

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");

		}, new FreeMarkerEngine());

		get("/articles", (request, response) -> {
			TypedQuery<Article> query = FacturationRepository.getEMInstance().createQuery("from Article", Article.class);
			List<Article> articles = query.getResultList();

			for (Article article : articles) {
				System.out.println("ARTICLES -> " + article.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/clients", (request, response) -> {
			TypedQuery<Client> query = FacturationRepository.getEMInstance().createQuery("from Client", Client.class);
			List<Client> clients = query.getResultList();

			for (Client client : clients) {
				System.out.println("CLIENTS -> " + client.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/bills/complete", (request, response) -> {
			TypedQuery<Bill> query = FacturationRepository.getEMInstance().createQuery("from Bill", Bill.class);

			for (Bill bill : (List<Bill>) query.getResultList()) {
				System.out.println("COMPLETE -> " + bill.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/bills/notPayed", (request, response) -> {
			
			Query billsByStatusQuery = FacturationRepository.getEMInstance().createNamedQuery("Bill.findByStatus");
			billsByStatusQuery.setParameter("status", Status.NOT_PAYED);

			for (Bill bill : (List<Bill>) billsByStatusQuery.getResultList()) {
				System.out.println("NOT_PAYED -> " +bill.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/bills/total", (request, response) -> {
		
			TypedQuery<Bill> billsQuery = FacturationRepository.getEMInstance().createQuery("from Bill", Bill.class);

			for (Bill bill : (List<Bill>) billsQuery.getResultList()) {
				Integer total = bill.calculateBillTotal();
				if(total > 500){
					System.out.println("TOTAL -> " + bill.toString());
				}
			}
			
			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());
	}

}
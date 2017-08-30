package fr.formation.jpa.tp11;

import static spark.Spark.get;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.formation.jpa.tp11.model.Address;
import fr.formation.jpa.tp11.model.AddressType;
import fr.formation.jpa.tp11.model.Article;
import fr.formation.jpa.tp11.model.Bill;
import fr.formation.jpa.tp11.model.BillLine;
import fr.formation.jpa.tp11.model.Client;
import fr.formation.jpa.tp11.model.Description;
import fr.formation.jpa.tp11.model.Status;
import fr.formation.jpa.tp11.repositories.FacturationRepository;
import fr.formation.jpa.tp11.services.FacturationServiceImpl;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	FacturationServiceImpl facturationServiceImpl = null;

	public void init() {

	//	facturationServiceImpl = new FacturationServiceImpl();
		
		 EntityManagerFactory entityManagerFactory =
		 Persistence.createEntityManagerFactory("facturation");
		 EntityManager em = entityManagerFactory.createEntityManager();


		get("/init", (request, response) -> {

		//	facturationServiceImpl.initDatabase();

			// Create a billing address
			Address billingAddress = new Address();
			billingAddress.setNumber(20);
			billingAddress.setStreet("rue Marie Curie");
			billingAddress.setPostalCode(Integer.valueOf(69009));
			billingAddress.setType(AddressType.BILLING);

			em.getTransaction().begin();
			em.persist(billingAddress);
			em.getTransaction().commit();

			// Create a delivery address
			Address deliveryAddress = new Address();
			deliveryAddress.setNumber(10);
			deliveryAddress.setStreet("av Berthlot");
			deliveryAddress.setPostalCode(Integer.valueOf(69003));
			deliveryAddress.setType(AddressType.DELIVERY);

			em.getTransaction().begin();
			em.persist(deliveryAddress);
			em.getTransaction().commit();

			// Create a client
			Client mariaC = new Client();
			mariaC.setFirstname("Maria");
			mariaC.setLastname("Carrey");
			mariaC.setDeliveryAddress(billingAddress);
			mariaC.setBillingAddress(deliveryAddress);

			em.getTransaction().begin();
			em.persist(mariaC);
			em.getTransaction().commit();

			// Create a description for hammer
			Description hammerDesc = new Description();
			hammerDesc.setDescription("It's a hammer");

			em.getTransaction().begin();
			em.persist(hammerDesc);
			em.getTransaction().commit();

			// Create an article : hammer
			Article hammer = new Article();
			hammer.setPrice(20);
			hammer.setReference("H_1");
			hammer.setDescription(hammerDesc);

			em.getTransaction().begin();
			em.persist(hammer);
			em.getTransaction().commit();

			// Create a description for nail
			Description nailDesc = new Description();
			nailDesc.setDescription("It's a nail");

			em.getTransaction().begin();
			em.persist(nailDesc);
			em.getTransaction().commit();

			// Create an article : nailSteel
			Article nailSteel = new Article();
			nailSteel.setPrice(2);
			nailSteel.setReference("NS_1");
			nailSteel.setDescription(nailDesc);

			em.getTransaction().begin();
			em.persist(nailSteel);
			em.getTransaction().commit();

			// Create an article : nailGlazier
			Article nailGlazier = new Article();
			nailGlazier.setPrice(5);
			nailGlazier.setReference("NG_1");
			nailGlazier.setDescription(nailDesc);

			em.getTransaction().begin();
			em.persist(nailGlazier);
			em.getTransaction().commit();

			// Create a bill line for hammer
			BillLine hammerLine = new BillLine();
			hammerLine.setArticle(hammer);
			hammerLine.setNumberOfArticles(3);

			em.getTransaction().begin();
			em.persist(hammerLine);
			em.getTransaction().commit();

			// Create a bill line for nailSteel
			BillLine nailSteelLine = new BillLine();
			nailSteelLine.setArticle(nailSteel);
			nailSteelLine.setNumberOfArticles(25);

			em.getTransaction().begin();
			em.persist(nailSteelLine);
			em.getTransaction().commit();

			// Create a bill line for nailGlazier
			BillLine nailGlazierLine = new BillLine();
			nailGlazierLine.setArticle(nailGlazier);
			nailGlazierLine.setNumberOfArticles(10);

			em.getTransaction().begin();
			em.persist(nailGlazierLine);
			em.getTransaction().commit();

			Set<BillLine> lines = new HashSet<>();
			lines.add(hammerLine);
			lines.add(nailSteelLine);
			lines.add(nailGlazierLine);

			// Create a bill
			Bill mariaBill = new Bill();
			mariaBill.setBillLines(lines);
			mariaBill.setClient(mariaC);
			mariaBill.setInvoiceDate(Calendar.getInstance().getTime());
			mariaBill.setStatus(Status.NOT_PAYED);

			em.getTransaction().begin();
			em.persist(mariaBill);
			em.getTransaction().commit();

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");

		}, new FreeMarkerEngine());

		get("/articles", (request, response) -> {
			TypedQuery<Article> query = FacturationRepository.getEMInstance().createQuery("from Article", Article.class);
			List<Article> articles = query.getResultList();

			for (Article article : articles) {
				System.out.println(article.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/clients", (request, response) -> {
			TypedQuery<Client> query = FacturationRepository.getEMInstance().createQuery("from Client", Client.class);
			List<Client> clients = query.getResultList();

			for (Client client : clients) {
				System.out.println(client.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());
		
		get("/completeBills", (request, response) -> {
			TypedQuery<Bill> query = FacturationRepository.getEMInstance().createQuery("from Bill", Bill.class);
			
			for (Bill bill : (List<Bill>) query.getResultList()) {
				System.out.println(bill.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());
		
		get("/bills/notPayed", (request, response) -> {
			
			Query query = em.createNamedQuery("Bill.findByStatus");
			query.setParameter("status", Status.NOT_PAYED);
		
			for (Bill bill : (List<Bill>) query.getResultList()) {
				System.out.println(bill.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/bills/total", (request, response) -> {
			
			Query query = em.createNamedQuery("Bill.findByTotal");
		
			for (Bill bill : (List<Bill>) query.getResultList()) {
				System.out.println(bill.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());
	}

}
package fr.codevallee.formation.tp;

import static spark.Spark.get;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.codevallee.formation.tp.modele.Address;
import fr.codevallee.formation.tp.modele.AddressType;
import fr.codevallee.formation.tp.modele.Article;
import fr.codevallee.formation.tp.modele.Bill;
import fr.codevallee.formation.tp.modele.BillLine;
import fr.codevallee.formation.tp.modele.Client;
import fr.codevallee.formation.tp.modele.Description;
import fr.codevallee.formation.tp.modele.Status;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	//FacturationServiceImpl facturationServiceImpl = null;

	public void init() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("facturation");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		//facturationServiceImpl = new FacturationServiceImpl();

		get("/init", (request, response) -> {

			//facturationServiceImpl.initDatabase();
			
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
			mariaBill.setStatus(Status.PAYED);
					
			em.getTransaction().begin();
			em.persist(mariaBill);
			em.getTransaction().commit();

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");

		}, new FreeMarkerEngine());

	}

}
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
	public static final Integer BILL_TOTAL_LIMIT = 500;

	FacturationServiceImpl facturationServiceImpl = null;

	public void init() {

		facturationServiceImpl = new FacturationServiceImpl();

		get("/init", (request, response) -> {
			facturationServiceImpl.initDatabase();

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");

		}, new FreeMarkerEngine());

		get("/articles", (request, response) -> {
			facturationServiceImpl.find("ARTICLES -> ", "from Article", Article.class);

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/clients", (request, response) -> {
			facturationServiceImpl.find("CLIENTS -> ", "from Client", Client.class);

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/bills/complete", (request, response) -> {
			facturationServiceImpl.find("COMPLETE -> ", "from Bill", Bill.class);

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/bills/notPayed", (request, response) -> {
			Query billsByStatusQuery = FacturationRepository.getEMInstance().createNamedQuery("Bill.findByStatus");
			billsByStatusQuery.setParameter("status", Status.NOT_PAYED);

			List<Bill> billsNotPayed = billsByStatusQuery.getResultList();
			System.out.println("NOT_PAYED -> Size = " + billsNotPayed.size());

			for (Bill bill : billsNotPayed) {
				System.out.println("NOT_PAYED -> " + bill.toString());
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());

		get("/bills/total", (request, response) -> {
			TypedQuery<Bill> billsQuery = FacturationRepository.getEMInstance().createQuery("from Bill", Bill.class);

			List<Bill> bills = billsQuery.getResultList();
			System.out.println("TOTAL > " + BILL_TOTAL_LIMIT + " -> Size = " + bills.size());

			for (Bill bill : bills) {
				Integer total = bill.calculateBillTotal();
				if (total > BILL_TOTAL_LIMIT) {
					System.out.println("TOTAL > " + BILL_TOTAL_LIMIT + " -> " + bill.toString());
				}
			}

			Map<String, Object> attributes = new HashMap<>();
			return new ModelAndView(attributes, "home.ftl");
		}, new FreeMarkerEngine());
	}

}
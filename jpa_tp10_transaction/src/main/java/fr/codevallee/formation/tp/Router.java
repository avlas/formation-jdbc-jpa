package fr.codevallee.formation.tp;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.support.TransactionSynchronizationManager;

import fr.codevallee.formation.tp.configuration.C;
import fr.codevallee.formation.tp.service.MairieServiceImpl;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	MairieServiceImpl mairieServiceImpl = null;

	public void init() {

		// final Logger logger = LoggerFactory.getLogger(Router.class);

		mairieServiceImpl = C.i.getMairieServiceImpl();

		get("/list", (request, response) -> {

			Map<String, Object> attributes = new HashMap<>();
			mairieServiceImpl.ajouterMairie(attributes);
			System.out.println("Router = " + TransactionSynchronizationManager.isActualTransactionActive());
			return new ModelAndView(attributes, "home.ftl");

		}, new FreeMarkerEngine());

	}

}
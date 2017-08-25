package fr.formation.jdbc.tp1;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.formation.jdbc.tp1.model.Plat;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	public void init() {

		// http://localhost:9999/exemple1
		get("/plats", (request, response) -> {
			List<Plat> plats = null;
			
			if(request.queryParams().isEmpty()) {
				plats = Connection_JDBC_MySQL.getPlatsByStmt();	
			} else {
				String tarif = request.queryParams("tarif");				// <- http://localhost:9999/exemple1?tarif=5
				plats = Connection_JDBC_MySQL.getPlatsByPreparedStmt(tarif);	
			}	
			
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("plats", plats);
			
			return new ModelAndView(attributes, "home.ftl"); // ModelAndView
																// (HashMap,
																// template gere
																// par
																// FreeMaker)
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
package fr.codevallee.formation.tp1;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.codevallee.formation.tp1.model.Plat;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Router implements SparkApplication {

	public void init() {

		get("/exemple1", (request, response) -> {
			List<Plat> plats = null;
			
			if( !request.queryParams().isEmpty()) {
				String tarif = request.queryParams("tarif");				// <- http://localhost:9999/exemple1?tarif=5
				plats = JDBCConnection.getPlatsByPreparedStmtConnection(tarif);				
			} else {
				plats = JDBCConnection.getPlatsByStmtConnection();	
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
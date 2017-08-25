package fr.codevallee.formation.tp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:configuration.properties")
public class ConfigurationApplication {

	// @Autowired
	// private Environment env;

	@Bean
	public P p() {
		P p = new P();
		return p;
	}

}

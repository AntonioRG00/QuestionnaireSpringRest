package es.antoniorg.myspringrest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// Si necesitamos simplemente un controlador que nos lleve a una plantilla lo
		// a�adimos desde aqu� para no tener que crear un controlador
		
		registry.addViewController("/login");
	}
	
	

}

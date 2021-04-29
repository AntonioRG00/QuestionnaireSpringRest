package es.antoniorg.myspringrest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("admin").password("admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/webjars/**", "/css/**", "/javax.faces.resource/**", "/rest_idioma/**", "/rest_area/**",
						"/rest_categoria/**", "/rest_pregunta/**", "/rest_respuesta/**", "/rest_preguntarespuesta/**")
				.permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login.xhtml").permitAll().and().logout();
		http.sessionManagement().maximumSessions(1);

		// Parte del logout
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/");

		http.csrf().disable();
	}
}

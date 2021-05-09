package es.antoniorg.myspringrest.security;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${urlsPermitidas}.split(',')")
	private String[] urlsPermitidas;
	
    private static final String[] SWAGGER_UI_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui/**"
    };

    private @Autowired MyUserDetailsService userDetailService;
    private @Autowired CustomAuthenticationFailureHandler authFailureHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(Stream.concat(Arrays.stream(urlsPermitidas), Arrays.stream(SWAGGER_UI_WHITELIST)).toArray(String[]::new)).permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login.xhtml").failureHandler(authFailureHandler).permitAll().failureUrl("/login-error").and().logout();
		http.sessionManagement().maximumSessions(3);

		http.logout().logoutUrl("/logout").logoutSuccessUrl("/");

		http.csrf().disable();
	}
}

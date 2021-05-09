package es.antoniorg.myspringrest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;
import lombok.Setter;

@Controller("dispatcherController")
public class DispatcherController {

	@GetMapping("/")
	public String inicio() {
		errorMessageLogin = "";
		return "index.xhtml";
	}
	
	/** Variable que contiene los mensajes de error del login */
	private @Getter @Setter String errorMessageLogin = "";
	
	@GetMapping("/login-error")
	public String loginError(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (ex != null) {
				errorMessageLogin = ex.getMessage();
			}
		}

		return "login.xhtml";
	}
}

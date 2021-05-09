package es.antoniorg.myspringrest.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.antoniorg.myspringrest.exception.LoginAttemptsExceded;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	private @Autowired LoginAttemptService loginAttemptService;

	private @Autowired HttpServletRequest request;
	
	private @Value("${login.usuario}") String usuario;
	private @Value("${login.contrasena}") String contrasena;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LoginAttemptsExceded {
		String ip = getClientIP();
		if (loginAttemptService.isBlocked(ip)) {
			throw new LoginAttemptsExceded("La ip " + getClientIP() + " ha sido bloqueada por superar el máximo de intentos");
		}

		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		return new User(usuario, contrasena, true, true, true, true, roles);
	}

	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}

package es.antoniorg.myspringrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/inicio")
	public String inicio() {
		return "index";
	}
	
	@GetMapping("/body_consulta_select")
	public void select() { 
		
	}
}

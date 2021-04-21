package es.antoniorg.myspringrest.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.repository.IdiomaRepository;
import lombok.Getter;
import lombok.Setter;

@Component("mainController")
@ViewScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = 8929086073643011545L;

	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private @Autowired IdiomaRepository idiomaRepository;
	
	/** Lista con los idiomas de la tabla */
	private @Getter @Setter List<Idioma> idiomas;
	
	private @Getter @Setter Idioma idiomaEdit;
	
	private @Getter @Setter String test = "";

	@PostConstruct
	public void init() {
		logger.info("MainController init");
		
		limpiarVariables();
		
		idiomas = idiomaRepository.findAll();
		
		logger.info("MainController end");
	}

	/** Inicializa todas las variables */
	public void limpiarVariables() {
		idiomaEdit = new Idioma();
	}
	
	/** Actualiza el idioma seleccionado para después llamar a crearIdioma */
	public void actualizarIdioma(Idioma idioma) {
		logger.info("actualizarIdioma init: Se procede a actualizar el idioma seleccionado a: " + idioma.toString());
		idiomaEdit = idioma;
	}
	
	/** Asigna un nuevo idioma a la variable idioma editable */
	public void crearIdioma() {
		logger.info("crearIdioma init: Aplastando la variable idiomaEdit");
		idiomaEdit = new Idioma();
	}
	
	/** Persiste un nuevo idioma con la variable idiomaEdit */
	public void persistIdioma() {
		logger.info("crearIdioma init: Se procede a persistir el idioma " + idiomaEdit.toString());
		idiomaRepository.saveAndFlush(idiomaEdit);
	}
	
	/** Elimina el idioma pasado por parámetro */
	public void eliminarIdioma(Idioma idioma) {
		logger.info("eliminarIdioma init: Se va a borrar el idioma: " + idioma.toString());
		idiomaRepository.delete(idioma);
	}
}
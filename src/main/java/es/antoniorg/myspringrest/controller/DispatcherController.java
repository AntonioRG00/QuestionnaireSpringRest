package es.antoniorg.myspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.antoniorg.myspringrest.model.Area;
import es.antoniorg.myspringrest.model.Categoria;
import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.model.Pregunta;
import es.antoniorg.myspringrest.model.PreguntaRespuesta;
import es.antoniorg.myspringrest.model.Respuesta;
import es.antoniorg.myspringrest.repository.AreaRepository;
import es.antoniorg.myspringrest.repository.CategoriaRepository;
import es.antoniorg.myspringrest.repository.IdiomaRepository;
import es.antoniorg.myspringrest.repository.PreguntaRepository;
import es.antoniorg.myspringrest.repository.PreguntaRespuestaRepository;
import es.antoniorg.myspringrest.repository.RespuestaRepository;

@Controller
public class DispatcherController {

	private @Autowired IdiomaRepository idiomaRepository;
	private @Autowired AreaRepository areaRepository;
	private @Autowired CategoriaRepository categoriaRepository;
	private @Autowired PreguntaRepository preguntaRepository;
	private @Autowired RespuestaRepository respuestaRepository;
	private @Autowired PreguntaRespuestaRepository prRepository;

	/** Lista con los nombres de las tablas de la base de datos */
	@Value("#{'${crud.tablas}'.split(',')}")
	private List<String> nombreTablas;

	@Value("#{'${datatable.idioma}'.split(',')}")
	private List<String> camposTablaIdioma;

	@Value("#{'${datatable.area}'.split(',')}")
	private List<String> camposTablaArea;

	@Value("#{'${datatable.categoria}'.split(',')}")
	private List<String> camposTablaCategoria;

	@Value("#{'${datatable.pregunta}'.split(',')}")
	private List<String> camposTablaPregunta;

	@Value("#{'${datatable.respuesta}'.split(',')}")
	private List<String> camposTablaRespuesta;
	
	@Value("#{'${datatable.preguntarespuesta}'.split(',')}")
	private List<String> camposTablaPreguntarespuesta;
	
	@GetMapping("/")
	public String inicio() {
		return "forward:/index.xhtml";
	}
}

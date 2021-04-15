package es.antoniorg.myspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.antoniorg.myspringrest.repository.AreaRepository;
import es.antoniorg.myspringrest.repository.CategoriaRepository;
import es.antoniorg.myspringrest.repository.IdiomaRepository;
import es.antoniorg.myspringrest.repository.PreguntaRepository;
import es.antoniorg.myspringrest.repository.RespuestaRepository;

@Controller
public class MainController {

	private @Autowired IdiomaRepository idiomaRepository;
	private @Autowired AreaRepository areaRepository;
	private @Autowired CategoriaRepository categoriaRepository;
	private @Autowired PreguntaRepository preguntaRepository;
	private @Autowired RespuestaRepository respuestaRepository;

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

	@GetMapping("/")
	public String inicio(Model model) {
		model.addAttribute("listaIdioma", idiomaRepository.findAll());
		model.addAttribute("listaArea", areaRepository.findAll());
		model.addAttribute("listaCategoria", categoriaRepository.findAll());
		model.addAttribute("listaPregunta", preguntaRepository.findAll());
		model.addAttribute("listaRespuesta", respuestaRepository.findAll());

		// Añadimos las tablas
		model.addAttribute("listaNombreTablas", nombreTablas);

		// Añadimos los campos de las tablas
		model.addAttribute("listaCamposIdioma", camposTablaIdioma);
		model.addAttribute("listaCamposArea", camposTablaArea);
		model.addAttribute("listaCamposCategoria", camposTablaCategoria);
		model.addAttribute("listaCamposPregunta", camposTablaPregunta);
		model.addAttribute("listaCamposRespuesta", camposTablaRespuesta);

		// Vamos al index.html
		return "index";
	}

	@GetMapping("/body_consulta_select")
	public void select() {

	}

}

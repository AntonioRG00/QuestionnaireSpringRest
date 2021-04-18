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
public class MainController {

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
		model.addAttribute("listaCamposPreguntarespuesta", camposTablaPreguntarespuesta);
		
		// Añadimos los objetos del formulario
		model.addAttribute("idiomaForm", new Idioma());
		model.addAttribute("areaForm", new Area());
		model.addAttribute("categoriaForm", new Categoria());
		model.addAttribute("preguntaForm", new Pregunta());
		model.addAttribute("respuestaForm", new Respuesta());
		model.addAttribute("preguntarespuestaForm", new PreguntaRespuesta());

		// Vamos al index.html
		return "index";
	}
	
	@PostMapping("/idioma/new/submit")
	public String createIdiomaForm(@ModelAttribute("idiomaForm") Idioma idioma) {
		try {
			idiomaRepository.save(new Idioma(idioma.getNombre(), idioma.getUrlImagen()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@PostMapping("/area/new/submit")
	public String createAreaForm(@ModelAttribute("areaForm") Area area) {
		try {
			areaRepository.save(new Area(area.getNombre(), area.getIdioma()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@PostMapping("/categoria/new/submit")
	public String createCategoriaForm(@ModelAttribute("categoriaForm") Categoria categoria) {
		try {
			categoriaRepository.save(new Categoria(categoria.getNombre(), categoria.getDescripcion(), categoria.getArea(), categoria.getExplicacion(), categoria.getMaxParaRecomendacion(), categoria.getRecomendacion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@PostMapping("/pregunta/new/submit")
	public String createPreguntaForm(@ModelAttribute("preguntaForm") Pregunta pregunta) {
		try {
			preguntaRepository.save(new Pregunta(pregunta.getPregunta(), pregunta.getCategoria(), pregunta.getRecomendacion(), pregunta.getPuntuacionRecomendacion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@PostMapping("/respuesta/new/submit")
	public String createRespuestaForm(@ModelAttribute("respuestaForm") Respuesta respuesta) {
		try {
			respuestaRepository.save(new Respuesta(respuesta.getRespuesta()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@PostMapping("/preguntarespuesta/new/submit")
	public String createPreguntarespuestaForm(@ModelAttribute("respuestaForm") PreguntaRespuesta pr) {
		try {
			prRepository.save(new PreguntaRespuesta(pr.getPregunta(), pr.getRespuesta(), pr.getPuntuacion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}

package es.antoniorg.myspringrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.antoniorg.myspringrest.model.Area;
import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.repository.AreaRepository;
import es.antoniorg.myspringrest.repository.CategoriaRepository;
import es.antoniorg.myspringrest.repository.IdiomaRepository;
import es.antoniorg.myspringrest.repository.PreguntaRepository;
import es.antoniorg.myspringrest.repository.PreguntaRespuestaRepository;
import es.antoniorg.myspringrest.repository.RespuestaRepository;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/rest")
public class ControladorRest {

	private @Autowired IdiomaRepository idiomaRepository;
	private @Autowired AreaRepository areaRepository;
	private @Autowired CategoriaRepository categoriaRepository;
	private @Autowired PreguntaRepository preguntaRepository;
	private @Autowired RespuestaRepository respuestaRepository;
	private @Autowired PreguntaRespuestaRepository prRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Area>> getAll() {
		try {
			List<Idioma> idiomas = new ArrayList<>();
			
			
			List<Area> areas = new ArrayList<>();

			areaRepository.findAll().forEach(areas::add);

			if (areas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(areas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

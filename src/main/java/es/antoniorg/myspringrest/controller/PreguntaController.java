package es.antoniorg.myspringrest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.antoniorg.myspringrest.model.Pregunta;
import es.antoniorg.myspringrest.repository.PreguntaRepository;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/rest_pregunta")
public class PreguntaController {

	@Autowired
	private PreguntaRepository preguntaRepository;

	@GetMapping("/pregunta")
	public ResponseEntity<List<Pregunta>> getAllPregunta() {
		try {
			List<Pregunta> pregunta = new ArrayList<Pregunta>();

			preguntaRepository.findAll().forEach(pregunta::add);

			if (pregunta.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(pregunta, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pregunta/{id}")
	public ResponseEntity<Pregunta> getPreguntaById(@PathVariable("id") Long id) {
		Optional<Pregunta> preguntaData = preguntaRepository.findById(id);

		if (preguntaData.isPresent()) {
			return new ResponseEntity<>(preguntaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/pregunta")
	public ResponseEntity<Pregunta> createPregunta(@RequestBody Pregunta pregunta) {
		try {

			Pregunta aux = preguntaRepository.save(new Pregunta(pregunta.getPregunta(), pregunta.getCategoria(), pregunta.getRecomendacion(), pregunta.getPuntuacionRecomendacion()));

			return new ResponseEntity<>(aux, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/pregunta/{id}")
	public ResponseEntity<Pregunta> updatePregunta(@PathVariable("id") Long id, @RequestBody Pregunta pregunta) {
		Optional<Pregunta> preguntaData = preguntaRepository.findById(id);

		if (preguntaData.isPresent()) {
			Pregunta aux = preguntaData.get();
			aux.setPregunta(pregunta.getPregunta());
			aux.setCategoria(pregunta.getCategoria());
			aux.setRecomendacion(pregunta.getRecomendacion());
			aux.setPuntuacionRecomendacion(pregunta.getPuntuacionRecomendacion());
			return new ResponseEntity<>(preguntaRepository.save(aux), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/pregunta/{id}")
	public ResponseEntity<HttpStatus> deletePregunta(@PathVariable("id") Long id) {
		try {
			preguntaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

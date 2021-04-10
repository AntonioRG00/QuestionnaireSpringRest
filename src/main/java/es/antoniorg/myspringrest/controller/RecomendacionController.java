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

import es.antoniorg.myspringrest.model.Recomendacion;
import es.antoniorg.myspringrest.repository.RecomendacionRepository;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/rest_recomendacion")
public class RecomendacionController {

	@Autowired
	private RecomendacionRepository recomendacionRepository;

	@GetMapping("/recomendacion")
	public ResponseEntity<List<Recomendacion>> getAllRecomendacion() {
		try {
			List<Recomendacion> recomendaciones = new ArrayList<Recomendacion>();

			recomendacionRepository.findAll().forEach(recomendaciones::add);

			if (recomendaciones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(recomendaciones, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/recomendacion/{id}")
	public ResponseEntity<Recomendacion> getRecomendacionById(@PathVariable("id") Long id) {
		Optional<Recomendacion> recomendacionData = recomendacionRepository.findById(id);

		if (recomendacionData.isPresent()) {
			return new ResponseEntity<>(recomendacionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/recomendacion")
	public ResponseEntity<Recomendacion> createRecomendacion(@RequestBody Recomendacion recomendacion) {
		try {
			
			Recomendacion aux = recomendacionRepository.save(new Recomendacion(recomendacion.getDescripcion()));
			
			return new ResponseEntity<>(aux, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/recomendacion/{id}")
	public ResponseEntity<Recomendacion> updateRecomendacion(@PathVariable("id") Long id, @RequestBody Recomendacion recomendacion) {
		Optional<Recomendacion> recomendacionData = recomendacionRepository.findById(id);

		if (recomendacionData.isPresent()) {
			Recomendacion aux = recomendacionData.get();
			aux.setDescripcion(recomendacion.getDescripcion());
			return new ResponseEntity<>(recomendacionRepository.save(aux), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/recomendacion/{id}")
	public ResponseEntity<HttpStatus> deleteRecomendacion(@PathVariable("id") Long id) {
		try {
			recomendacionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

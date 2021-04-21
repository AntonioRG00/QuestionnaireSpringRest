package es.antoniorg.myspringrest.rest_controllers;

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

import es.antoniorg.myspringrest.model.Respuesta;
import es.antoniorg.myspringrest.repository.RespuestaRepository;

@RestController
@RequestMapping("/rest_respuesta")
public class RespuestaController {

	@Autowired
	private RespuestaRepository respuestaRepository;

	@GetMapping("/respuesta")
	public ResponseEntity<List<Respuesta>> getAllRespuesta() {
		try {
			List<Respuesta> respuesta = new ArrayList<Respuesta>();

			respuestaRepository.findAll().forEach(respuesta::add);

			if (respuesta.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(respuesta, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/respuesta/{id}")
	public ResponseEntity<Respuesta> getRespuestaById(@PathVariable("id") Long id) {
		Optional<Respuesta> respuestaData = respuestaRepository.findById(id);

		if (respuestaData.isPresent()) {
			return new ResponseEntity<>(respuestaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/respuesta")
	public ResponseEntity<Respuesta> createRespuesta(@RequestBody Respuesta respuesta) {
		try {

			Respuesta aux = respuestaRepository.save(new Respuesta(respuesta.getRespuesta()));

			return new ResponseEntity<>(aux, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/respuesta/{id}")
	public ResponseEntity<Respuesta> updateRespuesta(@PathVariable("id") Long id, @RequestBody Respuesta respuesta) {
		Optional<Respuesta> respuestaData = respuestaRepository.findById(id);

		if (respuestaData.isPresent()) {
			Respuesta aux = respuestaData.get();
			aux.setRespuesta(respuesta.getRespuesta());
			return new ResponseEntity<>(respuestaRepository.save(aux), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/respuesta/{id}")
	public ResponseEntity<HttpStatus> deleteRespuesta(@PathVariable("id") Long id) {
		try {
			respuestaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

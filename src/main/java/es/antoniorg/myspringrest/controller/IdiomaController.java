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

import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.repository.IdiomaRepository;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/rest_idioma")
public class IdiomaController {

	@Autowired
	private IdiomaRepository idiomaRepository;

	@GetMapping("/idioma")
	public ResponseEntity<List<Idioma>> getAllIdioma() {
		try {
			List<Idioma> idiomas = new ArrayList<Idioma>();

			idiomaRepository.findAll().forEach(idiomas::add);

			if (idiomas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(idiomas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/idioma/{id}")
	public ResponseEntity<Idioma> getIdiomaById(@PathVariable("id") Long id) {
		Optional<Idioma> idiomaData = idiomaRepository.findById(id);

		if (idiomaData.isPresent()) {
			return new ResponseEntity<>(idiomaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/idioma")
	public ResponseEntity<Idioma> createIdioma(@RequestBody Idioma idioma) {
		try {
			
			Idioma aux = idiomaRepository.save(new Idioma(idioma.getNombre(), idioma.getUrlImagen()));
			
			return new ResponseEntity<>(aux, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/idioma/{id}")
	public ResponseEntity<Idioma> updateIdioma(@PathVariable("id") Long id, @RequestBody Idioma idioma) {
		Optional<Idioma> idiomaData = idiomaRepository.findById(id);

		if (idiomaData.isPresent()) {
			Idioma aux = idiomaData.get();
			aux.setNombre(idioma.getNombre());
			aux.setUrlImagen(idioma.getUrlImagen());
			return new ResponseEntity<>(idiomaRepository.save(aux), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/idioma/{id}")
	public ResponseEntity<HttpStatus> deleteIdioma(@PathVariable("id") Long id) {
		try {
			idiomaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
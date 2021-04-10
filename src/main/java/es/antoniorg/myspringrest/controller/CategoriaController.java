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

import es.antoniorg.myspringrest.model.Categoria;
import es.antoniorg.myspringrest.repository.CategoriaRepository;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/rest_categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> getAllCategoria() {
		try {
			List<Categoria> categoria = new ArrayList<Categoria>();

			categoriaRepository.findAll().forEach(categoria::add);

			if (categoria.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(categoria, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") Long id) {
		Optional<Categoria> categoriaData = categoriaRepository.findById(id);

		if (categoriaData.isPresent()) {
			return new ResponseEntity<>(categoriaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/categoria")
	public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
		try {

			Categoria aux = categoriaRepository.save(new Categoria(categoria.getNombre(), categoria.getDescripcion(),
					categoria.getIdArea(), categoria.getExplicacion(), categoria.getMaxParaRecomendacion(),
					categoria.getIdRecomendacion()));

			return new ResponseEntity<>(aux, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/categoria/{id}")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
		Optional<Categoria> idiomaData = categoriaRepository.findById(id);

		if (idiomaData.isPresent()) {
			Categoria aux = idiomaData.get();
			aux.setNombre(categoria.getNombre());
			aux.setDescripcion(categoria.getDescripcion());
			aux.setIdArea(categoria.getIdArea());
			aux.setExplicacion(categoria.getExplicacion());
			aux.setMaxParaRecomendacion(categoria.getMaxParaRecomendacion());
			aux.setIdRecomendacion(categoria.getIdRecomendacion());
			return new ResponseEntity<>(categoriaRepository.save(aux), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/categoria/{id}")
	public ResponseEntity<HttpStatus> deleteCategoria(@PathVariable("id") Long id) {
		try {
			categoriaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

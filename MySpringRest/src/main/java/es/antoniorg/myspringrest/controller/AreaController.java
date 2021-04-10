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

import es.antoniorg.myspringrest.model.Area;
import es.antoniorg.myspringrest.repository.AreaRepository;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/rest_area")
public class AreaController {

	@Autowired
	private AreaRepository areaRepository;

	@GetMapping("/area")
	public ResponseEntity<List<Area>> getAllAreas() {
		try {
			List<Area> areas = new ArrayList<Area>();

			areaRepository.findAll().forEach(areas::add);

			if (areas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(areas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/area/{id}")
	public ResponseEntity<Area> getAreaById(@PathVariable("id") Long id) {
		Optional<Area> areaData = areaRepository.findById(id);

		if (areaData.isPresent()) {
			return new ResponseEntity<>(areaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/area")
	public ResponseEntity<Area> createArea(@RequestBody Area area) {
		try {
			
			Area aux = areaRepository.save(new Area(area.getNombre()));
			
			return new ResponseEntity<>(aux, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/area/{id}")
	public ResponseEntity<Area> updateArea(@PathVariable("id") Long id, @RequestBody Area area) {
		Optional<Area> areaData = areaRepository.findById(id);

		if (areaData.isPresent()) {
			Area aux = areaData.get();
			aux.setNombre(area.getNombre());
			return new ResponseEntity<>(areaRepository.save(aux), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/area/{id}")
	public ResponseEntity<HttpStatus> deleteArea(@PathVariable("id") Long id) {
		try {
			areaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@DeleteMapping("/area")
//	public ResponseEntity<HttpStatus> deleteAllTutorials() {
//		try {
//			areaRepository.deleteAll();
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
}

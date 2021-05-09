package es.antoniorg.myspringrest.rest_controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.antoniorg.myspringrest.model.PreguntaRespuesta;
import es.antoniorg.myspringrest.model.PreguntaRespuestaPK;
import es.antoniorg.myspringrest.repository.PreguntaRespuestaRepository;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/rest_preguntarespuesta")
@Api(tags="Pregunta<->Respuesta-Rest")
public class PreguntaRespuestaController {

	@Autowired
	private PreguntaRespuestaRepository prRepository;

	@GetMapping("/preguntarespuesta")
	public ResponseEntity<List<PreguntaRespuesta>> getAllPreguntaRespuesta() {
		try {
			List<PreguntaRespuesta> pr = new ArrayList<PreguntaRespuesta>();

			prRepository.findAll().forEach(pr::add);

			if (pr.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(pr, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/preguntarespuesta/{idPregunta}/{idRespuesta}")
	public ResponseEntity<PreguntaRespuesta> getPreguntaRespuestaById(@PathVariable("idPregunta") Long idPregunta, @PathVariable("idRespuesta") Long idRespuesta) {
		Optional<PreguntaRespuesta> preguntaData = prRepository.findById(new PreguntaRespuestaPK(idPregunta, idRespuesta));

		if (preguntaData.isPresent()) {
			return new ResponseEntity<>(preguntaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@PostMapping("/preguntarespuesta")
//	public ResponseEntity<PreguntaRespuesta> createPreguntaRespuesta(@RequestBody PreguntaRespuesta pr) {
//		try {
//
//			PreguntaRespuesta aux = prRepository.save(new PreguntaRespuesta(pr.getPregunta(), pr.getRespuesta(), pr.getPuntuacion()));
//
//			return new ResponseEntity<>(aux, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@PutMapping("/preguntarespuesta/{idPregunta}/{idRespuesta}")
//	public ResponseEntity<PreguntaRespuesta> updatePreguntaRespuesta(@PathVariable("idPregunta") Long idPregunta, @PathVariable("idRespuesta") Long idRespuesta, @RequestBody PreguntaRespuesta pr) {
//		Optional<PreguntaRespuesta> preguntaData = prRepository.findById(new PreguntaRespuestaPK(idPregunta, idRespuesta));
//
//		if (preguntaData.isPresent()) {
//			PreguntaRespuesta aux = preguntaData.get();
//			aux.setPregunta(pr.getPregunta());
//			aux.setRespuesta(pr.getRespuesta());
//			aux.setPuntuacion(pr.getPuntuacion());
//			return new ResponseEntity<>(prRepository.save(aux), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@DeleteMapping("/preguntarespuesta/{idPregunta}/{idRespuesta}")
//	public ResponseEntity<HttpStatus> deletePreguntaRespuesta(@PathVariable("idPregunta") Long idPregunta, @PathVariable("idRespuesta") Long idRespuesta) {
//		try {
//			prRepository.deleteById(new PreguntaRespuestaPK(idPregunta, idRespuesta));
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}

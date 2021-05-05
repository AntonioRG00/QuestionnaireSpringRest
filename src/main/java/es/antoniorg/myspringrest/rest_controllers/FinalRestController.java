package es.antoniorg.myspringrest.rest_controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.model.PreguntaRespuesta;
import es.antoniorg.myspringrest.model.RespuestaPorDefecto;
import es.antoniorg.myspringrest.repository.IdiomaRepository;

@RestController
@RequestMapping("/rest")
public class FinalRestController {

	private @Autowired IdiomaRepository idiomaRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Idioma>> getAllData() {
		try {
			List<Idioma> idiomas = new ArrayList<Idioma>();

			idiomaRepository.findAll().forEach(idiomas::add);

			// Asignamos las respuestas por defecto si la pregunta no tiene respuestas
			idiomas.forEach(i -> i.getAreas().forEach(a -> a.getCategorias().forEach(c -> c.getPreguntas().forEach(p -> {
						if (p.getRespuestas().isEmpty()) {
							for (RespuestaPorDefecto prDefecto : a.getRespuestasPorDefecto()) {
								p.getRespuestas().add(
										new PreguntaRespuesta(p, prDefecto.getRespuesta(), prDefecto.getPuntuacion()));
							}
						}
					}))));

			if (idiomas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(idiomas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

package es.antoniorg.myspringrest.rest_controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.model.Perfil;
import es.antoniorg.myspringrest.model.PreguntaRespuesta;
import es.antoniorg.myspringrest.model.RespuestaPorDefecto;
import es.antoniorg.myspringrest.repository.IdiomaRepository;
import es.antoniorg.myspringrest.repository.PerfilRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest")
@Api(tags="!RestFinal (Servicio rest con las funciones más importantes)")
public class FinalRestController {

	private @Autowired IdiomaRepository idiomaRepository;
	private @Autowired PerfilRepository perfilRepository;

	@ApiOperation(value = "Obtiene todos los idiomas con sus datos (Ordenado por Idioma.nombre y dentro por su Area.nombre)", notes = "Devuelve el JSON final con las respuestas por defecto asociadas o las asignadas individualmente")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = Idioma.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/all")
	public ResponseEntity<List<Idioma>> getAllData() {
		try {
			List<Idioma> idiomas = new ArrayList<Idioma>();

			idiomaRepository.findAll(Sort.by(Sort.Direction.DESC, "nombre")).forEach(idiomas::add);
			
			// Asignamos las respuestas por defecto si la pregunta no tiene respuestas
			idiomas.forEach(i -> i.getAreas().forEach(a -> a.getCategorias().forEach(c -> c.getPreguntas().forEach(p -> {
						if (p.getRespuestas().isEmpty()) {
							for (RespuestaPorDefecto prDefecto : a.getRespuestasPorDefecto()) {
								p.getRespuestas().add(new PreguntaRespuesta(p, prDefecto.getRespuesta(), prDefecto.getPuntuacion()));
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
	
	@ApiOperation(value = "Obtiene todos los perfiles")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = Idioma.class),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/perfiles")
	public ResponseEntity<List<Perfil>> getAllPerfiles(){
		try {
			
			List<Perfil> perfiles = new ArrayList<>();
			
			perfilRepository.findAll().forEach(perfiles::add);
			
			if(perfiles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(perfiles, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

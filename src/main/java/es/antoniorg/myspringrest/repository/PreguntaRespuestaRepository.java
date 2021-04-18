package es.antoniorg.myspringrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.antoniorg.myspringrest.model.PreguntaRespuesta;
import es.antoniorg.myspringrest.model.PreguntaRespuestaPK;

public interface PreguntaRespuestaRepository extends JpaRepository<PreguntaRespuesta, PreguntaRespuestaPK>{

}

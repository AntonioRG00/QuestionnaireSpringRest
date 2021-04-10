package es.antoniorg.myspringrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.antoniorg.myspringrest.model.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{

}

package es.antoniorg.myspringrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.antoniorg.myspringrest.model.Recomendacion;

public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long>{

}

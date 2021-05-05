package es.antoniorg.myspringrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.antoniorg.myspringrest.model.RespuestaPorDefecto;
import es.antoniorg.myspringrest.model.RespuestaPorDefectoPK;

public interface RespuestaPorDefectoRepository extends JpaRepository<RespuestaPorDefecto, RespuestaPorDefectoPK>{

}

package es.antoniorg.myspringrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.antoniorg.myspringrest.model.Area;

public interface AreaRepository extends JpaRepository<Area, Long>{
	
	@Query(value = "SELECT * FROM area WHERE area.id_idioma = ?1", nativeQuery = true)
	List<Area> getAllAreasByIdiomaId(Long id);

}

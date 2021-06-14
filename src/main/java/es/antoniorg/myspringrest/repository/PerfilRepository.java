package es.antoniorg.myspringrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.antoniorg.myspringrest.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	@Query(value = "SELECT * FROM perfil WHERE perfil.id_idioma = ?1", nativeQuery = true)
	List<Perfil> getPerfilesByIdIdioma(Long idIdioma);
}


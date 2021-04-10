package es.antoniorg.myspringrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.antoniorg.myspringrest.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}

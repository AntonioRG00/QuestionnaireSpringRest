package es.antoniorg.myspringrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor
@Entity @Table(name = "pregunta")
public class Pregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "pregunta")
	private String pregunta;
	
	@Column(name = "id_categoria")
	private Long idCategoria;
	
	@Column(name = "id_respuesta")
	private Long idRespuesta;

	public Pregunta(String pregunta, Long idCategoria, Long idRespuesta) {
		this.pregunta = pregunta;
		this.idCategoria = idCategoria;
		this.idRespuesta = idRespuesta;
	}
}

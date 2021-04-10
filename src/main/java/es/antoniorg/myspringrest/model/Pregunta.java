package es.antoniorg.myspringrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_respuesta")
	private Respuesta respuesta;

	public Pregunta(String pregunta, Categoria categoria, Respuesta respuesta) {
		this.pregunta = pregunta;
		this.categoria = categoria;
		this.respuesta = respuesta;
	}
}

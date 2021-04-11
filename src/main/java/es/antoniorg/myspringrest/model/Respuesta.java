package es.antoniorg.myspringrest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor
@Entity @Table(name = "respuesta")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "respuestas")
	private String[] respuestas;
	
	@Column(name = "pos_respuesta_adecuada")
	private int posRespuestaAdecuada;
	
	@ManyToOne
	@JoinColumn(name = "recomendacion")
	private String recomendacion;

	@OneToMany
	private List<Pregunta> preguntas = new ArrayList<Pregunta>();

	public Respuesta(String[] respuestas, int posRespuestaAdecuada, String recomendacion) {
		this.respuestas = respuestas;
		this.posRespuestaAdecuada = posRespuestaAdecuada;
		this.recomendacion = recomendacion;
	}	
}

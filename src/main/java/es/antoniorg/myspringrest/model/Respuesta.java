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
	
	@Column(name = "id_recomendacion")
	private Long idRecomendacion;

	public Respuesta(String[] respuestas, int posRespuestaAdecuada, Long idRecomendacion) {
		this.respuestas = respuestas;
		this.posRespuestaAdecuada = posRespuestaAdecuada;
		this.idRecomendacion = idRecomendacion;
	}
}

package es.antoniorg.myspringrest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor
@Entity @Table(name = "pregunta_respuesta") @IdClass(PreguntaRespuestaPK.class)
public class PreguntaRespuesta implements Serializable {
	
	private static final long serialVersionUID = 6001634131165241957L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "pregunta_id")
	@EqualsAndHashCode.Include
	private Pregunta pregunta;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "respuesta_id")
	@EqualsAndHashCode.Include
	private Respuesta respuesta;
	
	@Column(name = "puntuacion", nullable = false)
	private int puntuacion;
	
	public PreguntaRespuesta(Pregunta pregunta, Respuesta respuesta, int puntuacion){
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.puntuacion = puntuacion;
	}
}

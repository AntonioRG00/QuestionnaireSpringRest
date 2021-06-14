package es.antoniorg.myspringrest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor @ToString @AllArgsConstructor
@Entity @Table(name = "pregunta_respuesta")
public class PreguntaRespuesta implements Serializable {
	
	private static final long serialVersionUID = 6001634131165241957L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value="ID de pregunta-respuesta", dataType="Long", example="1", position=1)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pregunta_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Pregunta pregunta;
	
	@ManyToOne
	@JoinColumn(name = "respuesta_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Respuesta respuesta;
	
	@Column(name = "puntuacion", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ApiModelProperty(value="Puntuación de la respuesta", dataType="int", example="1", position=1)
	private int puntuacion;

	public PreguntaRespuesta(Pregunta pregunta, Respuesta respuesta, int puntuacion) {
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.puntuacion = puntuacion;
	}
}

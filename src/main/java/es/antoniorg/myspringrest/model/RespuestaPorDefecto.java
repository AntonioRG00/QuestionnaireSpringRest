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

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor @ToString @AllArgsConstructor
@Entity @Table(name = "respuesta_por_defecto")
public class RespuestaPorDefecto implements Serializable{

	private static final long serialVersionUID = 7475397784156785171L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value="ID de respuesta por defecto", dataType="Long", example="1", position=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "respuesta_id")
	private Respuesta respuesta;
	
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	@Column(name = "puntuacion", nullable = false)
	@ApiModelProperty(value="Puntuación de la respuesta", dataType="int", example="1", position=1)
	private int puntuacion;

	public RespuestaPorDefecto(Respuesta respuesta, Area area, int puntuacion) {
		this.respuesta = respuesta;
		this.area = area;
		this.puntuacion = puntuacion;
	}
}

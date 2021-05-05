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
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor @ToString
@Entity @Table(name = "respuesta_por_defecto") @IdClass(RespuestaPorDefectoPK.class)
public class RespuestaPorDefecto implements Serializable{

	private static final long serialVersionUID = 7475397784156785171L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "respuesta_id")
	@EqualsAndHashCode.Include
	private Respuesta respuesta;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "area_id")
	@EqualsAndHashCode.Include
	private Area area;
	
	@Column(name = "puntuacion", nullable = false)
	private int puntuacion;

	public RespuestaPorDefecto(Respuesta respuesta, Area area, int puntuacion) {
		this.respuesta = respuesta;
		this.area = area;
		this.puntuacion = puntuacion;
	}
}

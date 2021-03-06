package es.antoniorg.myspringrest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor @ToString
@Entity @Table(name = "respuesta")
public class Respuesta implements Serializable {

	private static final long serialVersionUID = 480089179196269564L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value="ID de respuesta", dataType="Long", example="1", position=1)
	private Long id;
	
	@Column(name = "respuesta", nullable = false, length = 20)
	@ApiModelProperty(value="Respuesta", dataType="String", example="Algo", position=2)
	private String respuesta;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "respuesta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PreguntaRespuesta> preguntas_respuestas = new ArrayList<>();
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "area", orphanRemoval = true)
	private List<RespuestaPorDefecto> respuestasPorDefecto = new ArrayList<>();

	public Respuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public String toStringArbol(){
		return "Respuesta: " + this.respuesta;
	}
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity @Table(name = "pregunta")
public class Pregunta implements Serializable {

	private static final long serialVersionUID = -1755610604523990931L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value="ID de pregunta", dataType="Long", example="1", position=1)
	private Long id;

	@Column(name = "pregunta", nullable = false, length = 150)
	@ApiModelProperty(value="Pregunta", dataType="String", example="�Damos becas?", position=2)
	private String pregunta;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@Column(name = "recomendacion", nullable = false, columnDefinition="TEXT")
	@ApiModelProperty(value="Recomendacion por pregunta", dataType="String", example="Dar m�s dinero para las becas", position=3)
	private String recomendacion;

	@Column(name = "puntuacion_recomendacion", nullable = false)
	@ApiModelProperty(value="Si la respuesta seleccionada tiene una puntuaci�n m�s alta que esta propiedad hay que mostrar la recomendaci�n por pregunta", dataType="int", example="5", position=4)
	private int puntuacionRecomendacion;

	@ToString.Exclude
	@OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PreguntaRespuesta> respuestas = new ArrayList<>();

	public Pregunta(String pregunta, Categoria categoria, String recomendacion, int puntuacionRecomendacion) {
		this.pregunta = pregunta;
		this.categoria = categoria;
		this.recomendacion = recomendacion;
		this.puntuacionRecomendacion = puntuacionRecomendacion;
	}
	
	public String toStringArbol(){
		return "Pregunta: " + this.pregunta + ", Puntuacion: " + this.puntuacionRecomendacion;
	}
}

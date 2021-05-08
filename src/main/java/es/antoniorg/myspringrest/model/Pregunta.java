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
	private Long id;

	@Column(name = "pregunta", nullable = false, length = 150)
	private String pregunta;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@Column(name = "recomendacion", nullable = false, columnDefinition="TEXT")
	private String recomendacion;

	@Column(name = "puntuacion_recomendacion", nullable = false)
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
		return "Pregunta: " + this.pregunta + ", Puntuación: " + this.puntuacionRecomendacion;
	}
}

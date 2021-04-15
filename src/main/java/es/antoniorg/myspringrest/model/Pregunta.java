package es.antoniorg.myspringrest.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@Column(name = "pregunta", nullable = false, length = 150)
	private String pregunta;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@Column(name = "recomendacion", nullable = false, columnDefinition="TEXT")
	private String recomendacion;

	@Column(name = "puntuacion_recomendacion", nullable = false)
	private int puntuacionRecomendacion;

	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "preguntas_tienen_respuestas", joinColumns = @JoinColumn(name = "pregunta_id"), inverseJoinColumns = @JoinColumn(name = "respuesta_id"))
	private Set<Respuesta> respuestas = new HashSet<>();

	public Pregunta(String pregunta, Categoria categoria, String recomendacion, int puntuacionRecomendacion) {
		this.pregunta = pregunta;
		this.categoria = categoria;
		this.recomendacion = recomendacion;
		this.puntuacionRecomendacion = puntuacionRecomendacion;
	}
}

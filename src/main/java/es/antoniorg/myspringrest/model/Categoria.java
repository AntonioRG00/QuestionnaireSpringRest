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
@Entity @Table(name = "categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 3470881883939542678L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value="ID de categoría", dataType="Long", example="1", position=1)
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 40)
	@ApiModelProperty(value="Nombre de la categoría", dataType="String", example="Nucleo 1.1", position=2)
	private String nombre;
	
	@Column(name = "descripcion", nullable = false, length = 200)
	@ApiModelProperty(value="Descripción de la categoría", dataType="String", example="Información laboral y econ�mica de la formación", position=3)
	private String descripcion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area area;
	
	@Column(name = "explicacion", nullable = false, length = 200)
	@ApiModelProperty(value="Explicación de la categoría", dataType="String", example="Sobre la información laboral y económica", position=4)
	private String explicacion;
	
	@Column(name = "puntuacion", nullable = false)
	@ApiModelProperty(value="Si todas las respuestas asociadas a la categor�a suman un n�mero m�s alto que esta propiedad se muestra la recomendación por categoría", dataType="int", example="5", position=5)
	private int puntuacion;
	
	@Column(name = "recomendacion", columnDefinition="TEXT")
	@ApiModelProperty(value="Recomendación", dataType="String", example="Reforzar estos puntos", position=6)
	private String recomendacion;

	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", orphanRemoval = true)
	private List<Pregunta> preguntas = new ArrayList<>();

	public Categoria(String nombre, String descripcion, Area area, String explicacion, int puntuacion, String recomendacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.area = area;
		this.explicacion = explicacion;
		this.puntuacion = puntuacion;
		this.recomendacion = recomendacion;
	}
	
	public String toStringArbol(){
		return "Categoria: " + this.nombre + ", Puntuacion: " + this.puntuacion;
	}
}
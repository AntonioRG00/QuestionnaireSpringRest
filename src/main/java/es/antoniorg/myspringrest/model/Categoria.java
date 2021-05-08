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
@Entity @Table(name = "categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 3470881883939542678L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area area;
	
	@Column(name = "explicacion", nullable = false, length = 200)
	private String explicacion;
	
	@Column(name = "puntuacion", nullable = false)
	private int puntuacion;
	
	@Column(name = "recomendacion", columnDefinition="TEXT")
	private String recomendacion;

	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", orphanRemoval = true)
	private List<Pregunta> preguntas = new ArrayList<Pregunta>();

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
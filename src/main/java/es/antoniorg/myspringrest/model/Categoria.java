package es.antoniorg.myspringrest.model;

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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor
@Entity @Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area area;
	
	@Column(name = "explicacion", nullable = false, length = 200)
	private String explicacion;
	
	@Column(name = "max_para_recomendacion", nullable = false)
	private int maxParaRecomendacion;
	
	@Column(name = "recomendacion", columnDefinition="TEXT")
	private String recomendacion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
	private List<Pregunta> preguntas = new ArrayList<Pregunta>();

	public Categoria(String nombre, String descripcion, Area area, String explicacion, int maxParaRecomendacion, String recomendacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.area = area;
		this.explicacion = explicacion;
		this.maxParaRecomendacion = maxParaRecomendacion;
		this.recomendacion = recomendacion;
	}
}
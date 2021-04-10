package es.antoniorg.myspringrest.model;

import java.util.ArrayList;
import java.util.List;

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
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area area;
	
	@Column(name = "explicacion")
	private String explicacion;
	
	@Column(name = "max_para_recomendacion")
	private int maxParaRecomendacion;
	
	@ManyToOne
	@JoinColumn(name = "id_recomendacion")
	private Recomendacion recomendacion;

	@OneToMany
	private List<Pregunta> preguntas = new ArrayList<Pregunta>();

	public Categoria(String nombre, String descripcion, Area area, String explicacion, int maxParaRecomendacion, Recomendacion recomendacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.area = area;
		this.explicacion = explicacion;
		this.maxParaRecomendacion = maxParaRecomendacion;
		this.recomendacion = recomendacion;
	}
}
package es.antoniorg.myspringrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name = "id_area")
	private Long idArea;
	
	@Column(name = "explicacion")
	private String explicacion;
	
	@Column(name = "max_para_recomendacion")
	private int maxParaRecomendacion;
	
	@Column(name = "id_recomendacion")
	private Long idRecomendacion;

	public Categoria(String nombre, String descripcion, Long idArea, String explicacion, int maxParaRecomendacion, Long idRecomendacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.idArea = idArea;
		this.explicacion = explicacion;
		this.maxParaRecomendacion = maxParaRecomendacion;
		this.idRecomendacion = idRecomendacion;
	}
}
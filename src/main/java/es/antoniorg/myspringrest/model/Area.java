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
@Entity @Table(name = "area")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "id_idioma")
	private Idioma idioma;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
	private List<Categoria> categorias = new ArrayList<Categoria>();

	public Area(String nombre, Idioma idioma) {
		this.nombre = nombre;
		this.idioma = idioma;
	}
}

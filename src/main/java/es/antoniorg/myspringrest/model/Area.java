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
@Entity @Table(name = "area")
public class Area implements Serializable {

	private static final long serialVersionUID = 2341129915698318755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_idioma")
	private Idioma idioma;
	
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
	private List<Categoria> categorias = new ArrayList<Categoria>();

	public Area(String nombre, Idioma idioma) {
		this.nombre = nombre;
		this.idioma = idioma;
	}
}

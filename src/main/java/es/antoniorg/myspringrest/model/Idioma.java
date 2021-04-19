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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor
@Entity @Table(name = "idioma")
public class Idioma implements Serializable {

	private static final long serialVersionUID = 3384608419515916422L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nombre_idioma", nullable = false, length = 50)
	private String nombre;
	
	@Column(name = "url_imagen", nullable = false, length = 250)
	private String urlImagen;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idioma")
	private List<Area> areas = new ArrayList<Area>();

	public Idioma(String nombreIdioma, String urlImagen) {
		this.nombre = nombreIdioma;
		this.urlImagen = urlImagen;
	}
}

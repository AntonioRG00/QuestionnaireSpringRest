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
@Entity @Table(name = "idioma")
public class Idioma {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nombre_idioma")
	private String nombreIdioma;
	
	@Column(name = "url_imagen")
	private String urlImagen;

	public Idioma(String nombreIdioma, String urlImagen) {
		this.nombreIdioma = nombreIdioma;
		this.urlImagen = urlImagen;
	}
}

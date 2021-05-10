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

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor @ToString
@Entity @Table(name = "idioma")
public class Idioma implements Serializable {

	private static final long serialVersionUID = 3384608419515916422L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition="SERIAL")
	@EqualsAndHashCode.Include
	@ApiModelProperty(value="ID del idioma", dataType="Long", example="1", position=1)
	private Long id;
	
	@Column(name = "nombre_idioma", nullable = false, length = 50)
	@ApiModelProperty(value="Nombre del idioma", dataType="String", example="España", position=2)
	private String nombre;
	
	@Column(name = "url_imagen", nullable = false, length = 250)
	@ApiModelProperty(value="UrlImagen de la bandera", dataType="Long", example="https://www.comprarbanderas.es/images/banderas/400/60-espana-sin-escudo_400px.jpg", position=3)
	private String urlImagen;
	
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idioma", orphanRemoval = true)
	private List<Area> areas = new ArrayList<Area>();

	public Idioma(String nombreIdioma, String urlImagen) {
		this.nombre = nombreIdioma;
		this.urlImagen = urlImagen;
	}
	
	public String toStringArbol(){
		return "Idioma: " + this.nombre;
	}
}

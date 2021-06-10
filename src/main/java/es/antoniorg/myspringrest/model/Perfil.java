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
@Entity @Table(name = "perfil")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 5174126953055590776L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value="ID de pregunta", dataType="Long", example="1", position=1)
	private Long id;
	
	@Column(name = "perfil", nullable = false, length = 40)
	@ApiModelProperty(value="Nombre del perfil", dataType="String", example="Alumno", position=2)
	private String perfil;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_idioma")
	private Idioma idioma;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pregunta> preguntas = new ArrayList<>();
	
	public Perfil(String perfil) {
		this.perfil = perfil;
	}

}

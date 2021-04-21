package es.antoniorg.myspringrest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true) @NoArgsConstructor @ToString
@Entity @Table(name = "respuesta")
public class Respuesta implements Serializable {

	private static final long serialVersionUID = 480089179196269564L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "respuesta", nullable = false, length = 20)
	private String respuesta;

	@OneToMany(mappedBy = "respuesta", cascade = CascadeType.ALL)
	private Set<PreguntaRespuesta> preguntas_respuestas = new HashSet<>();

	public Respuesta(String respuesta) {
		this.respuesta = respuesta;
	}	
}

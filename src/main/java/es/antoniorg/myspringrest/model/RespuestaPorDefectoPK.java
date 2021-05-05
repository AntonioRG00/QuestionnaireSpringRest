package es.antoniorg.myspringrest.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class RespuestaPorDefectoPK implements Serializable{
	
	private static final long serialVersionUID = 357307440151420320L;

	private Long respuesta;
	
	private Long area;
}

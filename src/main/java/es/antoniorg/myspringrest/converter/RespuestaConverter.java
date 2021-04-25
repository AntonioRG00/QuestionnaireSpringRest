package es.antoniorg.myspringrest.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import es.antoniorg.myspringrest.model.Respuesta;

@FacesConverter("respuestaConverter")
public class RespuestaConverter implements Converter<Respuesta>{
	private static Map<Respuesta, String> entities = new WeakHashMap<Respuesta, String>();

	@Override
	public Respuesta getAsObject(FacesContext context, UIComponent component, String value) {
		for (Entry<Respuesta, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Respuesta value) {
		synchronized (entities) {
			if (!entities.containsKey(value)) {
				if (value != null) {
					Respuesta respuesta = (Respuesta) value;
					entities.put(respuesta, respuesta.toString());
					return respuesta.toString();
				}
				return null;
			} else {
				return entities.get(value);
			}
		}
	}
}

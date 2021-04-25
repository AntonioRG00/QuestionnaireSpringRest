package es.antoniorg.myspringrest.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import es.antoniorg.myspringrest.model.Pregunta;

@FacesConverter("preguntaConverter")
public class PreguntaConverter implements Converter<Pregunta>{
	private static Map<Pregunta, String> entities = new WeakHashMap<Pregunta, String>();

	@Override
	public Pregunta getAsObject(FacesContext context, UIComponent component, String value) {
		for (Entry<Pregunta, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Pregunta value) {
		synchronized (entities) {
			if (!entities.containsKey(value)) {
				if (value != null) {
					Pregunta pregunta = (Pregunta) value;
					entities.put(pregunta, pregunta.toString());
					return pregunta.toString();
				}
				return null;
			} else {
				return entities.get(value);
			}
		}
	}
}

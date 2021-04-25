package es.antoniorg.myspringrest.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import es.antoniorg.myspringrest.model.Idioma;

@FacesConverter("idiomaConverter")
public class IdiomaConverter implements Converter<Idioma> {

	private static Map<Idioma, String> entities = new WeakHashMap<Idioma, String>();

	@Override
	public Idioma getAsObject(FacesContext context, UIComponent component, String value) {
		for (Entry<Idioma, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Idioma value) {
		synchronized (entities) {
			if (!entities.containsKey(value)) {
				if (value != null) {
					Idioma idioma = (Idioma) value;
					entities.put(idioma, idioma.toString());
					return idioma.toString();
				}
				return null;
			} else {
				return entities.get(value);
			}
		}
	}
}

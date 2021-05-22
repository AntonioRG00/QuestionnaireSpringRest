package es.antoniorg.myspringrest.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import es.antoniorg.myspringrest.model.Perfil;

@FacesConverter("perfilConverter")
public class PerfilConverter implements Converter<Perfil> {

	private static Map<Perfil, String> entities = new WeakHashMap<Perfil, String>();

	@Override
	public Perfil getAsObject(FacesContext context, UIComponent component, String value) {
		for (Entry<Perfil, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Perfil value) {
		synchronized (entities) {
			if (!entities.containsKey(value)) {
				if (value != null) {
					Perfil perfil = (Perfil) value;
					entities.put(perfil, perfil.toString());
					return perfil.toString();
				}
				return null;
			} else {
				return entities.get(value);
			}
		}
	}
}

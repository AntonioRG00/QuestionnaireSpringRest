package es.antoniorg.myspringrest.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import es.antoniorg.myspringrest.model.Categoria;

@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {

	private static Map<Categoria, String> entities = new WeakHashMap<Categoria, String>();

	@Override
	public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
		for (Entry<Categoria, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Categoria value) {
		synchronized (entities) {
			if (!entities.containsKey(value)) {
				if (value != null) {
					Categoria categoria = (Categoria) value;
					entities.put(categoria, categoria.toString());
					return categoria.toString();
				}
				return null;
			} else {
				return entities.get(value);
			}
		}
	}
}

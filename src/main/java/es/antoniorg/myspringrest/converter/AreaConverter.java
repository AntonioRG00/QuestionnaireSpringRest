package es.antoniorg.myspringrest.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import es.antoniorg.myspringrest.model.Area;

@FacesConverter("areaConverter")
public class AreaConverter implements Converter<Area> {

	private static Map<Area, String> entities = new WeakHashMap<Area, String>();

	@Override
	public Area getAsObject(FacesContext context, UIComponent component, String value) {
		for (Entry<Area, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Area value) {
		synchronized (entities) {
			if (!entities.containsKey(value)) {
				if (value != null) {
					Area area = (Area) value;
					entities.put(area, area.toString());
					return area.toString();
				}
				return null;
			} else {
				return entities.get(value);
			}
		}
	}
}


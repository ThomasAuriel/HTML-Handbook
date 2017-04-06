package notes.contentfolder.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Handbook {

	public List<Volume> volumes;
	/**
	 * Contain all elements sorted by id. Structure : <id, element>
	 */
	private Map<String, Object> mapId; //
	/**
	 * Contain all elements refering to a element which is represented by the
	 * id-key. Structure : <id, Map<title, elementsReferingToTheId>>
	 */
	private Map<String, Map<String, Object>> mapRefering;

	public Handbook() {
		volumes = new ArrayList<Volume>();
		mapId = new TreeMap<String, Object>();
		mapRefering = new TreeMap<String, Map<String, Object>>();
	}
	
	public void addIdElement(String id, Element element){
		mapId.put(id, new Element(element));
	}

	public void addReferingElement(String id, Element element) {
		if (!mapRefering.containsKey(id)) {
			mapRefering.put(id, new TreeMap<String,Object>());
		}
		mapRefering.get(id).put(element.shortTitle, new Element(element));
	}

}

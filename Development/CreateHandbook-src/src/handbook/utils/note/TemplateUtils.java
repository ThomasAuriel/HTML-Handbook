package handbook.utils.note;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import handbook.note.Note;
import handbook.utils.xml.XMLUtils;
import handbook.view.HandbookUI;

public class TemplateUtils {

	public static final String templatePath = "standard_template.html";
	public static Map<String, String> templates = new HashMap<String, String>();

	/**
	 * Load the template in the folder templatePath.
	 * 
	 * @throws Exception
	 */
	private static Document loadTemplate(String path) throws Exception {

		Document xmlElement;
		try {
			xmlElement = XMLUtils.parseXML("./templates" + File.separator + path);
		} catch (Exception e) {
			HandbookUI.addMessage("Impossible to parse the template : " + path
					+ " \nCheck that the template xml tags are correctly closed.");
			throw e;
		}

		return xmlElement;
	}

	/**
	 * Create a xml element according to the template structure. The element is
	 * define in the same document than the parent xml element if the parent exist.
	 * 
	 * @param note
	 * @throws Exception
	 */
	private static void createXMLNote(Note note) throws Exception {

		// Get the template path stored in the node else get the standard
		// template.
		String tpath = (note.template == null || note.template.isEmpty()) ? templatePath : note.template;

		// Load the template
		Document xmlElement = loadTemplate(tpath);
		note.xmlElement = (Element) xmlElement.getChildNodes().item(0);

	}

	// /**
	// * Link the {@link Note} xmlElement with the one from the parent
	// *
	// * @param note
	// */
	// private static void linkXMLElements(Note root, Note note) {
	// if (note.parent != null) {
	// Node adoptNode =
	// root.xmlElement.getOwnerDocument().adoptNode(note.xmlElement);
	// note.xmlElement = (Element) adoptNode;
	// }
	// }

	/**
	 * Link {@link Note} to their parents.
	 * 
	 * @param note
	 */
	private static void linkXMLElement(Note note) {
		Note parent = note.parent;
		if (note.parent != null) {
			Node adoptNode = parent.xmlElement.getOwnerDocument().adoptNode(note.xmlElement);
			note.xmlElement = (Element) adoptNode;
		}

		for (Note subnote : note.subContent) {
			linkXMLElement(subnote);
		}
	}

	/**
	 * Create the XML structure of the future note.
	 * 
	 * @throws Exception
	 */
	public static void createXMLNotes(Note root) throws Exception {

		// Create all the Note xmlElements
		for (Note note : Note.getAllNotes()) {
			createXMLNote(note);
		}

		// Link all the note xmlElements
		// for (Note note : Note.getAllNotes()) {
		// linkXMLElements(root, note);
		// }

		linkXMLElement(root);
	}

}

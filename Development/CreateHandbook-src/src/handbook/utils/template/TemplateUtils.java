package handbook.utils.template;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import handbook.note.Note;
import handbook.utils.file.FileUtils;
import handbook.utils.xml.XMLUtils;

public class TemplateUtils {

	private static final String templatePath = "standard_template.html";
	public static Map<String, String> templates = new HashMap<String, String>();

	/**
	 * Load the template in the folder templatePath.
	 * 
	 * @throws IOException
	 */
	public static String loadTemplate(String path) throws IOException {
		try {
			String template = FileUtils.getContentFile("./templates" + File.separator + path);
			if (template == null || template.isEmpty()) {
				throw new IOException("The template stored at " + templatePath + " is empty.");
			}
			return template;
		} catch (IOException e) {
			System.err.println("The template " + templatePath + " is not readable or absent or empty.");
			throw e;
		}
	}

	/**
	 * Create a xml element according to the template structure. The element is
	 * define in the same document than the parent xml element if the parent
	 * exist.
	 * 
	 * @param note
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void createXMLElementNote(Note note) throws ParserConfigurationException, SAXException, IOException {

		// Get the template path stored in the node else get the standard
		// template.
		String tpath = (note.template == null || note.template.isEmpty()) ? templatePath : note.template;

		// Load the template
		String template = loadTemplate(tpath);

		Document xmlElement = XMLUtils.parseXML(template);
		note.xmlElement = (Element) xmlElement.getChildNodes().item(0);
		if (note.parent != null) {
			Node adoptNode = note.parent.xmlElement.getOwnerDocument().adoptNode(note.xmlElement);
			note.xmlElement = (Element) adoptNode;
		}

	}

}

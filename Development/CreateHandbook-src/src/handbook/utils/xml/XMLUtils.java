package handbook.utils.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import handbook.note.Note;
import handbook.view.HandbookUI;

public class XMLUtils {

	/**
	 * Parse a string in XML
	 * 
	 * @param content
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document parseXML(String url) throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(url);
		return doc;
	}

	/**
	 * Save note XML element in a output file.
	 * 
	 * @param note
	 * @param output
	 * @throws TransformerException
	 */
	public static void writeFile(Note note, File output) throws TransformerException {
		try {

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.METHOD, "html");

			// Add new line after each balises. Easier to read.
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "0");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-16");

			DOMSource source = new DOMSource(note.xmlElement);
			StreamResult result = new StreamResult(output);

			transformer.transform(source, result);
		} catch (TransformerException e) {
			HandbookUI.addMessage("Impossible to convert the final note or impossible to write the file at : "
					+ output.getAbsolutePath());
			throw e;
		}
	}

}

package handbook.structure.note;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import handbook.file.FileUtils;
import handbook.file.FolderUtils;
import handbook.file.XMLUtils;
import handbook.structure.html.NoteElements;

public class Note {

	protected File filePath;
	protected Element element;
	protected List<String> tags;

	protected int indentation;
	protected Note parent;
	protected List<Note> subNotes = new ArrayList<Note>();

	protected static Document rootDocument;
	protected static Map<String, Note> idElementMap = new TreeMap<String, Note>();
	protected static Map<String, List<Note>> referingElementMap = new TreeMap<String, List<Note>>();

	/**
	 * Create a note and its tree of subnotes.
	 * 
	 * @param folder
	 * @param indentation
	 * @throws IOException
	 */
	public Note(File folder) {
		this(folder, FolderUtils.getFileWithExtension(folder, ".md").get(0), null);
	}

	/**
	 * Create a note and its tree of subnotes.
	 * 
	 * @param folder
	 * @param indentation
	 * @throws IOException
	 */
	public Note(File folder, File filePath, Note parent) {

		try {

			// Parse the content in XML
			Document document = XMLUtils.parseXML(FileUtils.getContentFile(filePath));
			// If the root document is null, then just parse it.
			if (rootDocument == null)
				rootDocument = document;

			// Import the document in the root document
			element = (Element) rootDocument.importNode(document.getDocumentElement(), true);

			// Define the parent:
			this.parent = parent;

			// Define the indentation
			if (parent == null) {
				this.indentation = 1;
			} else {
				this.indentation = parent.indentation + 1;
			}

			// Populate tags
			String rawTags = this.element.getAttribute(NoteElements.balise_attribute_tags);
			List<Object> tagsObject = FileUtils.parseArrayJSON(rawTags);

			tags = new ArrayList<String>();
			if (tagsObject != null && !tagsObject.isEmpty())
				for (Object obj : tagsObject) {
					if (obj instanceof String) {
						tags.add((String) obj);
					}
				}

			// Populate the maps
			idElementMap.put(this.getId(), this);
			for (String noteId : getTags()) {

				// Find the list of referring notes associated to a tags.
				List<Note> list = referingElementMap.get(noteId);
				// Create the list if does not exist.
				if (list == null) {
					list = new ArrayList<Note>();
					referingElementMap.put(noteId, list);
				}
				list.add(this);
			}

			// Find all subfolders
			List<File> folders = FolderUtils.getFolders(folder);
			for (File subfolder : folders) {

				// Get the subfiles in each subfolders
				List<File> subfilePaths = FolderUtils.getFileWithExtension(subfolder, ".md");

				for (File subfilePath : subfilePaths) {
					Note note = new Note(subfolder, subfilePath, this);
					subNotes.add(note);
				}
			}

		} catch (ParserConfigurationException e) {
			System.out.println("Impossible to parse the file [" + this.filePath.getAbsolutePath()
					+ "] in XML/HTML.\n Are you sure that the file structure is correct ?");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Impossible to parse the file [" + this.filePath.getAbsolutePath()
					+ "] in XML/HTML.\n Are you sure that the file structure is correct ?");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Impossible to parse the file [" + this.filePath.getAbsolutePath()
					+ "] in XML/HTML.\n Are you sure that the file structure is correct ?");
			e.printStackTrace();
		}

	}

	public File getFilePath() {
		return filePath;
	}

	public Element getElement() {
		return element;
	}

	public int getIndentation() {
		return indentation;
	}

	public List<Note> getSubNotes() {
		return subNotes;
	}

	public String getId() {
		return this.element.getAttribute(NoteElements.balise_attribute_id);
	}

	public String getTitle() {
		return this.element.getAttribute(NoteElements.balise_attribute_title);
	}

	public String getAuthor() {
		return this.element.getAttribute(NoteElements.balise_attribute_author);
	}

	public String getDate() {
		if (this.element.hasAttribute(NoteElements.balise_attribute_date))
			return this.element.getAttribute(NoteElements.balise_attribute_date);
		else if (this.element.hasAttribute(NoteElements.balise_attribute_activity))
			return this.element.getAttribute(NoteElements.balise_attribute_activity);
		return new String();
	}

	public String getDateFormat() {
		return this.element.getAttribute(NoteElements.balise_attribute_dateFormat);
	}

	public String getVersion() {
		return this.element.getAttribute(NoteElements.balise_attribute_version);
	}

	public List<String> getTags() {
		return tags;
	}

	public boolean isActivity() {
		return this.element.hasAttribute(NoteElements.balise_attribute_activity);
	}

	public String getActivity() {
		return this.element.getAttribute(NoteElements.balise_attribute_activity);
	}

	/*
	 * Sub elements
	 */

	/**
	 * Return the first calendar element.
	 * 
	 * @return
	 */
	public Element getCalendar() {
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode instanceof Element && childNode.getNodeName().equals(NoteElements.balise_Calendar))
				return (Element) childNode;
		}

		return null;
	}

	/*
	 * 
	 */

	public static Map<String, Note> getIdelementmap() {
		return idElementMap;
	}

	public static Document getRootDocument() {
		return rootDocument;
	}

}
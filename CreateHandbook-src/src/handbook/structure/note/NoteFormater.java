package handbook.structure.note;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import handbook.structure.html.HTMLElements;
import handbook.structure.html.NoteElements;
import handbook.structure.note.customstructure.CustomCalendar;

public class NoteFormater {

	/**
	 * Format the note.
	 */
	public static void formatNote(Note note) {
		NodeList childNodes = note.element.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);

			if (item instanceof Element) {
				if (item.getNodeName().equals(NoteElements.balise_Head)) {
					formatHeadline(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_ToC)) {
					formatToC(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Content)) {
					formatContent(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Subcontent)) {
					formatSubContent(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_ContentList)) {
					formatContentList(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Calendar)) {
					CustomCalendar.defineCustomCalendar(note, (Element) item);
				}
			}
		}

		// Clean the element
		cleanXML(note.element);
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);

			if (item instanceof Element && !((Element) item).getNodeName().equals(NoteElements.balise_Content)) {
				if (item.getNodeName().equals(NoteElements.balise_Head)
						|| item.getNodeName().equals(NoteElements.balise_ToC)
						|| item.getNodeName().equals(NoteElements.balise_Subcontent)
						|| item.getNodeName().equals(NoteElements.balise_ContentList)) {
					cleanXML(((Element) item));
				}
			}
		}

	}

	/**
	 * Format the headline element (classic and handbook element).
	 * 
	 * @param note
	 * @param headLine
	 */
	public static void formatHeadline(Note note, Element headLine) {
		if (note.element.getNodeName().equals(NoteElements.balise_Handbook))
			formatHandbookTitle(note, headLine);
		else
			formatElementHeadline(note, headLine);
	}

	/**
	 * Format the headline for a handbook element.
	 * 
	 * @param note
	 * @param headLine
	 */
	private static void formatHandbookTitle(Note note, Element headLine) {

		// Define the first page
		Element firstpage = Note.rootDocument.createElement("div");
		firstpage.setAttribute("class", HTMLElements.balise_class_firstPage);

		// Define the title
		Element title = Note.rootDocument.createElement("h1");
		title.appendChild(Note.rootDocument.createTextNode(note.getTitle()));
		title.setAttribute("class", HTMLElements.balise_class_firstPageTitle);
		firstpage.appendChild(title);

		// Define the author
		Element author = Note.rootDocument.createElement("h2");
		author.appendChild(Note.rootDocument.createTextNode(note.getAuthor()));
		author.setAttribute("class", HTMLElements.balise_class_firstPageAuthor);
		firstpage.appendChild(author);

		// Define the version
		String versionString = note.getVersion();
		if (!versionString.isEmpty()) {
			Element version = Note.rootDocument.createElement("h2");
			version.appendChild(Note.rootDocument.createTextNode(versionString));
			version.setAttribute("class", HTMLElements.balise_class_firstPageVersion);
			firstpage.appendChild(version);
		}

		// Define the date
		String dateString = NoteUtils.formatDate(note);
		if (!dateString.isEmpty()) {
			Element date = Note.rootDocument.createElement("h2");
			date.appendChild(Note.rootDocument.createTextNode(dateString));
			date.setAttribute("class", HTMLElements.balise_class_firstPageDate);
			firstpage.appendChild(date);
		}

		headLine.appendChild(firstpage);
	}

	/**
	 * Format the headline for a classic element.
	 * 
	 * @param parentElement
	 */
	private static void formatElementHeadline(Note note, Element headLine) {

		// Define the title
		headLine.appendChild(NoteUtils.createTitle(note));

		// Define the tag section
		Element tagList = NoteUtils.createTagList(note);
		if (tagList != null)
			headLine.appendChild(tagList);
	}

	/**
	 * Format the table of content of the note.
	 * 
	 * @param note
	 * @param tocElement
	 */
	public static void formatToC(Note note, Element tocElement) {

		// If no sub-element, no tables of content are added.
		if (note.subNotes.isEmpty()) {
			return;
		}

		// Define the title of the Table of Content
		Element tocTitle = Note.rootDocument.createElement("h" + Math.min(note.indentation + 1, 9));
		tocTitle.setAttribute("class", HTMLElements.balise_class_tocTitle);
		tocTitle.appendChild(Note.rootDocument.createTextNode("Table of Content"));
		tocElement.appendChild(tocTitle);

		// Define the toc list.
		Element toc = Note.rootDocument.createElement("ul");
		toc.setAttribute("class", HTMLElements.balise_class_toc);
		tocElement.appendChild(toc);

		// Determine the maxLevel
		int maxLevel = 1;
		try {
			String maxLevelString = tocElement.getAttribute("level");
			maxLevel = Integer.parseInt(maxLevelString);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("The table of content level is not specified. Assuming it is 1.");
		}

		// Define entries.
		for (Note subNote : note.subNotes) {
			Element tocEntry = formatToCEntry(subNote, 1, maxLevel);
			toc.appendChild(tocEntry);
		}

	}

	/**
	 * Create a toc entry.
	 * 
	 * @param note
	 * @return
	 */
	private static Element formatToCEntry(Note note, int level, int maxLevel) {

		Element tocEntry = Note.rootDocument.createElement("li");
		tocEntry.setAttribute("class", HTMLElements.balise_class_tocElement);
		tocEntry.appendChild(NoteUtils.createLink(note));

		// If the maxlevel of the toc is not reached and if the subnote list is
		// not empty then create thoses entries.
		if (level < maxLevel && !note.subNotes.isEmpty()) {
			// Define the sublist of entry
			Element tocSubList = Note.rootDocument.createElement("ul");
			tocSubList.setAttribute("level", Integer.toString(level + 1));

			// Define each entry
			for (Note subNote : note.subNotes) {
				Element subEntry = formatToCEntry(subNote, level + 1, maxLevel);
				tocSubList.appendChild(subEntry);
			}

			// Add the subentry to the entry
			tocEntry.appendChild(tocSubList);
		}

		return tocEntry;
	}

	public static void formatContentList(Note note, Element item) {

		// Create the list of note refering to this note.
		Element referingNotesTitle = Note.rootDocument.createElement("h" + (note.indentation + 1));
		referingNotesTitle.setAttribute("class", HTMLElements.balise_class_referingNotesTitle);
		referingNotesTitle.appendChild(Note.rootDocument.createTextNode("Content in the document"));
		Element referingNotesList = Note.rootDocument.createElement("ul");
		referingNotesList.setAttribute("class", HTMLElements.balise_class_referingNotesList);

		// Create the list of activity refering to this note.
		Element referingActivityTitle = Note.rootDocument.createElement("h" + (note.indentation + 1));
		referingActivityTitle.setAttribute("class", HTMLElements.balise_class_referingActivityTitle);
		referingActivityTitle.appendChild(Note.rootDocument.createTextNode("Activity in the document"));
		Element referingActivityList = Note.rootDocument.createElement("ul");
		referingActivityList.setAttribute("class", HTMLElements.balise_class_referingActivityList);

		List<Note> referringNotes = Note.referingElementMap.get(note.getId());
		if (referringNotes != null)
			for (Note referringNote : referringNotes) {

				Element listElement = Note.rootDocument.createElement("li");
				listElement.setAttribute("class", HTMLElements.balise_class_referingNotesListElement);
				listElement.appendChild(NoteUtils.createLink(referringNote));

				if (referringNote.isActivity()) {
					referingActivityList.appendChild(listElement);
				} else {
					referingNotesList.appendChild(listElement);
				}
			}

		// Insert the list only if it will not be empty
		if (referingNotesList.hasChildNodes()) {
			item.appendChild(referingNotesTitle);
			item.appendChild(referingNotesList);
		}
		if (referingActivityList.hasChildNodes()) {
			item.appendChild(referingActivityTitle);
			item.appendChild(referingActivityList);
		}

	}

	public static void formatContent(Note note, Element item) {
		item.setAttribute("class", HTMLElements.balise_class_markdown);
	}

	public static void formatSubContent(Note note, Element subcontent) {

		for (Note subNote : note.subNotes) {
			// Format the note HTML element
			formatNote(subNote);
			// Then add the formated element to the subcontent.
			subcontent.appendChild(subNote.element);
		}

	}

	/**
	 * Remove all text nodes of an element. This help to keep a compact xml/html
	 * document with only the interesting elements.
	 * 
	 * @param elementToClean
	 */
	public static void cleanXML(Element elementToClean) {

		NodeList childNodes = elementToClean.getChildNodes();
		List<Node> listNodeToRemove = new ArrayList<Node>();

		// Find all text nodes.
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);

			if (node.getNodeType() == Node.TEXT_NODE) {
				listNodeToRemove.add(node);
			}
		}

		// remove identified child to remove.
		for (Node node : listNodeToRemove) {
			elementToClean.removeChild(node);
		}

	}

}

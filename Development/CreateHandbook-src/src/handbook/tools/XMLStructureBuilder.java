package handbook.tools;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import handbook.note.HTMLElements;
import handbook.note.Note;
import handbook.note.NoteElements;
import handbook.utils.Utils;

public class XMLStructureBuilder {

	public static void createXMLStructure(Note note) {
		NodeList childNodes = note.xmlElement.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);

			if (item instanceof Element) {
				if (item.getNodeName().equals(NoteElements.balise_Head)) {
					formatHeadlineNode(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_ToC)) {
					formatTocNode(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Tags)) {
					formatTagNode(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Content)) {
					formatContentNode(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_References)) {
					formatReferenceNode(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Subcontent)) {
					formatSubContent(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Previous)) {
					formatPrevious(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_Next)) {
					formatNext(note, (Element) item);
					// } else if
					// (item.getNodeName().equals(NoteElements.balise_Calendar))
					// {
					// // CustomCalendar.defineCustomCalendar(note, (Element)
					// // item);
				}
			}
		}
	}

	private static void formatHeadlineNode(Note note, Node headlineNode) {

		if (note.parent != null) {
			createHeadline(note, headlineNode);
		} else {
			formatHandbookTitle(note, headlineNode);
		}
	}

	/**
	 * Format the headline for a handbook element.
	 * 
	 * @param note
	 * @param headlineNode
	 */
	private static void formatHandbookTitle(Note note, Node headlineNode) {

		// Define the first page
		Element firstpage = note.xmlElement.getOwnerDocument().createElement("div");
		firstpage.setAttribute("class", HTMLElements.balise_class_firstPage);

		// Define the title
		Element title = note.xmlElement.getOwnerDocument().createElement("h1");
		title.appendChild(note.xmlElement.getOwnerDocument().createTextNode(note.title));
		title.setAttribute("class", HTMLElements.balise_class_firstPageTitle);
		firstpage.appendChild(title);

		// Define the author
		String authorString = note.author;
		if (authorString != null) {
			Element author = note.xmlElement.getOwnerDocument().createElement("h2");
			author.appendChild(note.xmlElement.getOwnerDocument().createTextNode(authorString));
			author.setAttribute("class", HTMLElements.balise_class_firstPageAuthor);
			firstpage.appendChild(author);
		}

		// Define the version
		String versionString = note.version;
		if (versionString != null) {
			Element version = note.xmlElement.getOwnerDocument().createElement("h2");
			version.appendChild(note.xmlElement.getOwnerDocument().createTextNode(versionString));
			version.setAttribute("class", HTMLElements.balise_class_firstPageVersion);
			firstpage.appendChild(version);
		}

		// Define the date
		String dateString = note.date;
		if (dateString != null) {
			Element date = note.xmlElement.getOwnerDocument().createElement("h2");
			date.appendChild(note.xmlElement.getOwnerDocument().createTextNode(Utils.formatDate(dateString)));
			date.setAttribute("class", HTMLElements.balise_class_firstPageDate);
			firstpage.appendChild(date);
		}

		headlineNode.appendChild(firstpage);
	}

	private static void createHeadline(Note note, Node headlineNode) {
		// Create a link to the file
		Element fileLink = note.xmlElement.getOwnerDocument().createElement("a");
		fileLink.setAttribute("class", HTMLElements.balise_class_fileLinkTitle);
		fileLink.setAttribute("href", note.filePath.getPath());
		Element fileLogo = note.xmlElement.getOwnerDocument().createElement("img");
		fileLogo.setAttribute("src", "./css/file.svg");
		fileLogo.setAttribute("alt", "Open the file : " + note.filePath.getPath());
		fileLink.appendChild(fileLogo);

		// Create a fade link to allow to get the link of the title.
		Element fadeLink = note.xmlElement.getOwnerDocument().createElement("a");
		fadeLink.setAttribute("class", HTMLElements.balise_class_fadeLinkTitle);
		fadeLink.setAttribute("href", "#" + note.id);
		fadeLink.appendChild(note.xmlElement.getOwnerDocument().createTextNode("#"));

		// Create the title
		Element title = note.xmlElement.getOwnerDocument().createElement("h1");
		title.setAttribute("id", note.id);
		title.appendChild(fadeLink);
		title.appendChild(note.xmlElement.getOwnerDocument().createTextNode(note.title));
		title.appendChild(fileLink);

		headlineNode.appendChild(title);
	}

	private static void formatTocNode(Note note, Element toc) {

		if (note.tocLevel <= 0)
			return;

		int indentationLevel = 0;

		Element tocTitle = note.xmlElement.getOwnerDocument().createElement("h2");
		tocTitle.appendChild(note.xmlElement.getOwnerDocument().createTextNode("Table of Content"));
		toc.appendChild(tocTitle);

		fillToc(note, toc, indentationLevel, note.tocLevel);
	}

	private static void fillToc(Note note, Element toc, int indentationLevel, int indentationMax) {
		if (indentationLevel >= indentationMax || note.subContent.isEmpty())
			return;

		Element tocList = note.xmlElement.getOwnerDocument().createElement("ul");
		tocList.setAttribute("class", HTMLElements.balise_class_toc);
		toc.appendChild(tocList);

		for (Note subNote : note.subContent) {
			Element link = subNote.xmlElement.getOwnerDocument().createElement("a");
			link.setAttribute("href", "#" + subNote.id);
			link.appendChild(subNote.xmlElement.getOwnerDocument().createTextNode(subNote.title));
			Element tocEntry = subNote.xmlElement.getOwnerDocument().createElement("li");
			tocEntry.setAttribute("class", HTMLElements.balise_class_tocElement);
			tocEntry.appendChild(link);
			tocList.appendChild(tocEntry);

			fillToc(subNote, tocEntry, indentationLevel + 1, indentationMax);
		}
	}

	public static void formatTagNode(Note note, Node tag) {

		List<String> tags = note.tags;

		if (tags != null && !tags.isEmpty()) {

			// Add the tag list element
			Element tagList = note.xmlElement.getOwnerDocument().createElement("ul");
			tagList.setAttribute("class", HTMLElements.balise_class_tagList);

			for (String tagId : tags) {
				Note tagNote = Note.idMap.get(tagId);

				Element listElement = note.xmlElement.getOwnerDocument().createElement("li");
				listElement.setAttribute("class", HTMLElements.balise_class_tagListElement);
				Element tagLink;
				if (tagNote != null) {
					tagLink = createLink(tagNote);
				} else {
					tagLink = createLink(note.xmlElement.getOwnerDocument(), "#", "NoNote> " + tagId);
				}
				tagLink.setAttribute("class", HTMLElements.balise_class_tag);
				listElement.appendChild(tagLink);

				tagList.appendChild(listElement);
			}

			if (tagList.hasChildNodes())
				tag.appendChild(tagList);

		}
	}

	private static void formatContentNode(Note note, Element content) {
		content.setAttribute("class", HTMLElements.balise_class_markdown);
		content.appendChild(note.xmlElement.getOwnerDocument().createTextNode(note.content));
	}

	private static void formatReferenceNode(Note note, Element refering) {

		// Create the list of note refering to this note.
		Element referingNotesTitle = note.xmlElement.getOwnerDocument().createElement("h2");
		referingNotesTitle.setAttribute("class", HTMLElements.balise_class_referingNotesTitle);
		referingNotesTitle.appendChild(note.xmlElement.getOwnerDocument().createTextNode("Content in the document"));
		Element referingNotesList = note.xmlElement.getOwnerDocument().createElement("ul");
		referingNotesList.setAttribute("class", HTMLElements.balise_class_referingNotesList);

		// Create the list of activity refering to this note.
		Element referingActivityTitle = note.xmlElement.getOwnerDocument().createElement("h2");
		referingActivityTitle.setAttribute("class", HTMLElements.balise_class_referingActivityTitle);
		referingActivityTitle
				.appendChild(note.xmlElement.getOwnerDocument().createTextNode("Activity in the document"));
		Element referingActivityList = note.xmlElement.getOwnerDocument().createElement("ul");
		referingActivityList.setAttribute("class", HTMLElements.balise_class_referingActivityList);

		List<Note> referringNotes = Note.referingElementMap.get(note.id);
		if (referringNotes != null && !referringNotes.isEmpty())
			for (Note referringNote : referringNotes) {

				Element listElement = note.xmlElement.getOwnerDocument().createElement("li");
				listElement.setAttribute("class", HTMLElements.balise_class_referingNotesListElement);
				listElement.appendChild(createLink(referringNote));

				if (referringNote.activity) {
					referingActivityList.appendChild(listElement);
				} else {
					referingNotesList.appendChild(listElement);
				}
			}

		// Insert the list only if it will not be empty
		if (referingNotesList.hasChildNodes()) {
			refering.appendChild(referingNotesTitle);
			refering.appendChild(referingNotesList);
		}
		if (referingActivityList.hasChildNodes()) {
			refering.appendChild(referingActivityTitle);
			refering.appendChild(referingActivityList);
		}

	}

	private static void formatSubContent(Note note, Element item) {
		for (Note subnote : note.subContent) {
			createXMLStructure(subnote);
			item.appendChild(subnote.xmlElement);
		}
	}

	private static Element createLink(Note note) {
		return createLink(note.xmlElement.getOwnerDocument(), "#" + note.id, note.title);
	}

	private static Element createLink(Document ownerDocument, String url, String text) {
		Element link = ownerDocument.createElement("a");
		link.setAttribute("href", url);
		link.appendChild(ownerDocument.createTextNode(text));

		return link;
	}

	public static void formatPrevious(Note note, Node previous) {

		List<String> previousIds = note.previousElement;

		if (previousIds != null && !previousIds.isEmpty()) {

			// Add the tag list element
			Element previousList = note.xmlElement.getOwnerDocument().createElement("ul");
			previousList.setAttribute("class", HTMLElements.balise_class_previousList);

			for (String previousId : previousIds) {
				Note previousNote = Note.idMap.get(previousId);

				Element listElement = note.xmlElement.getOwnerDocument().createElement("li");
				listElement.setAttribute("class", HTMLElements.balise_class_previousListElement);
				Element previousLink;
				if (previousNote != null) {
					previousLink = createLink(previousNote);
				} else {
					previousLink = createLink(note.xmlElement.getOwnerDocument(), "#", "NoNote> " + previousId);
				}
				previousLink.setAttribute("class", HTMLElements.balise_class_previous);
				listElement.appendChild(previousLink);

				previousList.appendChild(listElement);
			}

			if (previousList.hasChildNodes())
				previous.appendChild(previousList);

		}
	}
	
	public static void formatNext(Note note, Node next) {

		List<String> nextIds = note.nextElement;

		if (nextIds != null && !nextIds.isEmpty()) {

			// Add the tag list element
			Element nextList = note.xmlElement.getOwnerDocument().createElement("ul");
			nextList.setAttribute("class", HTMLElements.balise_class_nextList);

			for (String nextId : nextIds) {
				Note nextNote = Note.idMap.get(nextId);

				Element listElement = note.xmlElement.getOwnerDocument().createElement("li");
				listElement.setAttribute("class", HTMLElements.balise_class_nextListElement);
				Element nextLink;
				if (nextNote != null) {
					nextLink = createLink(nextNote);
				} else {
					nextLink = createLink(note.xmlElement.getOwnerDocument(), "#", "NoNote> " + nextId);
				}
				nextLink.setAttribute("class", HTMLElements.balise_class_next);
				listElement.appendChild(nextLink);

				nextList.appendChild(listElement);
			}

			if (nextList.hasChildNodes())
				next.appendChild(nextList);

		}
	}

}

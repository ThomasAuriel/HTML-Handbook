package handbook.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
				} else if (item.getNodeName().equals(NoteElements.balise_Dashboard)) {
					formatDashboard(note, (Element) item);
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

		// Create the tas status
		if (note.taskStatus != null && !note.taskStatus.isEmpty()) {
			Element status = note.xmlElement.getOwnerDocument().createElement("div");
			status.setAttribute("class", "status-" + note.taskStatus);
			status.appendChild(note.xmlElement.getOwnerDocument().createTextNode(note.taskStatus));
			headlineNode.appendChild(status);
		}
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

		String baliseClass = HTMLElements.balise_class_tag;
		String baliseClassList = HTMLElements.balise_class_tagList;
		String baliseClassListelement = HTMLElements.balise_class_tagListElement;
		formatList(note, tag, note.tags, baliseClass, baliseClassList, baliseClassListelement);

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

	public static void formatPrevious(Note note, Node previous) {

		String baliseClass = HTMLElements.balise_class_previous;
		String baliseClassList = HTMLElements.balise_class_previousList;
		String baliseClassListelement = HTMLElements.balise_class_previousListElement;
		// formatList(note, previous, note.previousElements, baliseClass,
		// baliseClassList, baliseClassListelement);
		// Need a custom previous list:
		if (note.previousElements == null || note.previousElements.isEmpty())
			return;

		// Add the list element
		Element elementList = note.xmlElement.getOwnerDocument().createElement("ul");
		elementList.setAttribute("class", baliseClassList);

		for (String previousId : note.previousElements) {
			Note previousTask = Note.idMap.get(previousId);

			Element listElement = note.xmlElement.getOwnerDocument().createElement("li");
			listElement.setAttribute("class", baliseClassListelement + "-" + previousTask.taskStatus);
			Element nextLink;
			if (previousTask != null) {
				nextLink = createLink(previousTask);
			} else {
				nextLink = createLink(note.xmlElement.getOwnerDocument(), "#", "NoNote> " + previousId);
			}
			nextLink.setAttribute("class", baliseClass);

			listElement.appendChild(nextLink);
			elementList.appendChild(listElement);
		}

		if (elementList.hasChildNodes())
			previous.appendChild(elementList);

	}

	public static void formatNext(Note note, Node next) {

		String baliseClass = HTMLElements.balise_class_next;
		String baliseClassList = HTMLElements.balise_class_nextList;
		String baliseClassListelement = HTMLElements.balise_class_nextListElement;
		// formatList(note, next, note.nextElements, baliseClass, baliseClassList,
		// baliseClassListelement);
		// Need a custom previous list:
		if (note.nextElements == null || note.nextElements.isEmpty())
			return;

		// Add the list element
		Element elementList = note.xmlElement.getOwnerDocument().createElement("ul");
		elementList.setAttribute("class", baliseClassList);

		for (String nextId : note.nextElements) {
			Note nextTask = Note.idMap.get(nextId);

			Element listElement = note.xmlElement.getOwnerDocument().createElement("li");
			listElement.setAttribute("class", baliseClassListelement + "-" + nextTask.taskStatus);
			Element nextLink;
			if (nextTask != null) {
				nextLink = createLink(nextTask);
			} else {
				nextLink = createLink(note.xmlElement.getOwnerDocument(), "#", "NoNote> " + nextId);
			}
			nextLink.setAttribute("class", baliseClass);

			listElement.appendChild(nextLink);
			elementList.appendChild(listElement);
		}

		if (elementList.hasChildNodes())
			next.appendChild(elementList);
	}

	private static void formatDashboard(Note note, Node item) {

		Set<String> activeTaskIds = note.activeTasks;
		Set<String> waitingTaskIds = note.waitingTasks;
		Set<String> doneTaskIds = note.doneTasks;

		String baliseClass = HTMLElements.balise_class_dashboard;
		String baliseClassList = HTMLElements.balise_class_dashboardList;
		String baliseClassListelement = HTMLElements.balise_class_dashboardListElement;
		List<String> activeList = new ArrayList<String>();
		activeList.addAll(activeTaskIds);
		List<String> waitingList = new ArrayList<String>();
		waitingList.addAll(waitingTaskIds);
		List<String> doneList = new ArrayList<String>();
		doneList.addAll(doneTaskIds);

		formatList(note, item, activeList, baliseClass + "-active", baliseClassList + "-active",
				baliseClassListelement + "-active");
		formatList(note, item, waitingList, baliseClass + "-waiting", baliseClassList + "-waiting",
				baliseClassListelement + "-waiting");
		formatList(note, item, doneList, baliseClass + "-done", baliseClassList + "-done",
				baliseClassListelement + "-done");

	}

	/**
	 * Create Link
	 * 
	 * @param note
	 * @return
	 */
	private static Element createLink(Note note) {
		return createLink(note.xmlElement.getOwnerDocument(), "#" + note.id, note.title);
	}

	/**
	 * Create link
	 * 
	 * @param ownerDocument
	 * @param url
	 * @param text
	 * @return
	 */
	private static Element createLink(Document ownerDocument, String url, String text) {
		Element link = ownerDocument.createElement("a");
		link.setAttribute("href", url);
		link.appendChild(ownerDocument.createTextNode(text));

		return link;
	}

	/**
	 * Format a list
	 * 
	 * @param note
	 *            root note
	 * @param includingElement
	 *            xmlElement which will contain the lsit
	 * @param includedIds
	 *            note's ids to include
	 * @param baliseClass
	 * @param baliseClassList
	 * @param baliseClassListelement
	 */
	private static void formatList(Note note, Node includingElement, List<String> includedIds, String baliseClass,
			String baliseClassList, String baliseClassListelement) {

		if (includedIds == null || includedIds.isEmpty())
			return;

		// Add the list element
		Element elementList = note.xmlElement.getOwnerDocument().createElement("ul");
		elementList.setAttribute("class", baliseClassList);

		for (String includeId : includedIds) {
			Note nextNote = Note.idMap.get(includeId);

			Element listElement = note.xmlElement.getOwnerDocument().createElement("li");
			listElement.setAttribute("class", baliseClassListelement);
			Element nextLink;
			if (nextNote != null) {
				nextLink = createLink(nextNote);
			} else {
				nextLink = createLink(note.xmlElement.getOwnerDocument(), "#", "NoNote> " + includeId);
			}
			nextLink.setAttribute("class", baliseClass);

			listElement.appendChild(nextLink);
			elementList.appendChild(listElement);
		}

		if (elementList.hasChildNodes())
			includingElement.appendChild(elementList);
	}

}

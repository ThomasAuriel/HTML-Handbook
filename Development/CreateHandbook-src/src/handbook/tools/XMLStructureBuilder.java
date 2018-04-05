package handbook.tools;

import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import handbook.note.Note;
import handbook.note.NoteElements;
import handbook.utils.Utils;
import handbook.utils.note.TaskUtil;
import handbook.utils.note.TemplateUtils;
import handbook.view.HandbookUI;

public class XMLStructureBuilder {

	public static void createXMLElements(Note root) throws Exception {
		TemplateUtils.createXMLNotes(root);
	}

	/**
	 * 
	 * @param note
	 */
	public static void completeXMLElements() {
		for (Note note : Note.getAllNotes()) {
			completeXMLElement(note);
		}
	}

	private static void completeXMLElement(Note note) {

		// Add the id of the note
		addID(note, note.xmlElement);

		NodeList childNodes = note.xmlElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);

			if (item instanceof Element) {
				if (item.getNodeName().equals(NoteElements.balise_attribute_titleNote)) {
					addTitle(note, (Element) item);

				} else if (item.getNodeName().equals(NoteElements.balise_attribute_author)) {
					addAuthor(note, (Element) item);

				} else if (item.getNodeName().equals(NoteElements.balise_attribute_version)) {
					addVersion(note, (Element) item);

				} else if (item.getNodeName().equals(NoteElements.balise_attribute_date)) {
					addDate(note, (Element) item);

				} else if (item.getNodeName().equals(NoteElements.balise_attribute_headline)) {
					// Contain headline: title, author, version, date
					addHeadline(note, (Element) item);

				} else if (item.getNodeName().equals(NoteElements.balise_attribute_tags)) {
					addTags(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_attribute_reference_tasks)) {
					addTasks(note, (Element) item);

				} else if (item.getNodeName().equals(NoteElements.balise_attribute_toc)) {
					addToC(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_attribute_dashboard)) {
					addDashboard(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_attribute_content)) {
					addContent(note, (Element) item);
				} else if (item.getNodeName().equals(NoteElements.balise_attribute_reference_notes)) {
					addReference(note, (Element) item);

				} else if (item.getNodeName().equals(NoteElements.balise_attribute_subcontent)) {
					addSubContent(note, (Element) item);
					// } else if
					// (item.getNodeName().equals(NoteElements.balise_Calendar))
					// {
					// // CustomCalendar.defineCustomCalendar(note, (Element)
					// // item);

				}
			}
		}

	}

	/**
	 * Add the id of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param rootNode
	 */
	private static void addID(Note note, Element rootNode) {
		rootNode.setAttribute("id", note.id);
	}

	/**
	 * Add the title of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param titleNode
	 */
	private static void addTitle(Note note, Element titleNode) {
		// Title
		String formatedTitle = "# " + getLink(note);
		Text titleText = note.xmlElement.getOwnerDocument().createTextNode(formatedTitle);

		Element title = note.xmlElement.getOwnerDocument().createElement("div");
		title.setAttribute("class", NoteElements.balise_attribute_title + " markdown");
		title.appendChild(titleText);
		titleNode.appendChild(title);

		// Link
		String formatedFilepath = " [![](./handbook-config/css/file.svg)](" + note.markdownFile.getPath().replace(" ", "%20") + ") ";
		Text linkText = note.xmlElement.getOwnerDocument().createTextNode(formatedFilepath);

		Element link = note.xmlElement.getOwnerDocument().createElement("div");
		link.setAttribute("class", NoteElements.balise_attribute_filepath + " markdown");
		link.appendChild(linkText);
		titleNode.appendChild(link);

	}

	/**
	 * Add the author of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param titleNode
	 */
	private static void addAuthor(Note note, Element authorNode) {
		String formatedAuthor = "## " + note.author;
		Text authorText = note.xmlElement.getOwnerDocument().createTextNode(formatedAuthor);
		authorNode.setAttribute("class", NoteElements.balise_attribute_author + " markdown");
		authorNode.appendChild(authorText);
	}

	/**
	 * Add the version of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param titleNode
	 */
	private static void addVersion(Note note, Element versionNode) {
		String formatedVersion = "## " + note.version;
		Text versionText = note.xmlElement.getOwnerDocument().createTextNode(formatedVersion);
		versionNode.setAttribute("class", NoteElements.balise_attribute_version + " markdown");
		versionNode.appendChild(versionText);
	}

	/**
	 * Add the date of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param date
	 */
	private static void addDate(Note note, Element date) {
		String formatedDate = "## " + Utils.formatDate(note.date);
		Text dateText = note.xmlElement.getOwnerDocument().createTextNode(formatedDate);
		date.setAttribute("class", NoteElements.balise_attribute_date + " markdown");
		date.appendChild(dateText);
	}

	/**
	 * Add the headline of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param titleNode
	 */
	private static void addHeadline(Note note, Element headlineNode) {

		// Add title
		if (note.title != null || !note.title.isEmpty()) {
			Element title = note.xmlElement.getOwnerDocument().createElement("div");
			headlineNode.appendChild(title);

			addTitle(note, title);
		}

		// Add author
		if (note.author != null || !note.author.isEmpty()) {
			Element author = note.xmlElement.getOwnerDocument().createElement("div");
			headlineNode.appendChild(author);

			addAuthor(note, author);
		}

		// Add version
		if (note.version != null || !note.version.isEmpty()) {
			Element version = note.xmlElement.getOwnerDocument().createElement("div");
			headlineNode.appendChild(version);

			addVersion(note, version);
		}

		// Add date
		if (note.date != null || !note.date.isEmpty()) {
			Element date = note.xmlElement.getOwnerDocument().createElement("div");
			headlineNode.appendChild(date);

			addDate(note, date);
		}

	}

	/**
	 * Add the tags of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param tocNode
	 */
	private static void addTags(Note note, Element tagNode) {

		if (note.tags.isEmpty())
			return;

		createListLink(note, note.tags, tagNode, NoteElements.balise_attribute_tags + " markdown");
	}

	/**
	 * Add the previous task of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param tocNode
	 */
	private static void addTasks(Note note, Element taskNote) {

		// Task status
		if (TaskUtil.isTask(note)) {
			Text textNode = note.xmlElement.getOwnerDocument().createTextNode(TaskUtil.getTaskStatus(note));
			Element taskStatus = note.xmlElement.getOwnerDocument().createElement("div");
			taskStatus.setAttribute("class", "status " + TaskUtil.getTaskStatus(note));
			taskStatus.appendChild(textNode);
			taskNote.appendChild(taskStatus);
		}

		// previous tasks
		if (!note.previousElements.isEmpty()) {

			// ul
			Element taskUL = note.xmlElement.getOwnerDocument().createElement("ul");
			taskUL.setAttribute("class", "previous");
			taskNote.appendChild(taskUL);

			for (String targetedNote : note.previousElements) {
				Note targetedTaskNote = Note.getNote(targetedNote);
				Text taskLinkText;
				try {
					taskLinkText = taskNote.getOwnerDocument().createTextNode(targetedTaskNote.title);
				} catch (Exception ex) {
					HandbookUI.addMessage("The note [" + targetedTaskNote
							+ "] is not defined. This note is refered by [" + note.title + "].");
					taskLinkText = taskNote.getOwnerDocument().createTextNode("No note [" + targetedNote + "]");
				}
				// a
				Element taskA = note.xmlElement.getOwnerDocument().createElement("a");
				taskA.setAttribute("class", TaskUtil.getTaskStatus(targetedTaskNote));
				taskA.setAttribute("href", "#" + targetedTaskNote.id);
				taskA.appendChild(taskLinkText);
				// li
				Element taskLI = note.xmlElement.getOwnerDocument().createElement("li");
				taskLI.appendChild(taskA);
				taskUL.appendChild(taskLI);

			}
		}

		// Next tasks
		if (!note.nextElements.isEmpty()) {

			// ul
			Element taskUL = note.xmlElement.getOwnerDocument().createElement("ul");
			taskUL.setAttribute("class", "next");
			taskNote.appendChild(taskUL);

			for (String targetedNote : note.nextElements) {
				Note targetedTaskNote = Note.getNote(targetedNote);
				Text taskLinkText;
				try {
					taskLinkText = taskNote.getOwnerDocument().createTextNode(targetedTaskNote.title);
				} catch (Exception ex) {
					HandbookUI.addMessage("The note [" + targetedTaskNote
							+ "] is not defined. This note is refered by [" + note.title + "].");
					taskLinkText = taskNote.getOwnerDocument().createTextNode("No note [" + targetedNote + "]");
				}
				// a
				Element taskA = note.xmlElement.getOwnerDocument().createElement("a");
				taskA.setAttribute("class", TaskUtil.getTaskStatus(targetedTaskNote));
				taskA.setAttribute("href", "#" + targetedTaskNote.id);
				taskA.appendChild(taskLinkText);
				// li
				Element taskLI = note.xmlElement.getOwnerDocument().createElement("li");
				taskLI.appendChild(taskA);
				taskUL.appendChild(taskLI);

			}
		}

	}

	/**
	 * Add the toc of a {@link Note} to an {@link Element}
	 * 
	 * @param note
	 * @param tocNode
	 */
	private static void addToC(Note note, Element tocNode) {

		// Does not add toc if the current note does not require one.
		if (note.tocLevel <= 0)
			return;

		String toc = fillToc(note, "\n", 0, note.tocLevel);

		Text tocText = note.xmlElement.getOwnerDocument().createTextNode(toc);
		tocNode.setAttribute("class", NoteElements.balise_attribute_toc + " markdown");
		tocNode.appendChild(tocText);
	}

	/**
	 * Fill the toc string provided with the toc structure based on the note
	 * provided.
	 * 
	 * @param note
	 * @param toc
	 * @param indentationLevel
	 * @param indentationMax
	 * @return
	 */
	private static String fillToc(Note note, String toc, int indentationLevel, int indentationMax) {

		// If the current level is lower than the maxIndentation, then create the
		// associated toc
		if (indentationLevel < indentationMax)

			for (Note subNote : note.subContent) {

				String indentation = new String();
				for (int i = 0; i < indentationLevel; i++) {
					indentation += "  ";
				}

				String tocEntry = indentation + "- [" + subNote.title + "](#" + subNote.id + ")" + "\n";
				toc += tocEntry;

				toc = fillToc(subNote, toc, indentationLevel + 1, indentationMax);
			}

		return toc;

	}

	/**
	 * 
	 * @param note
	 * @param subcontentNode
	 */
	private static void addDashboard(Note note, Element dashboardElement) {

		if (note.dashboardLevel <= 0)
			return;

		// Active
		Element active = note.xmlElement.getOwnerDocument().createElement("div");
		createListLink(note, note.dashboardActiveTasks, active,
				NoteElements.balise_attribute_dashboard_active + " markdown");
		dashboardElement.appendChild(active);
		// Waiting
		Element waiting = note.xmlElement.getOwnerDocument().createElement("div");
		createListLink(note, note.dashboardWaitingTasks, waiting,
				NoteElements.balise_attribute_dashboard_waiting + " markdown");
		dashboardElement.appendChild(waiting);
		// Done
		Element done = note.xmlElement.getOwnerDocument().createElement("div");
		createListLink(note, note.dashboardDoneTasks, done, NoteElements.balise_attribute_dashboard_done + " markdown");
		dashboardElement.appendChild(done);

	}

	/**
	 * 
	 * @param note
	 * @param contentNode
	 */
	private static void addContent(Note note, Element contentNode) {
		Text content = contentNode.getOwnerDocument().createTextNode(note.content);
		contentNode.appendChild(content);
		contentNode.setAttribute("class", NoteElements.balise_attribute_content + " markdown");
	}

	private static void addReference(Note note, Element refering) {

		// Add references
		Element reference = note.xmlElement.getOwnerDocument().createElement("div");
		createListLink(note, note.references, reference, NoteElements.balise_attribute_reference_notes + " markdown");
		refering.appendChild(reference);

		// Add activity
		Element activity = note.xmlElement.getOwnerDocument().createElement("div");
		createListLink(note, note.activity, activity, NoteElements.balise_attribute_reference_activities + " markdown");
		refering.appendChild(activity);

		// Add tasks
		Element task = note.xmlElement.getOwnerDocument().createElement("div");
		createListLink(note, note.tasks, task, NoteElements.balise_attribute_reference_tasks + " markdown");
		refering.appendChild(task);

	}

	/**
	 * 
	 * @param note
	 * @param subcontentNode
	 */
	private static void addSubContent(Note note, Element subcontentNode) {
		for (Note subnote : note.subContent) {
			subcontentNode.appendChild(subnote.xmlElement);
			subcontentNode.setAttribute("class", NoteElements.balise_attribute_subcontent);
		}
	}

	private static String getLink(String id) {

		Note targetNode = Note.getNote(id);
		if (targetNode == null)
			return "No Note for [" + id + "]";
		else
			return getLink(targetNode);
	}

	private static String getLink(Note targetNode) {
		return "[" + targetNode.title + "](#" + targetNode.id + ")";
	}

	private static void createListLink(Note note, Set<String> noteList, Element containingElement, String className) {

		String finalLinkList = "\n";
		for (String targetedNote : noteList) {
			String linkFormated = "";
			try {
				linkFormated = "- " + getLink(targetedNote);
			} catch (Exception ex) {
				HandbookUI.addMessage("The note [" + targetedNote + "] is not defined. This note is refered by ["
						+ note.title + "].");
				linkFormated = "- No note [" + targetedNote + "]";
			}
			finalLinkList += linkFormated + "\n";
		}

		Text noteListText = containingElement.getOwnerDocument().createTextNode(finalLinkList);
		containingElement.setAttribute("class", className);
		containingElement.appendChild(noteListText);

	}

}

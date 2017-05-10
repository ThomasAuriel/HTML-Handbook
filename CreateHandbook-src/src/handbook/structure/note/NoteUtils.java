package handbook.structure.note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import handbook.file.FileUtils;
import handbook.structure.html.HTMLElements;
import handbook.structure.html.NoteElements;

public class NoteUtils {

	/**
	 * Create a link to a note.
	 * 
	 * @param note
	 * @return
	 */
	public static Element createLink(Note note) {
		Element link = Note.rootDocument.createElement("a");
		link.setAttribute("href", "#" + note.getId());
		link.appendChild(Note.rootDocument.createTextNode(note.getTitle()));
		return createLink("#" + note.getId(), note.getTitle());
	}

	/**
	 * Create a link to a note.
	 * 
	 * @param note
	 * @return
	 */
	public static Element createLink(String ref, String text) {
		Element link = Note.rootDocument.createElement("a");
		link.setAttribute("href", ref);
		link.appendChild(Note.rootDocument.createTextNode(text));
		return link;
	}

	/**
	 * Create a note title which respect it indentation level. The indentation
	 * will be limited to 9 to respect HTML balises.
	 * 
	 * @param note
	 * @return
	 */
	public static Element createTitle(Note note) {

		// Create a fade link to allow to get the link of the title.
		Element fadeLink = Note.rootDocument.createElement("a");
		fadeLink.setAttribute("class", HTMLElements.balise_class_fadeLinkTitle);
		fadeLink.setAttribute("href", "#" + note.getId());
		fadeLink.appendChild(Note.rootDocument.createTextNode("#"));

		// Create the title
		Element title = Note.rootDocument.createElement("h" + Math.min(note.indentation, 9));
		title.appendChild(fadeLink);
		title.appendChild(Note.rootDocument.createTextNode(note.getTitle()));

		return title;
	}

	/**
	 * Format tags
	 * 
	 * @param note
	 */
	public static Element createTagList(Note note) {

		String tagString = note.element.getAttribute(NoteElements.balise_attribute_tags);
		List<Object> tagIds = FileUtils.parseArrayJSON(tagString);

		if (tagIds != null) {

			// Add the tag list element
			Element tagList = Note.rootDocument.createElement("ul");
			tagList.setAttribute("class", HTMLElements.balise_class_tagList);

			for (Object tagId : tagIds) {
				Note tagNote = Note.idElementMap.get(tagId);

				Element listElement = Note.rootDocument.createElement("li");
				listElement.setAttribute("class", HTMLElements.balise_class_tagListElement);
				Element tagLink;
				if (tagNote != null) {
					tagLink = createLink(tagNote);
				} else {
					tagLink = createLink("#", "NoNote>" + tagId);
				}
				tagLink.setAttribute("class", HTMLElements.balise_class_tag);
				listElement.appendChild(tagLink);

				tagList.appendChild(listElement);
			}
			
			return tagList.hasChildNodes() ? tagList : null;

		}

		return null;
	}

	/**
	 * Format the date contain in a note attribute.
	 * 
	 * @param note
	 * @return return a date string.
	 */
	public static String formatDate(Note note) {

		String stringDate = note.getDate();
		String stringDateFormat = note.getDateFormat();

		boolean containDate = stringDate != null && !stringDate.isEmpty();
		boolean containDateFormat = stringDateFormat != null && !stringDateFormat.isEmpty();

		if (containDate) {
			// Return the textual date
			return stringDate;
		} else if (containDateFormat) {
			// Return the current date formated.
			SimpleDateFormat sdf = new SimpleDateFormat(stringDateFormat);
			return sdf.format(new Date());
		}

		return new String();
	}
}

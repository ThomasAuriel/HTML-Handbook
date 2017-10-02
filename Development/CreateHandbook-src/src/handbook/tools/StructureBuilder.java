package handbook.tools;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ho.yaml.Yaml;

import handbook.note.Note;
import handbook.note.NoteElements;
import handbook.utils.Utils;
import handbook.utils.file.FileUtils;
import handbook.utils.file.FolderUtils;
import handbook.utils.template.TemplateUtils;
import handbook.view.HandbookUI;

public class StructureBuilder {

	/*
	 * \\A: must be at the begging [\\s]*: can be preceded by white-space characters
	 * but not by text character `{3,}: must be preceded by three ` characters [
	 * \\s: white-space \\S: any-character ]*: can contain any characters `{3,}:
	 * must be followed by three ` characters
	 */
	private final static String defaultRegexMetadata = "\\A[\\s]*`{3,}([\\s\\S]*?)`{3,}";
	private final static Pattern pattern = Pattern.compile(defaultRegexMetadata);

	/**
	 * Create the note structure from a root note. This element will be handle as
	 * the handbook.
	 * 
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public static Note getStructure(File folder) throws Exception {
		List<File> markdownFiles = FolderUtils.getFileWithExtension(folder, ".md");
		File markdownFile = markdownFiles.get(0);

		// Create the node based on the file
		Note note = createNote(null, markdownFile);

		// Create the xml element
		TemplateUtils.createXMLElementNote(note);

		// Determine the subcontent
		List<File> subFolders = FolderUtils.getFolders(folder);
		for (int j = 0; j < subFolders.size(); j++) {
			File subFolder = subFolders.get(j);
			setStructure(subFolder, note);
		}
		return note;
	}

	/**
	 * Create notes recursively.
	 * 
	 * @param folder
	 * @param parent
	 * @throws Exception
	 */
	private static void setStructure(File folder, Note parent) throws Exception {
		List<File> markdownFiles = FolderUtils.getFileWithExtension(folder, ".md");

		for (int i = 0; i < markdownFiles.size(); i++) {
			File markdownFile = markdownFiles.get(i);

			// Create the node based on the file
			Note note = createNote(parent, markdownFile);

			// And store it in the parent if it exists
			if (parent != null) {
				parent.subContent.add(note);
			}

			// Create the xml element
			TemplateUtils.createXMLElementNote(note);

			// Determine the subcontent
			List<File> subFolders = FolderUtils.getFolders(folder);
			for (int j = 0; j < subFolders.size(); j++) {
				File subFolder = subFolders.get(j);
				setStructure(subFolder, note);
			}

			// System.err.println("Impossible to read the file : " +
			// markdownFile.getPath());

		}

	}

	/**
	 * Create a note from a markdown file.
	 * 
	 * @param parent
	 * @param markdownFile
	 * @return
	 * @throws Exception
	 */
	private static Note createNote(Note parent, File markdownFile) throws Exception {
		String contentFile = FileUtils.getContentFile(markdownFile);

		// Extract metadata
		String metadata = extractMetadata(contentFile);
		// Extract content
		String content = extractContent(contentFile);
		content = Utils.formatPath(content, markdownFile);

		// Convert YAML structure
		// and get the different elements
		Map<String, Object> yamlElements = null;
		try {
			yamlElements = Yaml.loadType(metadata, HashMap.class);
		} catch (Exception ex) {
			HandbookUI.addMessage("An error occured during reading metadata of the file " + markdownFile.getPath());
			throw ex;
		}

		String title = (String) yamlElements.get(NoteElements.balise_attribute_title);
		// IDs
		String id = (String) yamlElements.get(NoteElements.balise_attribute_id);
		if (id == null || id.isEmpty())
			id = title;
		String template = (String) yamlElements.get(NoteElements.balise_attribute_template);
		// Tags
		List<String> tags = (List<String>) yamlElements.get(NoteElements.balise_attribute_tags);

		// ToC level
		Integer tocLevel = (Integer) yamlElements.get(NoteElements.balise_attribute_toc);
		if (tocLevel == null)
			tocLevel = 0;

		String author = (String) yamlElements.get(NoteElements.balise_attribute_author);
		String date = (String) yamlElements.get(NoteElements.balise_attribute_date);
		String version = (String) yamlElements.get(NoteElements.balise_attribute_version);

		String activityDate = (String) yamlElements.get(NoteElements.balise_attribute_activity);

		List<String> previous = (List<String>) yamlElements.get(NoteElements.balise_attribute_previous);

		// Create the note
		return new Note(markdownFile, parent, title, id, template, tags, tocLevel, author, date, version, activityDate,
				previous, content);
	}

	/**
	 * Extract the content of a note file.
	 * 
	 * @param contentFile
	 * @return
	 */
	public static String extractContent(String contentFile) {
		// Get the content of the note
		String content = contentFile.replaceAll(defaultRegexMetadata, "").trim();
		return content;
	}

	/**
	 * Extract metadata from a note file.
	 * 
	 * @param contentFile
	 * @return
	 */
	public static String extractMetadata(String contentFile) {
		// Extract metadata
		Matcher matcher = pattern.matcher(contentFile);
		String metadata = matcher.find() ? matcher.group(0) : "";
		// remove the delimiters
		metadata = metadata.replaceAll("\\A`{3,}[\\s]*|[\\s]*`{3,}\\z", "");
		return metadata;
	}

	// Populate the nextElement list.
	public static void formatNotes(Note note) {

		if (note.previousElement != null && !note.previousElement.isEmpty())
			for (String previous : note.previousElement) {
				Note previousNote = Note.idMap.get(previous);

				if (previousNote != null) {
					previousNote.nextElement.add(note.id);
				}
			}

		for (Note subnote : note.subContent) {
			formatNotes(subnote);
		}
	}

}

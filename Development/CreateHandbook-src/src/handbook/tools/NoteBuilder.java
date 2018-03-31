package handbook.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ho.yaml.Yaml;

import handbook.note.Note;
import handbook.utils.Utils;
import handbook.utils.file.FileUtils;
import handbook.view.HandbookUI;

public class NoteBuilder {

	/**
	 * Create a note from a markdown file.
	 * 
	 * @param parent
	 * @param markdownFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Note createNote(Note parent, File markdownFile) throws Exception {
		String contentFile = FileUtils.getContentFile(markdownFile);

		// Extract metadata
		String metadata = extractMetadata(contentFile);

		// Extract content
		String content = contentFile.replaceAll(defaultRegexMetadata, "").trim();
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

		// Create the note
		Note note = new Note(markdownFile, parent, content, yamlElements);

		return note;
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

	/*
	 * \\A: must be at the begging [\\s]*: can be preceded by white-space characters
	 * but not by text character `{3,}: must be preceded by three ` characters [
	 * \\s: white-space \\S: any-character ]*: can contain any characters `{3,}:
	 * must be followed by three ` characters
	 */
	private final static String defaultRegexMetadata = "\\A[\\s]*`{3,}([\\s\\S]*?)`{3,}";
	private final static Pattern pattern = Pattern.compile(defaultRegexMetadata);

}

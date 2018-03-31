package handbook.tools;

import java.io.File;
import java.util.List;

import handbook.note.Note;
import handbook.utils.file.FolderUtils;

public class StructureBuilder {

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
		Note note = NoteBuilder.createNote(null, markdownFile);

		// Create the xml element
		// TemplateUtils.createXMLElementNote(note);

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
			Note note = NoteBuilder.createNote(parent, markdownFile);

			// And store it in the parent if it exists
			if (parent != null) {
				parent.subContent.add(note);
			}

			// Create the xml element
			// TemplateUtils.createXMLElementNote(note);

			// Determine the subcontent
			List<File> subFolders = FolderUtils.getFolders(folder);
			for (int j = 0; j < subFolders.size(); j++) {
				File subFolder = subFolders.get(j);
				setStructure(subFolder, note);
			}

		}

	}

}

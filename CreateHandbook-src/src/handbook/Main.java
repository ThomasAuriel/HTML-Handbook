package handbook;

import java.io.File;
import java.io.IOException;

import handbook.file.XMLUtils;
import handbook.structure.note.Note;
import handbook.structure.note.NoteFormater;

public class Main {

	/**
	 * Read the content of the local data folder (./data/) and write the
	 * composed element in ./formatedHandbook.md.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String dataDirectoryPath = "./data";
		File dataDirectory = new File(dataDirectoryPath);

		String outputPath = "./formatedHandbook.md";
		File output = new File(outputPath);

		Note handbook = new Note(dataDirectory);
		NoteFormater.formatNote(handbook);

		XMLUtils.writeFile(handbook, output);

	}
}

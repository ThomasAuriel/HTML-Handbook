package notes.contentfolder.structure;

import java.io.IOException;
import java.util.List;

import notes.contentfolder.parser.FileParser;

public class Section extends Element {

	public List<String> referencedSections;

	public Section(String path) throws IOException {
		super();
		this.path = path;
		this.classElement = "section";
		FileParser.parseSection(this);
	}

	public Section(Section section) {
		super(section);
	}

}

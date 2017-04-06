package notes.contentfolder.structure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import notes.contentfolder.parser.FileParser;

public class Chapter extends Element {

	public List<Section> sections = new ArrayList<Section>();

	public Chapter(String path) throws IOException {
		super();
		this.path = path;
		this.classElement = "chapter";
		FileParser.parseChapter(this);
	}

	public Chapter(Chapter chapter) {
		super(chapter);
	}

}

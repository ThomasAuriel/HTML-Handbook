package notes.contentfolder.structure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import notes.contentfolder.parser.FileParser;

public class Volume extends Element {
	
	public List<Chapter> chapters = new ArrayList<Chapter>();

	
	public Volume(String path) throws IOException {
		super();
		this.path = path;
		this.classElement = "volume";
		FileParser.parseVolume(this);
	}
	
	public Volume(Volume volume) {
		super(volume);
	}
	
	
}

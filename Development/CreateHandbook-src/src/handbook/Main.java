package handbook;

import java.io.File;

import handbook.note.Note;
import handbook.tools.StructureBuilder;
import handbook.tools.XMLStructureBuilder;
import handbook.utils.xml.XMLUtils;

public class Main {

	public static void main(String[] args) {

		try {
			Note note = StructureBuilder.getStructure(new File("./data"));
			XMLStructureBuilder.createXMLStructure(note);
			XMLUtils.writeFile(note, new File("./formatedHandbook.md"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package handbook;

import java.io.File;

import handbook.note.Note;
import handbook.tools.DashboardBuilder;
import handbook.tools.StructureBuilder;
import handbook.tools.XMLStructureBuilder;
import handbook.utils.xml.XMLUtils;
import handbook.view.HandbookUI;

public class Main {

	public static void main(String[] args) {

		try {
			HandbookUI ui = new HandbookUI();
			ui.setVisible(true);

			// Create the hierarchy tree
			Note note = StructureBuilder.getStructure(new File("./data"));

			// Finish to format notes
			DashboardBuilder.formatNextElements(note);
			DashboardBuilder.formatDashboard(note);

			// Create the xml structure used in the javascript
			XMLStructureBuilder.createXMLStructure(note);

			XMLUtils.writeFile(note, new File("./formatedHandbook.md"));

			HandbookUI.addMessage("Formating completed");
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			HandbookUI.addMessage(e.getMessage());
			e.printStackTrace();
		}

	}

}

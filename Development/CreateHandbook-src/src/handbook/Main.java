package handbook;

import java.io.File;

import handbook.note.Note;
import handbook.tools.DashboardBuilder;
import handbook.tools.ReferenceBuilder;
import handbook.tools.StructureBuilder;
import handbook.tools.XMLStructureBuilder;
import handbook.utils.xml.XMLUtils;
import handbook.view.HandbookUI;

public class Main {

	public static boolean autoClose = true;

	public static void main(String[] args) {

		try {
			HandbookUI ui = new HandbookUI();
			ui.setVisible(true);

			// Create the note tree
			Note note = StructureBuilder.getStructure(new File("./data"));

			// Finish to format notes
			// Format dashboard
			DashboardBuilder.formatNextElements();
			DashboardBuilder.formatDashboards();
			// Format reference
			ReferenceBuilder.formatReferences();

			// Create the xml structure used in the javascript
			XMLStructureBuilder.createXMLElements(note);
			XMLStructureBuilder.completeXMLElements();

			XMLUtils.writeFile(note, new File("formatedHandbook.md"));

			HandbookUI.addMessage("Formating completed", false);

			if (autoClose)
				System.exit(0);
		} catch (Exception e) {
			HandbookUI.addMessage(e.getMessage());
			e.printStackTrace();
		}

	}

}

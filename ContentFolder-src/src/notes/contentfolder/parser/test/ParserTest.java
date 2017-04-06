package notes.contentfolder.parser.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import notes.contentfolder.structure.Section;

public class ParserTest {

	@Test
	public void test() throws IOException {

		Section section = new Section("E:\\Notes\\Notes\\HTML-Handbook\\data\\aVolume\\note\\data\\aNote.html");
		Assert.assertTrue(section.id != null && section.title != null);
	}

}

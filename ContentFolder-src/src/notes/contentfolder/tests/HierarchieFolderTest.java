package notes.contentfolder.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import notes.contentfolder.HierarchieFolder;
import notes.contentfolder.structure.Volume;

public class HierarchieFolderTest {

	private final static String path = "E:\\Notes\\Notes\\HTML-Handbook\\data\\aVolume";

	@Test
	public void test() throws IOException {
		List<Volume> volumes = HierarchieFolder.createHandbook(path);
		Assert.assertTrue(volumes != null && !volumes.isEmpty());
	}

}

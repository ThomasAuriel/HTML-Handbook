package notes.contentfolder.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Test;

import notes.contentfolder.ContentFolder;

public class ContentFolderTest {
	
	private final static String path = "/home/auriel/Documents/Project/Notes/Notes/HTML-Handbook/data/aVolume";

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testContentFolder(){
		List<File> contentFolder = ContentFolder.getAllElements(path);
		System.out.println(contentFolder);
	}
	
	@Test
	public void testGetFiles(){
		List<File> contentFolder = ContentFolder.getFiles(path);
		System.out.println(contentFolder);
	}
	
	@Test
	public void testGetFolder(){
		List<File> contentFolder = ContentFolder.getFolders(path);
		System.out.println(contentFolder);
	}

}

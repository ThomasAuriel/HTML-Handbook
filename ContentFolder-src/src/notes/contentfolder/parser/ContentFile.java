package notes.contentfolder.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ContentFile {

	public static String getContentFile(File file) throws IOException{
		return new String(Files.readAllBytes(file.toPath()));
	}
}

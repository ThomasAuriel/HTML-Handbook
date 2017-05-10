package handbook.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

public class FileUtils {

	private final static Gson gson = new Gson();

	/**
	 * Get the content of a file
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getContentFile(File file) throws IOException {
		try {
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			System.err.println("Impossible to read : " + file.getName() + " [" + file.toPath() + "]");
			throw e;
		}
	}

	/**
	 * Save a string content in a file.
	 * 
	 * @param file
	 * @param content
	 */
	public static void writeFile(File file, String content) {
		try {
			Files.write(Paths.get(file.getAbsolutePath()), content.getBytes());
		} catch (IOException e) {
			System.out.println("Impossible to write the file at : " + file.getAbsolutePath());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Object> parseArrayJSON(String toParse) {
		return gson.fromJson(toParse, ArrayList.class);
	}

	public static void clearNode(Node item) {
		NodeList oldNodes = item.getChildNodes();
		for (int i = 0; i < oldNodes.getLength(); i++)
			item.removeChild(oldNodes.item(i));
	}

}

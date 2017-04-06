package notes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import notes.contentfolder.HierarchieFolder;
import notes.contentfolder.structure.Handbook;

public class Main {

	public static Handbook handbook = new Handbook();

	public static void main(String[] args) {
		createContentFile("data", "data/content.json");
	}

	public static void createContentFile(String dataFolderPath, String outputFolder) {

		File dataDirectory = new File(dataFolderPath);

		try {
			String absolutePath = dataDirectory.getAbsolutePath();

			handbook.volumes.addAll(HierarchieFolder.createHandbook(absolutePath));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(handbook);

			// Format paths
			File rootDirectory = new File("");
			absolutePath = rootDirectory.getAbsolutePath().replaceAll(Pattern.quote("\\"), "/");
			json = json.replaceAll(Pattern.quote("\\\\"), "/");
			json = json.replaceAll(Pattern.quote(absolutePath), ".");

			File file = new File(outputFolder);
			Files.write(Paths.get(file.getAbsolutePath()), json.getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

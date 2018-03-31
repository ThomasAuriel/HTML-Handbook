package handbook.utils.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderUtils {

	/**
	 * Find the first file with a specific extension.
	 * @param folder
	 * @param extension
	 * @return
	 */
	public static List<File> getFileWithExtension(File folder, String extension) {
		
		List<File> toReturn = new ArrayList<File>();

		List<File> files = FolderUtils.getFiles(folder);
		for (File file : files) {
			if (file.isFile() && file.getAbsolutePath().endsWith(extension)) {
				toReturn.add(file);
			}
		}
		return toReturn;
	}

	/**
	 * Get all the element from a specific folder
	 * 
	 * @param folder
	 * @return
	 */
	public static List<File> getAllElements(File folder) {

		File[] tabFile = folder.listFiles(); // Get all elements
		Arrays.sort(tabFile); // Alphabetic order
		List<File> listElement = new ArrayList<File>(Arrays.asList(tabFile));

		return listElement;
	}

	/**
	 * Get all the files from a specific folder.
	 * 
	 * @param folder
	 * @return
	 */
	public static List<File> getFiles(File folder) {

		List<File> listElements = getAllElements(folder);
		List<File> listFiles = new ArrayList<File>();
		for (File file : listElements) {
			if (file.isFile()) {
				listFiles.add(file); // Get only the files
			}
		}

		return listFiles;
	}

	/**
	 * Get all the subfolders from a specific folder.
	 * 
	 * @param folder
	 * @return
	 */
	public static List<File> getFolders(File folder) {
		List<File> listElements = getAllElements(folder);
		// List<File> listFolder = listElements.stream().filter(file ->
		// file.isDirectory()).collect(Collectors.toList());
		List<File> listFolder = new ArrayList<File>();
		for (File subFolder : listElements) {
			if (subFolder.isDirectory()) {
				listFolder.add(subFolder);
			}
		}

		return listFolder;
	}

}

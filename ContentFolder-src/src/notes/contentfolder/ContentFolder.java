package notes.contentfolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentFolder {

	public static List<File> getAllElements(String path){
		File f = new File(path);
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		return files;
	}
	
	public static List<File> getFiles(String path){
		List<File> listElements  = getAllElements(path);
		//List<File> listFiles = listElements.stream().filter(file -> file.isFile()).collect(Collectors.toList());
		List<File> listFiles = new ArrayList<>();
		for(File file : listElements){
			if(file.isFile()){
				listFiles.add(file);
			}
		}
		
		return listFiles;
	}
	
	public static List<File> getFolders(String path){
		List<File> listElements  = getAllElements(path);
		//List<File> listFolder = listElements.stream().filter(file -> file.isDirectory()).collect(Collectors.toList());
		List<File> listFolder = new ArrayList<>();
		for(File folder : listElements){
			if(folder.isDirectory()){
				listFolder.add(folder);
			}
		}
		
		return listFolder;
	}
}

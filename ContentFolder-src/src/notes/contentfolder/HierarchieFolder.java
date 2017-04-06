package notes.contentfolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import notes.contentfolder.structure.Chapter;
import notes.contentfolder.structure.Section;
import notes.contentfolder.structure.Volume;

public class HierarchieFolder {

	public static List<Volume> createHandbook(String path) throws IOException {

		// find the handbook
		String handbookHTML = findFirstHTMLFile(path);
		// if no html was find
		if (handbookHTML == null || handbookHTML.isEmpty())
			return null;
		// Otherwise
		List<Volume> volumes = new ArrayList<Volume>();

		// get all volume folder from a handbook
		List<File> volumeFolders = ContentFolder.getFolders(path);
		for (File volumeFolder : volumeFolders) {

			Volume volume = createVolume(volumeFolder.getAbsolutePath());
			if (volume != null)
				volumes.add(volume);
		}
		
		return volumes;
	}

	public static Volume createVolume(String path) throws IOException {

		// find the volume html file:
		String volumeHTML = findFirstHTMLFile(path);

		// if no html was find
		if (volumeHTML == null || volumeHTML.isEmpty())
			return null;
		// Otherwise
		Volume volume = new Volume(volumeHTML);

		// get all chapter folder from a volume
		List<File> chapterFolders = ContentFolder.getFolders(path);
		for (File chapterFolder : chapterFolders) {

			Chapter chapter = createChapter(chapterFolder.getAbsolutePath());
			if (chapter != null)
				volume.chapters.add(chapter);
		}

		return volume;

	}

	public static Chapter createChapter(String path) throws IOException {

		// find the chapter html file:
		String chapterHTML = findFirstHTMLFile(path);

		// if no html was find
		if (chapterHTML == null || chapterHTML.isEmpty())
			return null;
		// Otherwise
		Chapter chapter = new Chapter(chapterHTML);

		// Get all folders in the chapter
		List<File> sectionFolders = ContentFolder.getFolders(path);
		for (File sectionFolder : sectionFolders) {

			// List<Section> listSection =
			// ContentFolder.getFiles(sectionFolder.getAbsolutePath()).stream()
			// .map(file -> file.getAbsolutePath()).filter(sectionPath ->
			// sectionPath.endsWith("html"))
			// .map(sectionPath ->
			// createSection(sectionPath)).collect(Collectors.toList());
			// chapter.sections.addAll(listSection);

			List<File> files = ContentFolder.getFiles(sectionFolder.getAbsolutePath());
			for (File file : files) {
				String sectionPath = file.getAbsolutePath();
				if (sectionPath.endsWith("html")) {
					Section section = createSection(sectionPath);
					chapter.sections.add(section);
				}
			}

		}

		return chapter;
	}

	public static Section createSection(String path) throws IOException {
		Section section = new Section(path);
		return section;
	}

	private static String findFirstHTMLFile(String path) {
		// find the chapter html file:
		// String htmlFile = ContentFolder.getFiles(path).stream().map(file ->
		// file.getAbsolutePath()).filter(sectionPath ->
		// sectionPath.endsWith("html")).findFirst().orElse(null);
		// return htmlFile;

		List<File> files = ContentFolder.getFiles(path);
		for (File file : files) {
			if (file.isFile() && file.getAbsolutePath().endsWith("html")) {
				return file.getAbsolutePath();
			}
		}
		return null;
	}

}

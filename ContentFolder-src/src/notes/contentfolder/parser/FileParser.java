package notes.contentfolder.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import notes.Main;
import notes.contentfolder.structure.Element;
import notes.contentfolder.structure.Section;
import notes.contentfolder.structure.Volume;

public class FileParser {

	private final static Gson gson = new Gson();

	public static void parseSection(Section section) throws IOException {
		Document doc = getHTMLDocument(section.path);

		Elements sectionElement = doc.getElementsByClass("section");
		section.id = sectionElement.attr("id");
		section.title = sectionElement.attr("title");
		section.shortTitle = sectionElement.attr("shortTitle");
		section.classElement += (sectionElement.hasAttr("activity") ? (" activity") : (""));

		String jsonToConvert = sectionElement.attr("referencedSection");
		section.referencedSections = gson.fromJson(jsonToConvert, ArrayList.class);

		Main.handbook.addIdElement(section.id, section);
		for (String referedSection : section.referencedSections) {
			Main.handbook.addReferingElement(referedSection, section);
		}
	}

	public static void parseChapter(Element chapter) throws IOException {
		Document doc = getHTMLDocument(chapter.path);

		Elements sectionElement = doc.getElementsByClass("chapter");
		chapter.id = sectionElement.attr("id");
		chapter.title = sectionElement.attr("title");
		chapter.shortTitle = sectionElement.attr("shortTitle");

		Main.handbook.addIdElement(chapter.id, chapter);
	}

	public static void parseVolume(Volume volume) throws IOException {
		Document doc = getHTMLDocument(volume.path);

		Elements sectionElement = doc.getElementsByClass("volume");
		volume.id = sectionElement.attr("id");
		volume.title = sectionElement.attr("title");
		volume.shortTitle = sectionElement.attr("shortTitle");

		Main.handbook.addIdElement(volume.id, volume);
	}

	private static Document getHTMLDocument(String path) throws IOException {
		File chapterFile = new File(path);
		String chapterFileContent = ContentFile.getContentFile(chapterFile);
		Document doc = Jsoup.parse(chapterFileContent);
		return doc;
	}

}

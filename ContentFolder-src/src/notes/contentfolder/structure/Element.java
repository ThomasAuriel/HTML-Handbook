package notes.contentfolder.structure;

import java.io.IOException;

public class Element {

	public String path;
	public String title;
	public String shortTitle;
	public String id;
	public String classElement;

	public Element() {
		super();
	}
	
	/**
	 * Create a flat copy of the element
	 * @param chapter
	 * @throws IOException
	 */
	public Element(Element chapter) {
		super();
		
		this.path = chapter.path;
		this.title = chapter.title;
		this.shortTitle = chapter.shortTitle;
		this.id = chapter.id;
		this.classElement = chapter.classElement;
	}

}
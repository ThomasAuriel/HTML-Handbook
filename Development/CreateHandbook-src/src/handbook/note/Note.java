package handbook.note;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Element;

/**
 * Note object used to represent a note file.
 * 
 * @author auriel
 *
 */
public class Note {

	public static Map<String, Note> idMap = new HashMap<String, Note>();
	public static final Map<String, List<Note>> referingElementMap = new HashMap<String, List<Note>>();

	public File filePath;
	public Element xmlElement;

	public Note parent;
	public List<Note> subContent;

	public String id;
	public String template;
	public List<String> tags;
	public int tocLevel;

	// Task
	public List<String> previousElements;
	public List<String> nextElements;
	public String taskStatus;

	// Dashboard
	public int dashboardMaxLevel = 0;
	public int dashboardCurrentLevel = 0;
	public Set<String> activeTasks;
	public Set<String> waitingTasks;
	public Set<String> doneTasks;

	public String title;
	public boolean activity;
	public String activityDate;
	public String author;
	public String date;
	public String version;
	public String content;

	/**
	 * Create a note with all the required elements.
	 * 
	 * @param filePath
	 * @param parent
	 * @param title
	 * @param id
	 * @param tags
	 * @param tocLevel
	 * @param author
	 * @param date
	 * @param version
	 * @param activityDate
	 * @param content
	 */
	public Note(File filePath, Note parent, String title, String id, String template, List<String> tags, int tocLevel,
			int dashboardMaxLevel, List<String> previousElements, String taskStatus, String author, String date,
			String version, String activityDate, String content) {

		this.filePath = filePath;

		this.parent = parent;
		this.subContent = new ArrayList<Note>();

		this.title = title;
		this.id = id;
		this.template = template;
		this.tags = tags;
		this.tocLevel = tocLevel;

		this.previousElements = previousElements != null ? previousElements : new ArrayList<String>();
		this.nextElements = new ArrayList<String>();
		this.taskStatus = taskStatus;

		this.dashboardMaxLevel = dashboardMaxLevel;
		this.dashboardCurrentLevel = 0;
		this.activeTasks = new TreeSet<String>();
		this.waitingTasks = new TreeSet<String>();
		this.doneTasks = new TreeSet<String>();

		this.author = author;
		this.activity = activityDate != null && !activityDate.isEmpty();
		this.activityDate = activityDate;
		this.date = date;
		this.version = version;
		this.content = content;

		populateMaps();
	}

	/**
	 * Populate maps to find faster the correct element according to its id.
	 */
	private void populateMaps() {

		// Populate the id map
		idMap.put(id, this);

		// Populate the refering element map
		if (tags != null)
			for (String tag : tags) {
				List<Note> list = referingElementMap.get(tag);
				if (list == null) {
					list = new ArrayList<Note>();
					referingElementMap.put(tag, list);
				}
				list.add(this);
			}
	}

}

package handbook.note;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import handbook.utils.template.TemplateUtils;
import handbook.view.HandbookUI;

/**
 * Note object used to represent a note file.
 * 
 * @author auriel
 *
 */
public class Note {

	private static Map<String, Note> idMap = new HashMap<String, Note>();
	public static final Map<String, List<Note>> referingElementMap = new HashMap<String, List<Note>>();

	/**
	 * Get a {@link Note} based on its {@param id}. Throw a
	 * {@link NullPointerException} if the note is not defined.
	 * 
	 * @param id
	 * @throws NullPointerException
	 * @return
	 */
	public static Note getNote(String id) throws NullPointerException {
		Note note = idMap.get(formatID(id));
		if (note == null)
			throw new NullPointerException("The note [" + id + "] is not defined.");
		return note;
	}
	
	public static String formatID(String id) {
		 return id.replaceAll("\\s", "");
	}

	/**
	 * Get all the defined {@link Note}
	 * 
	 * @return
	 */
	public static Collection<Note> getAllNotes() {
		return idMap.values();
	}

	public File markdownFile;
	public Element xmlElement;
	public String template;

	public Note parent;
	public List<Note> subContent;

	// Information
	public String id;
	public String title;

	public String author;
	public String date;
	public String version;

	public List<String> references;
	public List<String> activity;
	public List<String> tasks;

	// Tags
	public List<String> tags;

	// Toc
	public Integer tocLevel;

	// Task
	public String taskStatus;
	public List<String> previousElements;
	public List<String> nextElements;

	// Dashboard
	public Integer dashboardLevel;
	public List<String> dashboardActiveTasks;
	public List<String> dashboardWaitingTasks;
	public List<String> dashboardDoneTasks;
	public String activityDate;

	// Content
	public String content;

	/**
	 * Create a note with all the required elements.
	 * 
	 * @param markdownFile
	 * @param parent
	 * @param content
	 * @param yamlElements
	 * @throws Exception
	 */
	public Note(File markdownFile, Note parent, String content, Map<String, Object> yamlElements) throws Exception {

		this.markdownFile = markdownFile;
		this.content = content;

		this.parent = parent;
		this.subContent = new ArrayList<Note>();

		// Template to use
		extractTemplate(markdownFile, yamlElements);

		// Title, ID
		extratTitle(markdownFile, yamlElements);
		extractID(markdownFile, yamlElements);

		// Author, Date, Version
		extractAuthor(markdownFile, yamlElements);
		extractDate(markdownFile, yamlElements);
		extractVersion(markdownFile, yamlElements);

		// Tags, Toc
		extractTags(markdownFile, yamlElements);
		extractTocLevel(markdownFile, yamlElements);

		references = new ArrayList<String>();
		activity = new ArrayList<String>();
		tasks = new ArrayList<String>();

		// Dashboard, Task, Activity date
		extractActivityDate(markdownFile, yamlElements);
		extractDashboard(markdownFile, yamlElements);

		populateMaps();
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractTemplate(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.template = (String) yamlElements.get(NoteElements.balise_attribute_template);

			if (template == null || template.isEmpty())
				this.template = TemplateUtils.templatePath;
		} catch (NullPointerException e) {
			this.template = TemplateUtils.templatePath;
		} catch (Exception e) {
			HandbookUI.addMessage("An error occured will trying to read the template name of the markdown file: "
					+ markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extratTitle(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		// Title
		try {
			this.title = (String) yamlElements.get(NoteElements.balise_attribute_title);
			if (title == null) {
				title = new String();
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			HandbookUI.addMessage("The title of the note : " + markdownFile.getPath() + " is not defined.");
			throw e;
		} catch (Exception e) {
			HandbookUI.addMessage(
					"An error occured will trying to read the title of the markdown file: " + markdownFile.getPath());
			throw e;
		}

	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractID(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		// ID
		try {
			this.id = (String) yamlElements.get(NoteElements.balise_attribute_id);

			if (this.id == null || this.id.isEmpty()) {
				// Be sure that the title is not empty
				extratTitle(markdownFile, yamlElements);
				this.id = this.title;
			}

			// Format id : remove spaces
			id = formatID(id);
		} catch (Exception e) {
			HandbookUI.addMessage(
					"An error occured will trying to read the id of the markdown file: " + markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractAuthor(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.author = (String) yamlElements.get(NoteElements.balise_attribute_author);

			if (this.author == null)
				this.author = new String();
		} catch (Exception e) {
			HandbookUI.addMessage(
					"An error occured will trying to read the author of the markdown file: " + markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractDate(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.date = (String) yamlElements.get(NoteElements.balise_attribute_date);

			if (this.date == null)
				this.date = new String();
		} catch (Exception e) {
			HandbookUI.addMessage(
					"An error occured will trying to read the date of the markdown file: " + markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractVersion(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.version = (String) yamlElements.get(NoteElements.balise_attribute_version);
			if (this.version == null)
				this.version = new String();
		} catch (Exception e) {
			HandbookUI.addMessage(
					"An error occured will trying to read the title of the markdown file: " + markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void extractTags(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.tags = (List<String>) yamlElements.get(NoteElements.balise_attribute_tags);

			if (this.tags == null)
				this.tags = new ArrayList<String>();
		} catch (Exception e) {
			HandbookUI.addMessage("An error occured will trying to read the list of tags of the markdown file: "
					+ markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractTocLevel(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.tocLevel = (Integer) yamlElements.get(NoteElements.balise_attribute_toc);
			if (this.tocLevel == null)
				this.tocLevel = 0;
		} catch (Exception e) {
			HandbookUI.addMessage("An error occured will trying to read the ToC level of the markdown file: "
					+ markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractActivityDate(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.activityDate = (String) yamlElements.get(NoteElements.balise_attribute_activities);

			if (this.activityDate == null)
				this.activityDate = new String();
		} catch (Exception e) {
			HandbookUI.addMessage("An error occured will trying to read the activity date of the markdown file: "
					+ markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractDashboard(File markdownFile, Map<String, Object> yamlElements) throws Exception {

		// Task
		extractTaskStatus(markdownFile, yamlElements);
		extractPreviousElements(markdownFile, yamlElements);

		// Dashboard
		extractDashboardLevel(markdownFile, yamlElements);
		// If a dashboard level is defined
		if (dashboardLevel > 0) {
			dashboardActiveTasks = new ArrayList<String>();
			dashboardWaitingTasks = new ArrayList<String>();
			dashboardDoneTasks = new ArrayList<String>();
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractTaskStatus(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.taskStatus = (String) yamlElements.get(NoteElements.balise_attribute_taskstatus);

			if (this.taskStatus == null)
				this.taskStatus = new String();
		} catch (Exception e) {
			HandbookUI.addMessage("An error occured will trying to read the task status of the markdown file: "
					+ markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void extractPreviousElements(File markdownFile, Map<String, Object> yamlElements) throws Exception {

		this.nextElements = new ArrayList<String>();

		try {
			this.previousElements = (List<String>) yamlElements.get(NoteElements.balise_attribute_previous);

			if (this.previousElements == null)
				this.previousElements = new ArrayList<String>();
		} catch (Exception e) {
			HandbookUI.addMessage("An error occured will trying to read the previous tasks of the markdown file: "
					+ markdownFile.getPath());
			throw e;
		}
	}

	/**
	 * @param markdownFile
	 * @param yamlElements
	 * @throws Exception
	 */
	private void extractDashboardLevel(File markdownFile, Map<String, Object> yamlElements) throws Exception {
		try {
			this.dashboardLevel = (Integer) yamlElements.get(NoteElements.balise_attribute_dashboard);

			if (this.dashboardLevel == null)
				this.dashboardLevel = 0;
		} catch (Exception e) {
			HandbookUI.addMessage("An error occured will trying to read the dashboard size of the markdown file: "
					+ markdownFile.getPath());
			throw e;
		}
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

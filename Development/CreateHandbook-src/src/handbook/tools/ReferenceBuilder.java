package handbook.tools;

import handbook.note.Note;
import handbook.utils.template.TaskUtil;
import handbook.view.HandbookUI;

public class ReferenceBuilder {

	/**
	 * Format references
	 */
	public static void formatReferences() {

		for (Note note : Note.getAllNotes()) {
			formatReference(note);
		}

	}

	public static void formatReference(Note note) {

		boolean isActivity = !note.activityDate.isEmpty();
		boolean isTask = TaskUtil.isTask(note);

		// Add the reference of the current note to the targeted notes.
		for (String tag : note.tags) {

			try {
				Note taggedNote = Note.getNote(tag);

				if (isActivity)
					taggedNote.activity.add(note.id);
				if (isTask)
					taggedNote.tasks.add(note.id);

				// If the current note is not an activity or a task.
				if (!isActivity && !isTask)
					taggedNote.references.add(note.id);
			} catch (Exception ex) {
				HandbookUI.addMessage(
						"The note [" + tag + "] is not defined. This note is refered as Tag by [" + note.title + "].");
			}
		}

	}

}

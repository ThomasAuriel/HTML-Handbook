package handbook.tools;

import java.util.ArrayList;
import java.util.List;

import handbook.note.Note;
import handbook.utils.template.TaskUtil;

public class DashboardBuilder {

	/**
	 * Format required note dashboard
	 */
	public static void formatDashboards() {

		for (Note note : Note.getAllNotes()) {
			formatDashboard(note);
		}

	}

	public static void formatDashboard(Note dashboard) {

		if (dashboard.dashboardLevel > 0) {

			// Find all the subtasks
			List<Note> tasks = new ArrayList<Note>();
			for (Note subNote : dashboard.subContent) {
				getTask(subNote, tasks, dashboard, dashboard.dashboardLevel);
			}

			// Populate the activate, done and waiting list in the dashboard
			for (Note task : tasks) {
				if (TaskUtil.isDone(task)) {
					dashboard.dashboardDoneTasks.add(task.id);
				} else if (TaskUtil.isActive(task)) {
					dashboard.dashboardActiveTasks.add(task.id);
				} else {
					dashboard.dashboardWaitingTasks.add(task.id);
				}
			}
		}

	}

	private static void getTask(Note note, List<Note> tasks, Note dashboard, int currentLevel) {

		// if the current level is low than 0, then stop
		if (currentLevel <= 0)
			return;
		else
			currentLevel = currentLevel - 1; // Create a new integer to store the new level and not change the
												// dashboard.dashboardMaxLevel

		if (!note.taskStatus.isEmpty() || !note.previousElements.isEmpty()) {
			tasks.add(note);
		}

		// Get subtasks
		for (Note subnote : note.subContent) {
			getTask(subnote, tasks, dashboard, currentLevel);
		}

	}

	/**
	 * Populate the nextElement list of all loaded notes.
	 */
	public static void formatNextElements() {

		for (Note note : Note.getAllNotes()) {
			formatNextElement(note);
		}

	}

	/**
	 * Populate the nextElement list element of the notes targeted by the
	 * previousElements of the current note.
	 * 
	 * @param note
	 */
	private static void formatNextElement(Note note) {

		if (note.previousElements != null && !note.previousElements.isEmpty())
			for (String previous : note.previousElements) {
				Note previousNote = Note.getNote(previous);
				previousNote.nextElements.add(note.id);
			}
	}

}

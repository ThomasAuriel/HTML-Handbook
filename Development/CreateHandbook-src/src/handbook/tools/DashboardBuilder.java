package handbook.tools;

import java.util.ArrayList;
import java.util.List;

import handbook.note.Note;
import handbook.note.TaskStatus;

public class DashboardBuilder {

	public static void formatDashboard(Note dashboard) {

		if (dashboard.dashboardMaxLevel > 0) {

			// Find all the subtasks
			List<Note> tasks = new ArrayList<Note>();
			for (Note subNote : dashboard.subContent) {
				getTask(subNote, tasks, dashboard, dashboard.dashboardMaxLevel);
			}
		}

		// Get subdashboard
		for (Note subnote : dashboard.subContent) {
			formatDashboard(subnote);
		}

	}

	private static void getTask(Note note, List<Note> tasks, Note dashboard, int currentLevel) {

		// Init the note.
		note.dashboardCurrentLevel = 0;

		// if the current level is low than 0, then stop to scan notes to look for
		// subtasks.
		if (currentLevel <= 0)
			return;
		else
			currentLevel = currentLevel - 1; // Create a new integer to store the new level and not change the
												// dashboard.dashboardMaxLevel

		if (!note.nextElements.isEmpty() || !note.previousElements.isEmpty()) {
			tasks.add(note);
			note.dashboardCurrentLevel = currentLevel;
		}

		// Get subtasks
		for (Note subnote : note.subContent) {
			getTask(subnote, tasks, dashboard, currentLevel);
		}

		// Populate the activate, done and waiting list in the dashboard
		for (Note task : tasks) {
			if (task.taskStatus != null && task.taskStatus.equals(TaskStatus.done)) {
				dashboard.doneTasks.add(task.id);
			} else if (isActive(task)) {
				dashboard.activeTasks.add(task.id);
				task.taskStatus = TaskStatus.active;
			} else {
				dashboard.waitingTasks.add(task.id);
				task.taskStatus = TaskStatus.waiting;
			}
		}
	}

	/**
	 * Indicate if a task is active. All previous tasks shall be done.
	 * 
	 * @param note
	 * @return
	 */
	public static boolean isActive(Note note) {

		for (String previous : note.previousElements)
			// if once previous tasks is not done (active or waiting) then the current task
			// is not active.
			if (Note.idMap.get(previous).taskStatus == null
					|| !Note.idMap.get(previous).taskStatus.equals(TaskStatus.done))
				return false;

		return true;
	}

	// Populate the nextElement list.
	public static void formatNextElements(Note note) {

		if (note.previousElements != null && !note.previousElements.isEmpty())
			for (String previous : note.previousElements) {
				Note previousNote = Note.idMap.get(previous);

				if (previousNote != null) {
					previousNote.nextElements.add(note.id);
				}
			}

		for (Note subnote : note.subContent) {
			formatNextElements(subnote);
		}
	}

}

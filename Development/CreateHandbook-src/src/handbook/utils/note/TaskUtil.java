package handbook.utils.note;

import handbook.note.Note;
import handbook.note.NoteElements;

public class TaskUtil {

	public static String getTaskStatus(Note note) {

		String taskStatus = note.taskStatus;

		if (!taskStatus.isEmpty()) {
			return taskStatus;
		}

		if (isActive(note)) {
			return NoteElements.task_active;
		} else if (isWaiting(note)) {
			return NoteElements.task_waiting;
		} else if (isDone(note)) {
			return NoteElements.task_done;
		}

		return "Impossible to determine the status of the task";
	}

	/**
	 * Indicate if a {@link Note} is a task or not.
	 * 
	 * @param note
	 * @return
	 */
	public static boolean isTask(Note note) {
		return !note.taskStatus.isEmpty() || !note.previousElements.isEmpty();
	}

	/**
	 * Indicate if a {@link Note} is active. All previous {@link Note} shall be done
	 * tasks or the task is defined as activate.
	 * 
	 * @param note
	 * @return
	 */
	public static boolean isActive(Note note) {

		if (!note.taskStatus.isEmpty())
			return note.taskStatus.equals(NoteElements.task_active);
		else {
			boolean isActive = true; // by default a task is considered as active
			for (String previous : note.previousElements) {
				Note previousNote = Note.getNote(previous);
				if (!isDone(previousNote))
					// If one previous note is not done (active or waiting)
					isActive = false;
			}
			return isActive;
		}
	}

	/**
	 * Indicate if a task is waiting
	 * 
	 * @param note
	 * @return
	 */
	public static boolean isWaiting(Note note) {

		if (!note.taskStatus.isEmpty())
			return note.taskStatus.equals(NoteElements.task_waiting);
		else {
			boolean isWaiting = false;
			for (String previous : note.previousElements) {
				Note previousNote = Note.getNote(previous);
				if (isActive(previousNote) || isWaiting(previousNote))
					// If one previous note is active or waiting
					isWaiting = true;
			}
			return isWaiting;
		}

	}

	/**
	 * Indicate if a task is done
	 * 
	 * @param note
	 * @return
	 */
	public static boolean isDone(Note note) {
		return note.taskStatus.equals(NoteElements.task_done);
	}

}

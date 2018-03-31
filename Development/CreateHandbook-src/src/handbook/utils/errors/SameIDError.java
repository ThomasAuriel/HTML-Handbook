package handbook.utils.errors;

import handbook.note.Note;

public class SameIDError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4741332569404515785L;

	public SameIDError(String message) {
		super(message);
	}

	public SameIDError(Note note1, Note note2) {
		super("At least two notes share the same id :\n" + note1.markdownFile.getPath() + "\n" + note2.markdownFile.getPath());
	}

}

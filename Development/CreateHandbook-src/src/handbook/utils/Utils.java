package handbook.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	/**
	 * Create an id for a
	 * 
	 * @param input
	 * @return
	 */
	public static String createId(String title) {
		return title.replaceAll("[^\\p{ASCII}]|[\\s]+", "").toLowerCase();
	}

	/**
	 * Format the current date following the dateFormat provided.
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String formatDate(String dateFormat) {
		String toReturn = new String();
		try {
			// Try to use the date format to format the current date.
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date date = new Date();
			toReturn = sdf.format(date);
		} catch (IllegalArgumentException ex) {
			toReturn = dateFormat;
		}
		return toReturn;
	}

	public static String formatPath(String content, File file) {
		return content.replaceAll("\\$NOTEPATH", file.getParent());
	}
}

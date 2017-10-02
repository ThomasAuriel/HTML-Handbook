package handbook.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

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

		return content.replaceAll("(\\!\\[.*\\]\\()\\.(/.*\\))", "$1"+file.getParent()+"$2");
	}
}

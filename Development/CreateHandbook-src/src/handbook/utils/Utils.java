package handbook.utils;

import java.io.File;
import java.nio.file.FileSystems;
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

	private static Pattern pURL = Pattern.compile("\\[.*\\]\\((..*?)\\)");

	/**
	 * Format path contained in the structure [.*](./pathToFormat).
	 * 
	 * @param content
	 * @param file
	 * @return
	 */
	public static String formatPath(String content, File file) {

		// Extract links and format them
		Matcher mURL = pURL.matcher(content);
		while (mURL.find()) {
			String originalURL = mURL.group(1);

			String parent = file.getParent();
			String separator = FileSystems.getDefault().getSeparator();
			String formatedURL = originalURL.replaceFirst("^\\.([/\\\\])",
					Matcher.quoteReplacement(parent + separator));

			content = content.replaceFirst(originalURL, Matcher.quoteReplacement(formatedURL));

			// Match.quoteReplacement prevents java.lang.IllegalArgumentException: character
			// to be escaped is missing and prevent also to interprent charater as \[a-zA-Z]
			// as special characters
		}

		return content;
	}
}

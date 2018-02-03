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

	private static Pattern pDescription = Pattern.compile("\\[.*?\\]");
	private static Pattern pURL = Pattern.compile("\\(.*?\\)");

	public static String formatPath(String content, File file) {

		// Extract description
		Matcher mDescrition = pDescription.matcher(content);
		String description;
		if (mDescrition.find())
			description = mDescrition.group(0);
		else
			return content;

		// Extract link
		Matcher mURL = pURL.matcher(content);
		String url;
		if (mURL.find())
			url = mURL.group(0);
		else
			return content;

		// Format the link
		url = url.replace("^(.", file.getParent());
		url = url.replaceAll("/", Matcher.quoteReplacement(File.separator));

		return description + url;
	}
}

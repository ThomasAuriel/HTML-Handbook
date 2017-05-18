package handbook.structure.note.customstructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import handbook.structure.note.Note;

public class CustomCalendar {

	private static final transient SimpleDateFormat defaultSDF = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Define a custom calendar using sub activity element.
	 * 
	 * @param note
	 * @param calendar
	 */
	public static void defineCustomCalendar(Note note, Element calendar) {

		List<Note> activities = new ArrayList<Note>();
		collectActivities(note, activities);

		// Create the json structure which represent the calendar.
		JsonArray jsonActivityStructure = new JsonArray();
		int maxYear = Integer.MIN_VALUE;
		int minYear = Integer.MAX_VALUE;
		for (Note activity : activities) {
			try {

				JsonObject jsonObject = new JsonObject();

				// Define the date
				String stringDate = activity.getDate();
				String dateFormat = activity.getDateFormat();

				Calendar cal = Calendar.getInstance();
				Date date = null;

				if (dateFormat != null && !dateFormat.isEmpty()) {
					SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
					date = sdf.parse(stringDate);
				} else {
					date = defaultSDF.parse(stringDate);
				}
				stringDate = defaultSDF.format(date);

				// Find the max and min year
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);
				if (year > maxYear)
					maxYear = year;
				if (year < minYear)
					minYear = year;

				// Store the jsonObject
				jsonObject.addProperty("date", stringDate);
				jsonObject.addProperty("id", activity.getId());
				jsonActivityStructure.add(jsonObject);

			} catch (ParseException e) {
				// Impossible to use the dateFormat, so continue;
				continue;
			}
		}

		// Store the different element in the HTML calendar
		calendar.setAttribute("starting", Integer.toString(minYear));
		calendar.setAttribute("ending", Integer.toString(minYear));
		calendar.setAttribute("data", jsonActivityStructure.toString());

	}

	/**
	 * Recursive function which collect all sub-activity-note with a provided
	 * date from a parent note. Store activities in the list listActivities.
	 * 
	 * @param note
	 * @param listActivities
	 */
	private static void collectActivities(Note note, List<Note> listActivities) {

		// Collect a note if it is an activity and a date is provided in the
		// activity element.
		if (note.isActivity() && !note.getActivity().isEmpty()) {
			listActivities.add(note);
		}

		for (Note subnote : note.getSubNotes()) {
			collectActivities(subnote, listActivities);
		}

	}
}

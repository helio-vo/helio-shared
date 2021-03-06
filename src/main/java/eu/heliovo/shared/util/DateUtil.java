package eu.heliovo.shared.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Utilities to handle Java Dates. 
 * @author marco soldati at fhnw ch
 *
 */
public class DateUtil {

	/**
	 * Formatter for the ISO format.
	 */
    private static DateFormat ISO8601_FORMAT = new SimpleDateFormat(
    "yyyy-MM-dd'T'HH:mm:ss");
    
    /**
     * Formatter for the ISO format with Milliseconds
     */
    private static DateFormat ISO8601_MS_FORMAT = new SimpleDateFormat(
    "yyyy-MM-dd'T'HH:mm:ss.SSS");
	static {
		ISO8601_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
		ISO8601_MS_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	/**
	 * Convert a date object to an ISO date string such as used by XML docs.
	 * 
	 * @param date
	 *            the date to convert.
	 * @return the date as string.
	 */
	public static synchronized String toIsoDateString(Date date) {
		if (date == null) {
			return null;
		}

		// format in (almost) ISO8601 format
		String dateStr = ISO8601_FORMAT.format(date);

		// remap the timezone from 0000 to 00:00 (starts at char 22)
		// return dateStr.substring (0, 22) + ":" + dateStr.substring (22);
		return dateStr;
	};
	
	/**
	 * Parse the ISO date string into a date object in time zone UTC.
	 * @param isoDate the date as ISO String
	 * @return the ISO date.
	 * @throws ParseException if anything goes wrong
	 */
	public static synchronized Date fromIsoDate(String isoDate) throws ParseException {
	    if (isoDate == null) {
	        return null;
	    }
	    Date date;
	    if (isoDate.contains(".")) {
	        date = ISO8601_MS_FORMAT.parse(isoDate);
	    } else {
	        date = ISO8601_FORMAT.parse(isoDate);
	    }
	    return date;
	}
	
   /**
     * Build all permutations of two lists. If list1 contain {A,B,C} and list2 contains {1,2}, the resulting lists will contain
     * [{A,A,B,B,C,C}, {1,2,1,2,1,2}].
     * @param <T> type of the list to permute.
     * @param list1 the first list to permute.
     * @param list2 the second list to permute.
     * @return array of two permuted lists.
     */
    public static <T> List<T>[] permuteLists(List<T> list1, List<T> list2) {
        @SuppressWarnings("unchecked")
        List<T>[] ret = new List[2];
        int maxSize = list1.size() * list2.size();
        
        ret[0] = new ArrayList<T>(maxSize);
        ret[1] = new ArrayList<T>(maxSize);
        for (int i = 0; i < list2.size(); i++) {
            for (int j = 0; j < list1.size(); j++) {
                ret[1].add(list2.get(i));
                ret[0].add(list1.get(j));
            }
        }
        return ret;
    }
}

package nl.smartworkx.admin.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public final class DateUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public final static LocalDate toDate(String dateAsString){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(dateAsString, formatter);
	}

	public static final LocalDateTime dateToDateTime(String dateAsString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		return LocalDateTime.parse(dateAsString + " 00:00:00", formatter);

	}
}

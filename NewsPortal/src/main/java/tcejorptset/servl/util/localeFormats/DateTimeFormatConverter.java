package tcejorptset.servl.util.localeFormats;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatConverter {
	
	private static final DateTimeFormatConverter instance = new DateTimeFormatConverter ();
	
	private DateTimeFormatConverter () {}

	public static DateTimeFormatConverter getInstance () {
		return instance;
	}
	
	public String getLocalDateShort (String locale, Instant instant) {
		if (locale == null) {
			locale = System.getProperty("user.language");
		}
		Locale currentLocale = new Locale (locale.toLowerCase(), locale.toUpperCase());
		DateFormat dateFormatShort = DateFormat.getDateInstance(DateFormat.SHORT, currentLocale);
		return dateFormatShort.format(Date.from(instant)); 
	}
	
	public String getLocalDateTime (String locale, Instant instant) {
		if (locale == null) {
			locale = System.getProperty("user.language");
		}
		Locale currentLocale = new Locale (locale.toLowerCase(), locale.toUpperCase());
		DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, currentLocale);
		return dateTimeFormat.format(Date.from(instant));
	}
}

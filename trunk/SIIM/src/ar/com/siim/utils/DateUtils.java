package ar.com.siim.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility methods for dates.
 *
 * @author pruggia
 */
public abstract class DateUtils {

	/** Highest considered year. */
	private static final int MAX_YEAR = 3000;

	/** Default hour, minute, second and milisecond value. */
	private static final int HOUR_MIN_SEC_MIL_DEFAULT = 0;

	/**
	 * Returns a new Date object with the same time that the one passed as
	 * parameter.
	 *
	 * @param date A date, can be null.
	 * @return A Date object or null.
	 */
	public static Date cloneDate(final Date date) {
		if (date == null) {
			return null;
		} else {
			return new Date(date.getTime());
		}
	}

	/**
	 * Creates a new date from a string in the format 'yyyy-MM-dd HH:mm'.
	 *
	 * @param strDate the date as a string
	 * @return a {@link Date}
	 */
	public static Date dateFromString(final String strDate) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date format error: " + strDate, e);
		}
	}

	/**
	 * Creates a new date from a string in the particular format.
	 *
	 * @return date
	 */
	public static Date dateFromString(final String strDate, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Formats a date to a 'yyyy-MM-dd HH:mm' string, if null returns an empty
	 * String.
	 *
	 * @param date the date to format
	 * @return String formatted date
	 */
	public static String stringFromDate(final Date date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.format(date);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * Formats a date to a format given string, if null returns an empty
	 * String.
	 * Examples for format are "yyyy-MM-dd HH:mm", "MM/dd/yyyy"
	 *
	 * @param date the date
	 * @param format the format
	 * @return a string with the formatted date
	 */
	public static String stringFromDate(final Date date, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.format(date);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * Add days to the original date.
	 *
	 * @param originalDate the original date
	 * @param days the amount of days
	 * @return originalDate with days added
	 */
	public static Date addDaysToDate(final Date originalDate, final int days) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(originalDate);
		c1.add(Calendar.DATE, days);
		return c1.getTime();
	}

	public static Date addMinutesToDate(final Date originalDate, final int minutes) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(originalDate);
		c1.add(Calendar.MINUTE, minutes);
		return c1.getTime();
	}

	public static Date previousDate(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static Date nextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * Returns a Date, set to dayCount days from now. i.e. "yesterday" ==
	 * daysFromToday(-1) and "next week" == daysFromToday(7)
	 *
	 * @param dayCount days from now
	 * @return a Date
	 */
	public static Date daysFromToday(final int dayCount) {
		Calendar c = Calendar.getInstance();
		calendarTimeReset(c);
		c.add(Calendar.DATE, dayCount);
		return c.getTime();
	}

	/**
	 * Compare two {@link Date} ignoring hour, minutes, seconds and milliseconds.
	 * If date1 is after date2 returns <code>1</code>; if date1 equals date 2
	 * returns <code>0</code>; if date1 is before date 2 returns
	 * <code>-1</code>.
	 *
	 * @param date1 the first date
	 * @param date2 the second date
	 * @return int number with the result of the comparison
	 */
	public static int compareDates(final Date date1, final Date date2) {
		Calendar time1 = calendarFromDate(date1);
		calendarTimeReset(time1);
		Calendar time2 = calendarFromDate(date2);
		calendarTimeReset(time2);
		return time1.compareTo(time2);
	}

	/**
	 * Returns <code>true</code> if the first date is before the second date,
	 * <code>false</code> in other case.
	 *
	 * @param firstDate the first date to compare
	 * @param secondDate the second date to compare
	 * @return if the firstDate is before the secondDate
	 */
	public static boolean isBefore(final Date firstDate, final Date secondDate) {
		return DateUtils.compareDates(firstDate, secondDate) == -1;
	}

	/**
	 * Returns <code>true</code> if the first date is before or equal to the
	 * second date, <code>false</code> in other case.
	 *
	 * @param firstDate the first date to compare
	 * @param secondDate the second date to compare
	 * @return if the firstDate is before or equal to the secondDate
	 */
	public static boolean isBeforeOrEqual(final Date firstDate, final Date secondDate) {
		return DateUtils.compareDates(firstDate, secondDate) < 1;
	}

	/**
	 * Returns <code>true</code> if the first date is after the second date,
	 * <code>false</code> in other case.
	 *
	 * @param firstDate the first date to compare
	 * @param secondDate the second date to compare
	 * @return if the firstDate is after the secondDate
	 */
	public static boolean isAfter(final Date firstDate, final Date secondDate) {
		return DateUtils.compareDates(firstDate, secondDate) == 1;
	}

	/**
	 * Returns <code>true</code> if the first date is after or equal to the
	 * second date, <code>false</code> in other case.
	 *
	 * @param firstDate the first date to compare
	 * @param secondDate the second date to compare
	 * @return if the firstDate is after or equal to the secondDate
	 */
	public static boolean isAfterOrEqual(final Date firstDate, final Date secondDate) {
		return DateUtils.compareDates(firstDate, secondDate) > -1;
	}

	/**
	 * Returns <code>true</code> if the first date is equal to the
	 * second date, <code>false</code> in other case.
	 *
	 * @param firstDate the first date to compare
	 * @param secondDate the second date to compare
	 * @return if the firstDate is equal to the secondDate
	 */
	public static boolean areEqual(final Date firstDate, final Date secondDate) {
		return DateUtils.compareDates(firstDate, secondDate) == 0;
	}

	/**
	 * Returns <code>true</code> if the first date is equal to the
	 * second date, <code>false</code> in other case.
	 *
	 * @param firstDate the first date to compare
	 * @param secondDate the second date to compare
	 * @return if the firstDate is equal to the secondDate
	 */
	public static boolean areEqualWithTime(final Date firstDate, final Date secondDate) {
		Calendar firstDateTime = Calendar.getInstance();
		firstDateTime.setTime(firstDate);
		Calendar secondDateTime = Calendar.getInstance();
		secondDateTime.setTime(secondDate);
		return firstDateTime.compareTo(secondDateTime) == 0;
	}

	/**
	 * Sets hour, minutes, seconds and milliseconds to 0.
	 *
	 * @param date the date to reset
	 * @return a {@link Calendar} with time in 0
	 */
	public static Date resetDate(final Date date) {
		Calendar time = calendarFromDate(date);
		calendarTimeReset(time);
		return time.getTime();
	}

	/**
	 * Checks that the initial date is not after the final date of a period.
	 * Warning: the validation does not take account of time.
	 *
	 * @param initialDate the initial date
	 * @param finalDate the final date
	 * @return <code>true</code> if the period is correctly bounded
	 */
	public static boolean validateDatePeriod(final Date initialDate, final Date finalDate) {
		if (initialDate != null && finalDate != null) {
			return DateUtils.isBeforeOrEqual(DateUtils.resetDate(initialDate),
					DateUtils.resetDate(finalDate));
		} else {
			return true;
		}
	}

	/**
	 * Returns today's date with reseted hour, minutes, seconds and milliseconds.
	 *
	 * @return the actual date reseted
	 */
	public static Date getTodayDate() {
		Calendar c = Calendar.getInstance();
		calendarTimeReset(c);
		return c.getTime();
	}

	/**
	 * Returns today's date with reseted hour, minutes, seconds and milliseconds.
	 *
	 * @return the actual date reseted
	 */
	public static Date getTodayDateAndTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/** Returns the current time as a Timestamp.
	 * It is needed for certain operations (instead of using a date)
	 * where the generated object will be compared against dates recently
	 * retrieved by Hibernate from the database, as thew will be Timestamps
	 * and Timestamps shouldn't be compared with Dates.
	 * @return timestamp set to current time */
	public static Timestamp stamp() {
		return new Timestamp(new Date().getTime());
	}

	/** Test if theDate is between lowerLimit and upperLimit.
	 * @param theDate the Date
	 * @param theLowerLimit the lower limit Date
	 * @param theUpperLimit the upper limit Date
	 * @return whether theDate is between lowerLimit and upperLimit */
	public static boolean isDateBetween(final Date theDate, final Date theLowerLimit,
			final Date theUpperLimit) {
		if (null == theDate) {
			return false;
		} else {
			Date date = resetDate(theDate);
			Date lowerLimit = dateLowerBoundReset(theLowerLimit);
			Date upperLimit = dateUpperBoundReset(theUpperLimit);
			return !date.after(upperLimit) && !date.before(lowerLimit);
		}
	}

	/** Returns a zero-timed version of the given date, or
	 * a Date with YEAR and DAY_OF_YEAR set to the highest possible value if null.
	 * @param theDate the Date
	 * @return a Date */
	private static Date dateUpperBoundReset(final Date theDate) {
		Calendar c = Calendar.getInstance();
		if (null == theDate) {
			c.set(Calendar.YEAR, MAX_YEAR);
			c.set(Calendar.DAY_OF_YEAR, c.getMaximum(Calendar.DAY_OF_YEAR));
		} else {
			c.setTime(theDate);
		}
		calendarTimeReset(c);
		return c.getTime();
	}

	/** Returns a zero-timed version of the given date, or
	 * a Date with YEAR and DAY_OF_YEAR set to the lowest possible value if null.
	 * @param theDate the Date
	 * @return a Date */
	private static Date dateLowerBoundReset(final Date theDate) {
		Calendar c = Calendar.getInstance();
		if (null == theDate) {
			c.set(Calendar.YEAR, 1);
			c.set(Calendar.DAY_OF_YEAR, 1);
		} else {
			c.setTime(theDate);
		}
		calendarTimeReset(c);
		return c.getTime();
	}

	/** Sets the time of the calendar to zero.
	 * @param calendar the Calendar
	 * @return the received Calendar */
	private static Calendar calendarTimeReset(final Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, HOUR_MIN_SEC_MIL_DEFAULT);
		calendar.set(Calendar.MINUTE, HOUR_MIN_SEC_MIL_DEFAULT);
		calendar.set(Calendar.SECOND, HOUR_MIN_SEC_MIL_DEFAULT);
		calendar.set(Calendar.MILLISECOND, HOUR_MIN_SEC_MIL_DEFAULT);
		return calendar;
	}

	/** Returns a Calendar instance set with the given Date's values.
	 * @param aDate a Date
	 * @return a Calendar */
	private static Calendar calendarFromDate(final Date aDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(aDate);
		return calendar;
	}

	public static Date setTimeToDate(final Date theDate, final Integer hour, final Integer minutes,
			final Integer seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(theDate);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minutes);
		c.set(Calendar.SECOND, seconds);
		return c.getTime();
	}

	/**
	 * This method return the minimun of the two date. The null value
	 * is infinity.
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the minimun date
	 */
	public static Date getMinimunDate(final Date date1, final Date date2) {
		if (date1 == null) {
			return date2;
		}
		if (date2 == null) {
			return date1;
		}
		if (DateUtils.isBefore(date1, date2)) {
			return date1;
		} else {
			return date2;
		}
	}

	/**
	 * This method return the maximun of the two date. The null value
	 * is infinity.
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return the maximun date
	 */
	public static Date getMaximunDate(final Date date1, final Date date2) {
		if (date1 == null) {
			return date1;
		}
		if (date2 == null) {
			return date2;
		}
		if (DateUtils.isAfter(date1, date2)) {
			return date1;
		} else {
			return date2;
		}
	}

	/**
	 * Validate whether the argument string can be parsed into a
	 * legal date.<br />
	 *
	 * Does check for formating errors and illegal data (so an invalid
	 * month or day number is detected).
	 * @param allowPast set to true to allow dates in the past, false if
	 * only dates in the future should be allowed.
	 * @param formatStr date format string to be used to validate against
	 * @return true if a correct date and conforms to the restrictions
	 */

	public static boolean validateDate(String dateStr, boolean allowPast, String formatStr) {

		if (formatStr == null) {

			return false; // or throw some kinda exception, possibly a InvalidArgumentExceptionVoIP Services and Information			 
		}

		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		Date testDate = null;

		try {
			testDate = df.parse(dateStr);
		} catch (ParseException e) {
			//invalid date format		
			return false;
		}
		if (!allowPast) {

			//initialise the calendar to midnight to prevent	
			//the current day from being rejected

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			if (cal.getTime().after(testDate)) {
				return false;
			}

		}

		//now test for legal values of parameters		
		if (!df.format(testDate).equals(dateStr)) {
			return false;
		}
		return true;
	}

	/**
	 * Formatea una fecha String AAAA-MM-DD para ser visualizada
	 */
	public static String formatAAAAMMDDHHMMSS(String pFecha) {
		return pFecha.substring(8, 10) + "/" + pFecha.substring(5, 7) + "/"
				+ pFecha.substring(0, 4);
	}
}

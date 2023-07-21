package app.curioustale.curioustale.utils;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {
    private DateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getHumanReadableDate(Date timestamp) {
        SimpleDateFormat customFormat = new SimpleDateFormat("d MMM", Locale.getDefault());
        return customFormat.format(timestamp);
    }

    public static String getDayFromTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp.toDate());
        return String.format(Locale.getDefault(), "%02d", calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getMonthFromTimestamp(Timestamp timestamp) {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp.toDate());
        return monthFormat.format(calendar.getTime());
    }

    public static long getDifferenceBetweenDates(Date start, Date end) {
        Calendar startCal = getOnlyDateInstance(start);
        Calendar endCal = getOnlyDateInstance(end);
        long msDiff = endCal.getTimeInMillis() - startCal.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(msDiff);
    }

    private static Calendar getOnlyDateInstance(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
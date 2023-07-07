package app.curioustale.curioustale.utils;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {
    private DateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getCurrentUTCTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so adding 1
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);
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
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        long msDiff = endCal.getTimeInMillis() - startCal.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(msDiff);
    }
}
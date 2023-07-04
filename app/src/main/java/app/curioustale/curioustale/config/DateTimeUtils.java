package app.curioustale.curioustale.config;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

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
}
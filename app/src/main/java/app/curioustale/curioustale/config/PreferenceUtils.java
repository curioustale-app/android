package app.curioustale.curioustale.config;

import android.content.Context;
import android.content.SharedPreferences;

import app.curioustale.curioustale.utils.DateTimeUtils;

public class PreferenceUtils {
    private static final String PREFERENCES = "curious-tail-shared-preferences";
    private static final String TODAY = "curious-tail-today";

    private PreferenceUtils() {
        throw new IllegalStateException("Utility Class");
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void saveToday(Context context, String today) {
        prefs(context).edit().putString(TODAY, today).apply();
    }

    public static String getToday(Context context) {
        return prefs(context).getString(TODAY, DateTimeUtils.getCurrentUTCTime());
    }
}

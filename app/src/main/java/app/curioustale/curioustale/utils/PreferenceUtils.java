package app.curioustale.curioustale.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private static final String PREF = "curious-tale-preference";
    private static final String KEY_REFRESH = "preference-refresh-state";
    private final Context context;

    public PreferenceUtils(Context context) {
        this.context = context;
    }

    private SharedPreferences getPreference() {
        return context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return getPreference().edit();
    }

    public void storeRefreshStateOfStories(boolean refresh) {
        getEditor().putBoolean(KEY_REFRESH, refresh).apply();
    }

    public boolean getRefreshStateOfStories() {
        return getPreference().getBoolean(KEY_REFRESH, false);
    }
}

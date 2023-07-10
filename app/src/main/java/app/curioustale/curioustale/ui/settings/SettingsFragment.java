package app.curioustale.curioustale.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.ui.settings.sheet.SuggestionSheet;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference twitterPreference = findPreference("twitter");
        Preference discordPreference = findPreference("discord");
        Preference suggestionPreference = findPreference("suggestion");

        if (twitterPreference != null) {
            twitterPreference.setIntent(getIntentForUrl(Constants.TWITTER_LINK));
        }
        if (discordPreference != null) {
            discordPreference.setIntent(getIntentForUrl(Constants.DISCORD_LINK));
        }
        if (suggestionPreference != null) {
            suggestionPreference.setOnPreferenceClickListener(this::handleOnSuggestionClicked);
        }
    }

    private boolean handleOnSuggestionClicked(Preference preference) {
        SuggestionSheet sheet = new SuggestionSheet();
        sheet.show(getChildFragmentManager(), SuggestionSheet.TAG);
        return true;
    }

    private Intent getIntentForUrl(String url) {
        return new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
    }
}
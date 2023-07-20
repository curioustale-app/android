package app.curioustale.curioustale.utils;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FirebaseUtils {
    public static final String COLLECTION_QUESTIONS = "questions";
    public static final String COLLECTION_ANSWERS = "answers";
    public static final String COLLECTION_USERS_ANSWERS = "users-answers";
    public static final String COLLECTION_USERS = "users";
    public static final String COLLECTION_INSIGHTS = "insights";
    public static final String COLLECTION_SUGGESTIONS = "suggestions";

    private FirebaseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String firebaseTimestampToDayKey(Timestamp timestamp) {
        Date parsed = timestamp.toDate();
        SimpleDateFormat customFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return customFormat.format(parsed);
    }

    public static String getToday() {
        return firebaseTimestampToDayKey(Timestamp.now());
    }

    public static Date getTodayAsDate() {
        return Timestamp.now().toDate();
    }
}

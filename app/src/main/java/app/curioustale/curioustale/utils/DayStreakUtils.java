package app.curioustale.curioustale.utils;

import com.google.firebase.Timestamp;

import app.curioustale.curioustale.models.User;

public class DayStreakUtils {
    private DayStreakUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Cases:
     * Case 1: When the user is answering for the first time
     * Case 2: When the user is answering on the same day
     * Case 3: When the user is answering on new day
     * Case 4: When the user is answering after X days from previous session
     */
    public static User updateStreak(User user, Timestamp today) {
        // case 1
        if (user.getLastSubmissionDate() == null) {
            user.setStreak(1);
            user.setLastSubmissionDate(today.toDate());
            return user;
        }

        long difference = DateTimeUtils.getDifferenceBetweenDates(user.getLastSubmissionDate(), today.toDate());
        user.setLastSubmissionDate(today.toDate());

        // case 2
        if (difference == 0) return user;

        // case 3
        if (difference == 1) {
            user.setStreak(user.getStreak() + 1);
            return user;
        }

        // case 4
        if (difference > 1) {
            user.setStreak(1);
            return user;
        }

        return user;
    }
}

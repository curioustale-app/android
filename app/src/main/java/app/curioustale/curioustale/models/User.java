package app.curioustale.curioustale.models;

import java.util.Date;

@SuppressWarnings("ALL")
public class User {
    private String userId;
    private Date lastSubmissionDate;
    private int streak;

    public User() {
        // required Firebase
    }

    public User(String userId) {
        this.userId = userId;
        this.streak = 0;
        this.lastSubmissionDate = null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastSubmissionDate() {
        return lastSubmissionDate;
    }

    public void setLastSubmissionDate(Date lastSubmissionDate) {
        this.lastSubmissionDate = lastSubmissionDate;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", lastSubmissionDate=" + lastSubmissionDate +
                ", streak=" + streak +
                '}';
    }
}

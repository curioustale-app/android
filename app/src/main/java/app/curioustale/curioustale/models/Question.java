package app.curioustale.curioustale.models;

import java.util.List;

public class Question {
    private String title;
    private List<String> tags;

    public Question() {
        // Unused
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }
}

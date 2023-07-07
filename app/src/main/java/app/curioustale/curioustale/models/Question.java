package app.curioustale.curioustale.models;

import java.util.List;

@SuppressWarnings("ALL")
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", tags=" + tags +
                '}';
    }
}

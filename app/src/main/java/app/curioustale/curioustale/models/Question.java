package app.curioustale.curioustale.models;

import androidx.annotation.Keep;

import java.util.List;

@SuppressWarnings("ALL")
@Keep
public class Question {
    private String title;
    private List<String> tags;

    public Question() {
        // Unused
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
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

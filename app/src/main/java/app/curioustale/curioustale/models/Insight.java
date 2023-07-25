package app.curioustale.curioustale.models;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Locale;

import app.curioustale.curioustale.utils.DateTimeUtils;

@SuppressWarnings("ALL")
@Keep
public class Insight {
    private String title;
    private String subtitle;
    private String type;
    private String url;
    private Date timestamp;

    public Insight() {
        // Unused
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormattedDayAndType() {
        return String.format(Locale.getDefault(), "%s • %s",
                this.type.toLowerCase(Locale.getDefault()),
                DateTimeUtils.getHumanReadableDate(this.timestamp)
        );
    }

    @NonNull
    @Override
    public String toString() {
        return "Insight{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

package main;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class clientCluster implements Serializable {
    private String user;
    private String text;
    private String history;
    private LocalDateTime date;

    public clientCluster(String user, String text, String history) {
        this.user = user;
        this.text = text;
        this.date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.history = history;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFormatedMessage() {
        return date + " " + user + ": " + text;
    }
}

package jansen.thomas.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable{
    private int _id;
    private String title;
    private String content;
    private String mood;
    private String timestamp;

    public JournalEntry(int id, String title, String content, String mood, String timestamp) {
        this._id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public int getId() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}


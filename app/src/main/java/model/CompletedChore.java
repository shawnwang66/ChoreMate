package model;

import android.graphics.drawable.Drawable;

public class CompletedChore {
    private Drawable image;
    private String name;
    private String time;
    private String completer;
    private int upvote;
    private int downvote;
    private boolean visible;

    public CompletedChore(Drawable image, String name, String time, String completer, int upvote, int downvote, boolean visible) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.completer = completer;
        this.upvote = upvote;
        this.downvote = downvote;
        this.visible = visible;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompleter() {
        return completer;
    }

    public void setCompleter(String completer) {
        this.completer = completer;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

package model;

public class CompletedChore {
    private int image;
    private String name;
    private String time;
    private String completer;
    private int upvote;
    private int downvote;

    public CompletedChore(int image, String name, String time, String completer, int upvote, int downvote) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.completer = completer;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
}

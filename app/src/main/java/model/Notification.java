package model;

public class Notification {
    private int avatar;
    private String name;
    private String time;
    private String message;
    private String action;


    public Notification(int avatar, String name, String time, String message, String action) {
        this.avatar = avatar;
        this.name = name;
        this.time = time;
        this.message = message;
        this.action = action;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

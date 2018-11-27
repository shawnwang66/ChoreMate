package model;

public class Notification {
    private User user;
    private String time;
    private String message;
    private String action;

    public Notification(int avatar, String name, String time, String message, String action) {
        user = new User(name,avatar);
        this.time = time;
        this.message = message;
        this.action = action;
    }

    public int getAvatar() {
        return user.getAvatar();
    }

    public void setAvatar(int avatar) {
        this.user.setAvatar(avatar);
    }

    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        this.user.setName(name);
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

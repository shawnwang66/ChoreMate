package model;

public class Chore {
    private String choreName;
    private String choreDueDate;
    private String choreDueTime;
    private User assignee;
    private int difficulty;

    public Chore(String choreName, String choreDueDate, String choreDueTime, User assignee, int difficulty) {
        this.choreName = choreName;
        this.choreDueDate = choreDueDate;
        this.choreDueTime = choreDueTime;
        this.assignee = assignee;
        this.difficulty = difficulty;
    }

    // getters
    public String getChoreName() {
        return choreName;
    }

    public String getChoreDueDate() {
        return choreDueDate;
    }

    public String getChoreDueTime() {
        return choreDueTime;
    }

    public User getAssignee() {
        return assignee;
    }

    public int getDifficulty() {
        return difficulty;
    }

    // setters
    public void setChoreName(String choreName) {
        this.choreName = choreName;
    }

    public void setChoreDueDate(String choreDueDate) {
        this.choreDueDate = choreDueDate;
    }

    public void setChoreDueTime(String choreDueTime) {
        this.choreDueTime = choreDueTime;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}

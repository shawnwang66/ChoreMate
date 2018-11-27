package model;

public class Chore {
    private String choreaName;
    private String choreDueDate;


    public Chore(String choreaName, String choreDueDate) {
        this.choreaName = choreaName;
        this.choreDueDate = choreDueDate;
    }

    public String getChoreaName() {
        return choreaName;
    }

    public void setChoreaName(String choreaName) {
        this.choreaName = choreaName;
    }

    public String getChoreDueDate() {
        return choreDueDate;
    }

    public void setChoreDueDate(String choreDueDate) {
        this.choreDueDate = choreDueDate;
    }
}

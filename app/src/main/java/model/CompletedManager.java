package model;

import java.util.List;

import kwang66.edu.choremate.R;

public class CompletedManager {

    public List<CompletedChore> chores;
    private static CompletedManager instance = null;

    private CompletedManager() {

    }

    public static CompletedManager getInstance() {
        if(instance == null) {
            instance = new CompletedManager();
        }
        return instance;
    }

}

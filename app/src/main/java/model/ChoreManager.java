package model;

import java.util.ArrayList;
import java.util.List;

public class ChoreManager {

    public List<Chore> chores = new ArrayList<>();;
    private static ChoreManager instance = null;

    private ChoreManager() {

    }

    public static ChoreManager getInstance() {
        if(instance == null) {
            instance = new ChoreManager();

        }
        return instance;
    }

}

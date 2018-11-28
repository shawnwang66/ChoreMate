package model;

import java.util.ArrayList;
import java.util.List;

public class ChoreManager {

    public List<Chore> chores = new ArrayList<>();
    public List<Chore> groupChore = new ArrayList<>();
    private static ChoreManager instance = null;

    private ChoreManager() {

        Chore addChore = new Chore("Take out trash","11/27/2018","05:05PM",
                UserManager.getInstance().users.get(1),1);
        groupChore.add(addChore);
        addChore = new Chore("Take out trash","11/28/2018","01:05PM",
                UserManager.getInstance().users.get(2),1);
        groupChore.add(addChore);
        addChore = new Chore("Buy Coca-Cola","11/30/2018","04:20PM",
                UserManager.getInstance().users.get(3),1);
        groupChore.add(addChore);
        addChore = new Chore("Wash Dishes","11/31/2018","07:20AM",
                UserManager.getInstance().users.get(2),1);
        groupChore.add(addChore);

    }

    public static ChoreManager getInstance() {
        if(instance == null) {
            instance = new ChoreManager();


        }
        return instance;
    }

}

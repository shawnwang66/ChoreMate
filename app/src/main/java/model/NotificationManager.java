package model;

import java.util.List;

import kwang66.edu.choremate.R;

public class NotificationManager {

    public List<Notification> notifications;
    private static NotificationManager instance = null;

    private NotificationManager() {
        Notification Note1 = new Notification(R.drawable.john,"John","12:30pm, Nov 27th","I have finished a chore(clean dishes),check it out!","view");
        Notification Note2 = new Notification(R.drawable.dez,"Dez","3:24pm, Nov 27th","I have created a chore(Buy Coke) and assigned it to Gunther.","detail");
        Notification Note3 = new Notification(R.drawable.matt,"Matt", "11:14am, Nov 24th","I have created a chore(clean dishes) and assigned to John.",null);
        Notification Note4 = new Notification(R.drawable.dez,"John","2:54pm, Nov 24th","I accepted the chore(clean dishes) created by Matt.",null);

        notifications.add(Note1);
        notifications.add(Note2);
        notifications.add(Note4);
        notifications.add(Note3);
    }

    public static NotificationManager getInstance() {
        if(instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

}

package model;

import java.util.ArrayList;
import java.util.List;

import kwang66.edu.choremate.R;

public class NotificationManager {

    public List<Notification> notifications = new ArrayList<>();
    private static NotificationManager instance = null;

    private NotificationManager() {
        Notification Note1 = new Notification(R.drawable.john,"John","12:30 11/17","I have finished a chore(clean dishes),check it out!","view");
        Notification Note2 = new Notification(R.drawable.dez,"Dez","15:24 11/27","I have created a chore(Buy Coke) and assigned it to Gunther.","detail");
        Notification Note3 = new Notification(R.drawable.matt,"Matt", "11:14 11/24","I have created a chore(clean dishes) and assigned to John.",null);
        Notification Note4 = new Notification(R.drawable.dez,"John","14:54 11/24","I accepted the chore(clean dishes) created by Matt.",null);

        notifications.add(Note3);
        notifications.add(Note4);
        notifications.add(Note2);
        notifications.add(Note1);
    }

    public static NotificationManager getInstance() {
        if(instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

}

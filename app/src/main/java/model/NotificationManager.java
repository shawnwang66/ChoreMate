package model;

import java.util.List;

public class NotificationManager {

    public List<Notification> notifications;
    private static NotificationManager instance = null;

    private NotificationManager() {

    }

    public static NotificationManager getInstance() {
        if(instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

}

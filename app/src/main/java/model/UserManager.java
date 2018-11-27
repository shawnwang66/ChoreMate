package model;

import java.util.ArrayList;
import java.util.List;

import kwang66.edu.choremate.R;

public class UserManager {

    public List<User> users = new ArrayList<>();
    private static UserManager instance = null;

    private UserManager() {
        User john = new User("John", R.drawable.john);
        User dez = new User("Dez", R.drawable.dez);
        User matt = new User("Matt", R.drawable.matt);
        User gunther = new User("Gunther", R.drawable.gunther);

        users.add(john);
        users.add(dez);
        users.add(matt);
        users.add(gunther);
    }

    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getUser(String name){
        for (User user : users){
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }
}

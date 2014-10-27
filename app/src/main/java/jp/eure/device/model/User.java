package jp.eure.device.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by katsuyagoto on 2014/10/25.
 */
public class User {
    public static Map<Long, User> USER_MAP = new HashMap<Long, User>();
    private long identity;
    private String name;

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void put(User user){
        USER_MAP.put(user.getIdentity(), user);
    }

    public static User get(long identity){
        return USER_MAP.get(identity);
    }

    public static List<User> list(){
        List<User> users = new ArrayList<User>();
        for (User user : USER_MAP.values()){
            users.add(user);
        }
        return users;
    }
}

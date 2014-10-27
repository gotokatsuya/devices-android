package jp.eure.device.model.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.eure.device.model.Device;
import jp.eure.device.model.User;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class UserParser {

    private enum ParamKeys {
        KEY_ID("id"),
        KEY_NAME("name");

        private String mKey;

        private ParamKeys(String key) {
            mKey = key;
        }

        public String getKey() {
            return mKey;
        }
    }

    public static List<User> parse(JSONArray array) {
        List<User> users = new ArrayList<User>();
        for (int i=0, n=array.length(); i<n; i++){
            try {
                User user = parse(array.getJSONObject(i));
                if (user != null && user.getIdentity() > 0L) {
                    users.add(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public static User parse(JSONObject obj) {
        User user = null;
        try {
            long identity = obj.getLong(ParamKeys.KEY_ID.getKey());
            user = User.get(identity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (user == null){
            user = new User();
        }

        for (ParamKeys key : ParamKeys.values()) {
            try {
                setParam(user, obj, key);
            } catch (JSONException e) {
                continue;
            }
        }

        User.put(user);

        return user;
    }

    private static void setParam(User user, JSONObject obj, ParamKeys key) throws JSONException {
        switch (key) {
            case KEY_ID:
                user.setIdentity(obj.getLong(key.getKey()));
                break;
            case KEY_NAME:
                user.setName(obj.getString(key.getKey()));
                break;
        }
    }

}

package jp.eure.device.request;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.eure.device.helper.ThreadHelper;
import jp.eure.device.model.User;
import jp.eure.device.model.parser.UserParser;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class UserRequest extends BaseRequest {

    enum UserMethod{
        CREATE("users/create"),
        UPDATE("users/update"),
        LIST("users/list");

        private String method;

        UserMethod(String method) {
            this.method = method;
        }
    }

    private static UserRequest ourInstance = new UserRequest();

    private static UserRequest getInstance() {
        return ourInstance;
    }

    public static void create(final String username, final Response.Listener<User> successListener){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);

        String method = UserMethod.CREATE.method;
        getInstance().post(method, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                ThreadHelper.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject userObj = response.getJSONObject("user");
                            final User user = UserParser.parse(userObj);
                            ThreadHelper.runOnUi(new Runnable() {
                                @Override
                                public void run() {
                                    successListener.onResponse(user);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public static void update(final long user_id, final String username, final Response.Listener<User> successListener){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("user_id", String.valueOf(user_id));
        if (username != null) {
            params.put("username", username);
        }
        String method = UserMethod.UPDATE.method;
        getInstance().post(method, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                ThreadHelper.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject userObj = response.getJSONObject("user");
                            final User user = UserParser.parse(userObj);
                            ThreadHelper.runOnUi(new Runnable() {
                                @Override
                                public void run() {
                                    successListener.onResponse(user);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public static void list(final Response.Listener<List<User>> successListener){
        String method = UserMethod.LIST.method;
        getInstance().get(method, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                ThreadHelper.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray usersObj = response.getJSONArray("users");
                            final List<User> users = UserParser.parse(usersObj);
                            ThreadHelper.runOnUi(new Runnable() {
                                @Override
                                public void run() {
                                    successListener.onResponse(users);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}

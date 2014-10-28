package jp.eure.device.model.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.eure.device.model.Device;
import jp.eure.device.model.DeviceState;
import jp.eure.device.model.User;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class DeviceStateParser {

    private enum ParamKeys {
        KEY_ID("id"),
        KEY_DEVICE_ID("device_id"),
        KEY_ACTION("action"),
        KEY_USER("user"),
        KEY_CREATED_AT("createdAt");

        private String mKey;

        private ParamKeys(String key) {
            mKey = key;
        }

        public String getKey() {
            return mKey;
        }
    }

    public static List<DeviceState> parse(JSONArray array) {
        List<DeviceState> deviceStates = new ArrayList<DeviceState>();
        for (int i=0, n=array.length(); i<n; i++){
            try {
                DeviceState deviceState = parse(array.getJSONObject(i));
                if (deviceState != null && deviceState.getIdentity() > 0L) {
                    deviceStates.add(deviceState);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return deviceStates;
    }

    public static DeviceState parse(JSONObject obj) {
        DeviceState deviceState = null;
        try {
            long identity = obj.getLong(ParamKeys.KEY_ID.getKey());
            deviceState = DeviceState.get(identity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (deviceState == null) {
            deviceState = new DeviceState();
        }

        for (ParamKeys key : ParamKeys.values()) {
            try {
                setParam(deviceState, obj, key);
            } catch (JSONException e) {
                continue;
            }
        }

        DeviceState.put(deviceState);

        return deviceState;
    }

    private static void setParam(DeviceState deviceState, JSONObject obj, ParamKeys key) throws JSONException {
        switch (key) {
            case KEY_ID:
                deviceState.setIdentity(obj.getLong(key.getKey()));
                break;
            case KEY_DEVICE_ID:
                deviceState.setIdentity(obj.getLong(key.getKey()));
                break;
            case KEY_ACTION:
                deviceState.setAction(obj.getBoolean(key.getKey()));
                break;
            case KEY_CREATED_AT:
                deviceState.setCreatedAt(obj.getLong(key.getKey()));
                break;
            case KEY_USER:
                JSONObject userObj = obj.getJSONObject(key.getKey());
                deviceState.setUser(UserParser.parse(userObj));
                break;
        }
    }

}

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
public class DeviceParser {

    private enum ParamKeys {
        KEY_ID("id"),
        KEY_NAME("name"),
        KEY_MANUFACTURER("manufacturer"),
        KEY_CARRIER("carrier"),
        KEY_OS("os"),
        KEY_SIZE("size"),
        KEY_RESOLUTION("resolution"),
        KEY_MEMORY("memory"),
        KEY_DATE_OF_RELEASE("dateOfRelease"),
        KEY_OTHER("other"),
        KEY_USER("user"),
        KEY_DEVICE_STATES("device_states");

        private String mKey;

        private ParamKeys(String key) {
            mKey = key;
        }

        public String getKey() {
            return mKey;
        }
    }

    public static List<Device> parse(JSONArray array) {
        List<Device> devices = new ArrayList<Device>();
        for (int i=0, n=array.length(); i<n; i++){
            try {
                Device device = parse(array.getJSONObject(i));
                if (device != null && device.getIdentity() > 0L) {
                    devices.add(device);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return devices;
    }

    public static Device parse(JSONObject obj) {
        Device device = null;
        try {
            long identity = obj.getLong(ParamKeys.KEY_ID.getKey());
            device = Device.get(identity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (device == null) {
            device = new Device();
        }

        for (ParamKeys key : ParamKeys.values()) {
            try {
                setParam(device, obj, key);
            } catch (JSONException e) {
                continue;
            }
        }

        Device.put(device);

        return device;
    }

    private static void setParam(Device device, JSONObject obj, ParamKeys key) throws JSONException {
        switch (key) {
            case KEY_ID:
                device.setIdentity(obj.getLong(key.getKey()));
                break;
            case KEY_NAME:
                device.setName(obj.getString(key.getKey()));
                break;
            case KEY_MANUFACTURER:
                device.setManufacturer(obj.getString(key.getKey()));
                break;
            case KEY_CARRIER:
                device.setCarrier(obj.getString(key.getKey()));
                break;
            case KEY_OS:
                device.setOs(obj.getString(key.getKey()));
                break;
            case KEY_SIZE:
                device.setSize(obj.getString(key.getKey()));
                break;
            case KEY_RESOLUTION:
                device.setResolution(obj.getString(key.getKey()));
                break;
            case KEY_MEMORY:
                device.setMemory(obj.getString(key.getKey()));
                break;
            case KEY_DATE_OF_RELEASE:
                device.setDateOfRelease(obj.getLong(key.getKey()));
                break;
            case KEY_OTHER:
                device.setOther(obj.getString(key.getKey()));
                break;
            case KEY_USER:
                JSONObject userObj = obj.getJSONObject(key.getKey());
                device.setUser(UserParser.parse(userObj));
                break;
            case KEY_DEVICE_STATES:
                JSONArray array = obj.getJSONArray(key.getKey());
                device.setDeviceStates((ArrayList)DeviceStateParser.parse(array));
                break;
        }
    }

}

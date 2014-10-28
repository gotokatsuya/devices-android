package jp.eure.device.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by katsuyagoto on 2014/10/28.
 */
public class DeviceState {
    public static Map<Long, DeviceState> DEVICE_STATE_MAP = new HashMap<Long, DeviceState>();

    private long identity;

    private long deviceId;

    private boolean state;

    private User user;

    private long createdAt;

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public static void put(DeviceState deviceState){
        if (DEVICE_STATE_MAP == null){
            return;
        }
        DEVICE_STATE_MAP.put(deviceState.getIdentity(), deviceState);
    }

    public static DeviceState get(long identity){
        if (DEVICE_STATE_MAP == null){
            return null;
        }
        return DEVICE_STATE_MAP.get(identity);
    }

    public String getStateText() {
        if (isState()) {
            return "貸";
        } else {
            return "返";
        }
    }

}

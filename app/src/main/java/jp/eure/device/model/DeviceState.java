package jp.eure.device.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by katsuyagoto on 2014/10/28.
 */
public class DeviceState {
    public static Map<Long, DeviceState> DEVICE_STATE_MAP = new HashMap<Long, DeviceState>();

    private long identity;
    private long device_id;
    private boolean action;
    private User user;
    private long createdAt;

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(long device_id) {
        this.device_id = device_id;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
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
        DEVICE_STATE_MAP.put(deviceState.getIdentity(), deviceState);
    }

    public static DeviceState get(long identity){
        return DEVICE_STATE_MAP.get(identity);
    }

}

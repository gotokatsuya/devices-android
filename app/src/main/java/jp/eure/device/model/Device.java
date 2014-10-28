package jp.eure.device.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by katsuyagoto on 2014/10/25.
 */
public class Device {
    public static Map<Long, Device> DEVICE_MAP = new HashMap<Long, Device>();
    private long identity;
    private String name;
    private String manufacturer;
    private String carrier;
    private String os;
    private String size;
    private String resolution;
    private String memory;
    private long dateOfRelease;
    private String other;
    private User user;
    private ArrayList<DeviceState> deviceStates;
    private boolean state;

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

    public String getManufacturer() {
        if (manufacturer == null) {
            return "";
        }
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCarrier() {
        if (carrier == null) {
            return "";
        }
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getOs() {
        if (os == null) {
            return "";
        }
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getSize() {
        if (size == null) {
            return "";
        }
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResolution() {
        if (resolution == null) {
            return "";
        }
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMemory() {
        if (memory == null) {
            return "";
        }
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public long getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(long dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public String getOther() {
        if (other == null) {
            return "";
        }
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<DeviceState> getDeviceStates() {
        return deviceStates;
    }

    public void setDeviceStates(ArrayList<DeviceState> deviceStates) {
        this.deviceStates = deviceStates;
    }

    public static void put(Device device){
        if (DEVICE_MAP == null){
            return;
        }
        DEVICE_MAP.put(device.getIdentity(), device);
    }


    public static Device get(long identity){
        if (DEVICE_MAP == null){
            return null;
        }
        return DEVICE_MAP.get(identity);
    }

    public static List<Device> list(){
        List<Device> devices = new ArrayList<Device>();
        for (Device device : DEVICE_MAP.values()){
            devices.add(device);
        }
        return devices;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getStateText() {
        if (isState()) {
            return "貸出中";
        } else {
            return "貸出可";
        }
    }

}

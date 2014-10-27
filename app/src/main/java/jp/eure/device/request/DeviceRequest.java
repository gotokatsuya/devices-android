package jp.eure.device.request;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import jp.eure.device.helper.ThreadHelper;
import jp.eure.device.model.Device;
import jp.eure.device.model.parser.DeviceParser;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class DeviceRequest extends BaseRequest {

    enum DeviceMethod{
        CREATE("devices/create"),
        UPDATE("devices/update"),
        LIST("devices/list"),
        BORROW("devices/borrow");

        private String method;

        DeviceMethod(String method) {
            this.method = method;
        }
    }

    private static DeviceRequest ourInstance = new DeviceRequest();

    private static DeviceRequest getInstance() {
        return ourInstance;
    }

    public static void create(final String name,
                              final String manufacturer,
                              final String carrier,
                              final String os,
                              final String size,
                              final String resolution,
                              final String memory,
                              final long dateOfRelease,
                              final String other,
                              final Response.Listener<Device> successListener){
        HashMap<String, String> params = new HashMap<String, String>();
        if (name != null) {
            params.put("name", name);
        }
        if (manufacturer != null) {
            params.put("manufacturer", manufacturer);
        }
        if (carrier != null) {
            params.put("carrier", carrier);
        }
        if (os != null) {
            params.put("os", os);
        }
        if (size != null) {
            params.put("size", size);
        }
        if (resolution != null) {
            params.put("resolution", resolution);
        }
        if (memory != null) {
            params.put("memory", memory);
        }
        if (dateOfRelease > 0L) {
            params.put("dateOfRelease", String.valueOf(dateOfRelease));
        }
        if (other != null) {
            params.put("other", other);
        }
        String method = DeviceMethod.CREATE.method;
        getInstance().post(method, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                ThreadHelper.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject deviceObj = response.getJSONObject("device");
                            final Device device = DeviceParser.parse(deviceObj);
                            ThreadHelper.runOnUi(new Runnable() {
                                @Override
                                public void run() {
                                    successListener.onResponse(device);
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

    public static void update(final long device_id,
                              final String name,
                              final String manufacturer,
                              final String carrier,
                              final String os,
                              final String size,
                              final String resolution,
                              final String memory,
                              final long dateOfRelease,
                              final String other,
                              final Response.Listener<Device> successListener){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("device_id", String.valueOf(device_id));
        if (name != null) {
            params.put("name", name);
        }
        if (manufacturer != null) {
            params.put("manufacturer", manufacturer);
        }
        if (carrier != null) {
            params.put("carrier", carrier);
        }
        if (os != null) {
            params.put("os", os);
        }
        if (size != null) {
            params.put("size", size);
        }
        if (resolution != null) {
            params.put("resolution", resolution);
        }
        if (memory != null) {
            params.put("memory", memory);
        }
        if (dateOfRelease > 0L) {
            params.put("dateOfRelease", String.valueOf(dateOfRelease));
        }
        if (other != null) {
            params.put("other", other);
        }
        String method = DeviceMethod.UPDATE.method;
        getInstance().post(method, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                ThreadHelper.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject deviceObj = response.getJSONObject("device");
                            final Device device = DeviceParser.parse(deviceObj);
                            ThreadHelper.runOnUi(new Runnable() {
                                @Override
                                public void run() {
                                    successListener.onResponse(device);
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

    public static void list(final Response.Listener<List<Device>> successListener){
        String method = DeviceMethod.LIST.method;
        getInstance().get(method, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                ThreadHelper.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray devicesObj = response.getJSONArray("devices");
                            final List<Device> devices = DeviceParser.parse(devicesObj);
                            ThreadHelper.runOnUi(new Runnable() {
                                @Override
                                public void run() {
                                    successListener.onResponse(devices);
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

    public static void borrow(final long user_id,
                              final long device_id ,
                              final Response.Listener<Device> successListener){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("user_id", String.valueOf(user_id));
        params.put("device_id", String.valueOf(device_id));
        String method = DeviceMethod.BORROW.method;
        getInstance().post(method, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                ThreadHelper.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject deviceObj = response.getJSONObject("device");
                            final Device device = DeviceParser.parse(deviceObj);
                            ThreadHelper.runOnUi(new Runnable() {
                                @Override
                                public void run() {
                                    successListener.onResponse(device);
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

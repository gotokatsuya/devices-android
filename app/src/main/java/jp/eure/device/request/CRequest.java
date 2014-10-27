package jp.eure.device.request;

/**
 * Created by katsuyagoto on 2014/10/25.
 */

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import jp.eure.device.request.volley.VolleyManager;
import jp.eure.device.util.Logger;


/**
 * Created by katsuyagoto on 2014/06/19.
 */
public class CRequest extends com.android.volley.Request<JSONObject> {

    private final Response.Listener<JSONObject> mListener;
    private Map<String, String> mParams;


    public CRequest(int method, String url, Response.Listener<JSONObject> listener) {
        super(method, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error != null && error.networkResponse != null) {
                    if(!TextUtils.isEmpty(error.getMessage())){
                        Logger.E("Error", error.getMessage());
                    }
                }
            }
        });
        mListener = listener;
        Logger.D("Request url", getUrl());
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        Logger.D("Request response", response.toString());
		if(mListener != null)
            mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getParams(){
        return mParams;
    }


    public void setParams(Map<String, String> params){
        this.mParams = params;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        JSONObject resultJson;
        try {
            String data = new String(response.data);
            resultJson = new JSONObject(data);
        } catch (JSONException e) {
            Logger.E("Error", "parseNetworkResponse JSONException");
            return Response.error(new VolleyError());
        }

        if (!success(resultJson)){
            Logger.E("Error", "Success => false");
            return Response.error(new VolleyError());
        }

        return Response.success(resultJson, HttpHeaderParser.parseCacheHeaders(response));
    }

    protected boolean success(JSONObject object){
        boolean success = false;
        try {
            success = object.getBoolean("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void start() {
        VolleyManager.getInstance().addToRequestQueue(this);
    }

}

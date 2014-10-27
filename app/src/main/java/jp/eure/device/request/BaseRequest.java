package jp.eure.device.request;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jp.eure.device.Config;
import jp.eure.device.request.volley.ExRetryPolicy;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class BaseRequest {

    protected void post(
            final String methodName,
            final HashMap<String, String> params,
            final Response.Listener<JSONObject> listener){

        CRequest request
                = new CRequest(
                com.android.volley.Request.Method.POST,
                buildMethodUrl(methodName),
                listener) {
        };
        request.setParams(params);
        request.setRetryPolicy(new ExRetryPolicy(request));
        request.start();
    }

    protected void get(
            final String methodName,
            final HashMap<String, String> params,
            final Response.Listener<JSONObject> listener){

        CRequest request
                = new CRequest(
                com.android.volley.Request.Method.GET,
                appendParams(buildMethodUrl(methodName), params),
                listener) {
        };
        request.setRetryPolicy(new ExRetryPolicy(request));
        request.start();
    }


    private static String buildMethodUrl(String method) {
        StringBuilder builder = new StringBuilder(Config.getAPIBaseUrl());
        builder.append(method);
        builder.append("?");
        return builder.toString();
    }

    protected static String appendParams(String url, HashMap<String, String> params){
        if(params == null){
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append('&');
            builder.append(entry.getKey());
            builder.append('=');
            builder.append(entry.getValue());
        }
        return builder.toString();
    }
}

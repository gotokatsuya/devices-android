package jp.eure.device;

import android.app.Application;

import jp.eure.device.request.volley.VolleyManager;

/**
 * Created by katsuyagoto on 2014/10/26.
 */
public class Devices extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
		/*Volley*/
        VolleyManager.getInstance().init(getApplicationContext());
    }

}

package jp.eure.device.util;

import android.util.Log;

import jp.eure.device.Config;

/**
 * Created by katsuyagoto on 2014/10/25.
 */
public class Logger {

    public static void D(String tag, String msg){
        if(Config.DEBUG_MODE){
            Log.d(tag, msg);
        }
    }

    public static void E(String tag, String msg){
        if(Config.DEBUG_MODE){
            Log.e(tag, msg);
        }
    }
}

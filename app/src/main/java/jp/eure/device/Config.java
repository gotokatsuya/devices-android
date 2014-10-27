package jp.eure.device;

/**
 * Created by katsuyagoto on 2014/10/25.
 */
public class Config {
    public static boolean DEBUG_MODE = true;
    public static String VERSION = "1.0.0";

    //TODO Divide url according to mode
    public static String getAPIBaseUrl() {
        return "http://54.64.150.114/api/";
    }
}

package com.yueweather.app.util;

import android.util.Log;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/16 16:17
 * 修改人：lenovo
 * 修改时间：2016/11/16 16:17
 * 修改备注：
 */

public class LogUtil {
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;
    private static final int LEVEL = VERBOSE;
    public static final String TAG = "YueWeather";

    public static void v(String tag, String msg){
        if(LEVEL <= VERBOSE){
            Log.v(tag,msg);
        }
    }
    public static void d(String tag,  String msg){
        if (LEVEL <= DEBUG){
            Log.d(tag,msg);
        }
    }
    public static void i(String tag, String msg){
        if (LEVEL <= INFO){
            Log.i(tag,msg);
        }
    }
    public static void w(String tag, String msg){
        if (LEVEL <= WARN){
            Log.w(tag,msg);
        }
    }
    public  static void e(String tag, String msg){
        if (LEVEL <= ERROR){
            Log.e(tag,msg);
        }
    }

}

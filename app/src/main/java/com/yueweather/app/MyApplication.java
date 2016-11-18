package com.yueweather.app;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/18 11:22
 * 修改人：lenovo
 * 修改时间：2016/11/18 11:22
 * 修改备注：
 */
public class MyApplication extends Application {
    public static Context context;
    public  static Context getContext(){
         return  context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

}

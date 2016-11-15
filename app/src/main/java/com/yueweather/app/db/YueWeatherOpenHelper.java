package com.yueweather.app.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/15 13:41
 * 修改人：lenovo
 * 修改时间：2016/11/15 13:41
 * 修改备注：
 */

public class YueWeatherOpenHelper extends SQLiteOpenHelper {
   public static final String CREATE_PROVINCE = "create table Province (" +
           ")";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

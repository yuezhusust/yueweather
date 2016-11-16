package com.yueweather.app.db;

import android.content.Context;
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

    /**
     * Province 表建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province (" + "id integer primary key autoincrement, " + " province_name text," + "province_code text)";

    /**
     * City表建表语句
     */
    public static final String CREATE_CITY = "create table City (" + "id integer primary key autoincrement, "+" city_name text, " + "city_code text," + " province_id integer )";
    /**
     * County表建表语句
     */
    public static final String CREATE_COUNTY = "create table County(" + " id integer primary_key autoincrement, " + "county_name text, " + " county_code text, " + " city_id integer)";

    public YueWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     * @param sqLiteDatabase
     */
    @Override
    /**   
    * @desd: onCreate
    * @author：lenovo   
    * @time ：2016/11/16 9:23    
    * @params: [sqLiteDatabase] 
    * @return: void  
    */ 
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_COUNTY);

    }

    @Override
    /**   
    * @desd: onUpgrade
    * @author：lenovo   
    * @time ：2016/11/16 9:23    
    * @params: [sqLiteDatabase, i, i1] 
    * @return: void  
    */ 
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

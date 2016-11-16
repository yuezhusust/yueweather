package com.yueweather.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yueweather.app.db.YueWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/16 15:28
 * 修改人：lenovo
 * 修改时间：2016/11/16 15:28
 * 修改备注：
 */

public class YueWeatherDB {
    public static final String DB_NAME = "yue_weather";
    public static final int VERSION = 1;
    private static YueWeatherDB yueWeatherDB ;
    private SQLiteDatabase db;

    private YueWeatherDB(Context context) {
        YueWeatherOpenHelper dbHelper = new YueWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static YueWeatherDB getInstance(Context context){
        if (yueWeatherDB == null){
            yueWeatherDB = new YueWeatherDB(context);
        }
        return yueWeatherDB;
    }
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    public List<Province> loadProvinces(){
        List<Province> provinceList = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provinceList.add(province);
            }while (cursor.moveToNext());
        }
        return provinceList;
    }
    public void saveCity(City city){
        if (city != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name",city.getCityName());
            contentValues.put("city_code",city.getCityCode());
            contentValues.put("province_id",city.getProvinceId());
            db.insert("City",null,contentValues);
        }
    }
    public List<City> loadCities(int provinceId){
        List<City> cityList = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[]{
        String.valueOf(provinceId)},null,null,null);
        if(cursor.moveToFirst()){
            do {
               City city = new City();
               city.setId(cursor.getInt(cursor.getColumnIndex("id")));
               city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
               city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
               city.setProvinceId(provinceId);
               cityList.add(city);
            }while (cursor.moveToNext());
        }
        return cityList;
    }
    public void saveCounty(County county){
        if(county != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("county_name",county.getCountyName());
            contentValues.put("county_code",county.getCountyCode());
            contentValues.put("city_id",county.getCityId());
            db.insert("County",null,contentValues);
        }
    }
    public List<County> loadCounties(int cityId){
        List<County> countyList = new ArrayList<County>();
        Cursor cursor = db.query("County",null,"city_id = ?",new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
              County county = new County();
              county.setId(cursor.getInt(cursor.getColumnIndex("id")));
              county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
              county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
              county.setCityId(cityId);
              countyList.add(county);

            }while (cursor.moveToNext());
        }
        return countyList;
    }
}
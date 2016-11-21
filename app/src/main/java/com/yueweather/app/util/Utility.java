package com.yueweather.app.util;


import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import android.text.TextUtils;


import com.yueweather.app.MyApplication;
import com.yueweather.app.model.City;
import com.yueweather.app.model.County;
import com.yueweather.app.model.Province;
import com.yueweather.app.model.YueWeatherDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/17 16:39
 * 修改人：lenovo
 * 修改时间：2016/11/17 16:39
 * 修改备注：
 */

public class Utility {
    public synchronized static boolean handleProvincesResponse(YueWeatherDB yueWeatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length>0){
                for (String p: allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceName(array[1]);
                    province.setProvinceCode(array[0]);
                    yueWeatherDB.saveProvince(province);
                }
               return true;
            }
        }
        return false;
    }

    public static  boolean handleCitiesResponse(YueWeatherDB yueWeatherDB,String response, int provinceId){
       if (!TextUtils.isEmpty(response)){
         String[] allCities = response.split(",");
           if (allCities != null && allCities.length>0){
               for (String c: allCities){
                   String[] array = c.split("\\|");
                   City city = new City();
                   city.setCityName(array[1]);
                   city.setCityCode(array[0]);
                   city.setProvinceId(provinceId);
                   yueWeatherDB.saveCity(city);
               }
               return true;
           }
       }
        return false;
    }

    public static boolean handleCountiesResponse(YueWeatherDB yueWeatherDB, String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length>0){
                for(String c:allCounties){
                   String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyName(array[1]);
                    county.setCountyCode(array[0]);
                    county.setCityId(cityId);
                    yueWeatherDB.saveCounty(county);
                }
                return true;
            }

        }
        return false;
    }

    public static void  handleWeatherResponse(String response){
        try {
            LogUtil.d(LogUtil.TAG,"the response is=="+response);
            JSONObject jsonObject = new JSONObject(response);
            JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
            String cityName = weatherInfo.getString("city");
            String weatherCode = weatherInfo.getString("cityid");
            String temp1 = weatherInfo.getString("temp1");
            String temp2 = weatherInfo.getString("temp2");
            String weatherDesp = weatherInfo.getString("weather");
            String publishTime = weatherInfo.getString("ptime");
            saveWeatherInfo(cityName,weatherCode,temp1,temp2,weatherDesp,publishTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void saveWeatherInfo(String cityName, String weatherCode, String temp1, String temp2, String weatherDesp, String publishTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日",Locale.CHINA);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        editor.putBoolean("city_selected",true);
        editor.putString("city_name",cityName);
        editor.putString("weather_code",weatherCode);
        editor.putString("temp1",temp1);
        editor.putString("temp2",temp2);
        editor.putString("weather_desp",weatherDesp);
        editor.putString("publish_time",publishTime);
        editor.putString("current_date",sdf.format(new Date()));
        editor.commit();
    }
}

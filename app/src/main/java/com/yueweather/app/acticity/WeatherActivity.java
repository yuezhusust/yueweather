package com.yueweather.app.acticity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yueweather.app.MyApplication;
import com.yueweather.app.R;
import com.yueweather.app.util.HttpCallbackListener;
import com.yueweather.app.util.HttpUtil;
import com.yueweather.app.util.LogUtil;
import com.yueweather.app.util.Utility;

public class WeatherActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout linearLayout;
    private TextView cityNameText;
    private TextView publishText;
    private TextView weatherDespText;
    private TextView temp1Text;
    private TextView temp2Text;
    private TextView currentDateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);
        linearLayout = (LinearLayout) findViewById(R.id.weather_info_layout);
        cityNameText = (TextView) findViewById(R.id.city_name);
        publishText = (TextView) findViewById(R.id.publish_text);
        weatherDespText = (TextView) findViewById(R.id.weather_desp);
        temp1Text = (TextView) findViewById(R.id.temp1);
        temp2Text = (TextView) findViewById(R.id.temp2);
        currentDateText = (TextView) findViewById(R.id.current_date);
        String countyCode = this.getIntent().getStringExtra("county_code");
        if (!TextUtils.isEmpty(countyCode)){
            publishText.setText("同步中...");
            linearLayout.setVisibility(View.INVISIBLE);
            cityNameText.setVisibility(View.INVISIBLE);
            queryWeatherCode(countyCode);
        }else {
             showWeather();
        }

    }

    private void showWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        cityNameText.setText(prefs.getString("city_name",""));
        temp1Text.setText(prefs.getString("temp1",""));
        temp1Text.setText(prefs.getString("temp2",""));
        publishText.setText("今天"+prefs.getString("publish_time","")+"发布");
        weatherDespText.setText(prefs.getString("weather_desp",""));
        currentDateText.setText(prefs.getString("current_date",""));
        linearLayout.setVisibility(View.VISIBLE);
        cityNameText.setVisibility(View.VISIBLE);
    }

    private void queryWeatherCode(String countyCode) {
        String address = "http://www.weather.com.cn/data/list3/city"+countyCode+".xml";
        queryFromServer(address,"countyCode");
    }

    private void queryFromServer(String address, final String type) {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                if ("countyCode".equals(type)){
                    String[] array = response.split("\\|");
                    String weatherCode = array[1];
                    LogUtil.d(LogUtil.TAG,"the array is"+array[1]);
                    queryWeatherInfo(weatherCode,"weatherCode");
                } else if ("weatherCode".equals(type)) {
                    Utility.handleWeatherResponse(response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeather();
                        }
                    });
                }

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        publishText.setText("同步失败");
                    }
                });
            }
        });
    }

    private void queryWeatherInfo(String weatherCode, String type) {
        String address = "http://www.weather.com.cn/data/cityinfo/"+weatherCode+".html";
        queryFromServer(address,"weatherCode");
    }

    @Override
    public void onClick(View view) {

    }
}

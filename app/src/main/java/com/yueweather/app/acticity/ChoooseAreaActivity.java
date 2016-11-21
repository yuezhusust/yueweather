package com.yueweather.app.acticity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yueweather.app.MyApplication;
import com.yueweather.app.R;
import com.yueweather.app.model.City;
import com.yueweather.app.model.County;
import com.yueweather.app.model.Province;
import com.yueweather.app.model.YueWeatherDB;
import com.yueweather.app.util.HttpCallbackListener;
import com.yueweather.app.util.HttpUtil;
import com.yueweather.app.util.LogUtil;
import com.yueweather.app.util.Utility;

import java.util.ArrayList;
import java.util.List;


public class ChoooseAreaActivity extends BaseActivity {
    public static final  int LEVEL_PROVINCE = 0;
    public static final  int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private ProgressDialog progressDialog;
    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private YueWeatherDB yueWeatherDB;
    private List<String> dataList = new ArrayList<String>();
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    private Province selectedProvince;
    private City selectedCity;
    private int currentLevel;
    private final String tag ="YUEWEATHER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        titleText = (TextView) findViewById(R.id.title_text);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        yueWeatherDB = YueWeatherDB.getInstance(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(i);
                    queryCities();
                }else if(currentLevel == LEVEL_CITY){
                    selectedCity = cityList.get(i);
                    queryCounties();
                }
            }
        });
        queryProvinces();

    }

    @Override
    public void onBackPressed() {
       if (currentLevel == LEVEL_COUNTY){
           queryCities();
       }else if(currentLevel == LEVEL_CITY){
           queryProvinces();
       }else {
           finish();
       }
    }

    private void queryProvinces() {
        provinceList = yueWeatherDB.loadProvinces();
        if (provinceList.size() > 0){
            dataList.clear();
            for(Province p: provinceList){
                dataList.add(p.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        }else {
             queryFromServer(null,"province");
        }
    }
    private void queryCities() {
        cityList = yueWeatherDB.loadCities(selectedProvince.getId());
        if(cityList.size()>0){
            dataList.clear();
            for(City c: cityList){
                dataList.add(c.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText(selectedProvince.getProvinceName());
            currentLevel = LEVEL_CITY;
        }else {
            LogUtil.d(tag,"is empty");
            queryFromServer(selectedProvince.getProvinceCode(),"city");
        }
    }
    private void queryCounties() {
        countyList = yueWeatherDB.loadCounties(selectedCity.getId());
        if (countyList.size() > 0 ){
           dataList.clear();
           for (County c : countyList){
               dataList.add(c.getCountyName());
           }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText(selectedCity.getCityName());
            currentLevel = LEVEL_COUNTY;
        }else {
            queryFromServer(selectedCity.getCityCode(),"county");
        }
    }

    private void queryFromServer(final String code, final String type) {
        String address;
        if(!TextUtils.isEmpty(code)){
             address = "http://www.weather.com.cn/data/list3/city"+code+".xml";
        }else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }

        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                if ("province".equals(type)) {
                    result = Utility.handleProvincesResponse(yueWeatherDB,response);
                }else if("city".equals(type)){
                    result = Utility.handleCitiesResponse(yueWeatherDB,response,selectedProvince.getId());
                }else if("county".equals(type)){
                    LogUtil.d(tag,"go therr");
                    result = Utility.handleCountiesResponse(yueWeatherDB,response,selectedCity.getId());
                    LogUtil.d(tag,"the result is=="+result);
                }
                if (result){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if ("province".equals(type)){
                                queryProvinces();
                            }else if ("city".equals(type)){
                                queryCities();
                            }else if ("county".equals(type)){
                                LogUtil.d(tag,"county");
                                queryCounties();
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         closeProgressDialog();
                         Toast.makeText(MyApplication.getContext(),"加载失败",Toast.LENGTH_LONG).show();
                     }
                 });
            }
        });
    }

    private void closeProgressDialog() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }


}

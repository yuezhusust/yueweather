package com.yueweather.app.model;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/16 15:11
 * 修改人：lenovo
 * 修改时间：2016/11/16 15:11
 * 修改备注：
 */

public class City {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    private int id;
    private String cityName;
    private String cityCode;
    private int provinceId;
}

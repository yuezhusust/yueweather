package com.yueweather.app.model;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/16 15:20
 * 修改人：lenovo
 * 修改时间：2016/11/16 15:20
 * 修改备注：
 */

public class County {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    private int id;
    private String countyName;
    private String countyCode;
    private int    cityId;
}

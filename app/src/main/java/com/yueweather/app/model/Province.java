package com.yueweather.app.model;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/16 14:50
 * 修改人：lenovo
 * 修改时间：2016/11/16 14:50
 * 修改备注：
 */

public class Province {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    private  int id;
    private String provinceName;
    private String provinceCode;
}

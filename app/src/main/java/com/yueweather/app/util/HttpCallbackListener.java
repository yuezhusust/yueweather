package com.yueweather.app.util;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/17 15:36
 * 修改人：lenovo
 * 修改时间：2016/11/17 15:36
 * 修改备注：
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}

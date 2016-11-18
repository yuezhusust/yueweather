package com.yueweather.app.acticity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：YueWeather
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2016/11/18 16:19
 * 修改人：lenovo
 * 修改时间：2016/11/18 16:19
 * 修改备注：
 */

public class ActivityColleter {
    public static List<Activity> activityList = new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        if (activityList != null && !activityList.contains(activity)){
            activityList.add(activity);
        }
    }
    public static void  removeActivity(Activity activity){
        if (activityList != null){
            activityList.remove(activity);
        }
    }
    public static void finishAllActivities(){
        if (activityList != null){
            for (Activity activity : activityList){
                if(!activity.isFinishing()){
                    activity.finish();
                }
            }
        }
    }


}

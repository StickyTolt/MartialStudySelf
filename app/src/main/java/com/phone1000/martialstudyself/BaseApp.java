package com.phone1000.martialstudyself;

import android.app.Activity;
import android.app.Application;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/11/27.
 */
public class BaseApp extends Application {

    private static List<Activity> data ;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        data = new ArrayList<>();
    }

    public static void addActivity(Activity activity){
        data.add(activity);
    }

    public static void deleteActivity(Activity activity){
        data.remove(activity);
    }

    public static void romoveAllActivity(){
        for (int i = 0; i < data.size(); i++) {
            data.remove(i);
        }
    }



}

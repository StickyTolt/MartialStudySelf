package com.phone1000.martialstudyself;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 马金利 on 2016/11/27.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}

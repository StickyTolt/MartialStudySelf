package com.phone1000.martialstudyself.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chen on 2016/11/29.
 */

public class NetWorkUtil {

    public static boolean isConnected(Context context) {
        // 获取连接的管理者
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }


}


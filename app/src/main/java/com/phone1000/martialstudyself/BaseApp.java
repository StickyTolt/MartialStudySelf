package com.phone1000.martialstudyself;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.phone1000.martialstudyself.constants.HttpParams;
import com.phone1000.martialstudyself.utils.NetWorkUtil;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 马金利 on 2016/11/27.
 */
public class BaseApp extends Application {

    private static List<Activity> data;
    public static boolean isLogIn = false;
    private static Context context;
    public static Activity activity;

    public static boolean isFirst = false;

    public static void setIsFirst(boolean isFirst) {
        BaseApp.isFirst = isFirst;
    }

    public static void setActivity(Activity activity) {
        BaseApp.activity = activity;
    }

    public static void setIsLogIn(boolean isLogIn) {
        BaseApp.isLogIn = isLogIn;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        data = new ArrayList<>();
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);

        /*if (user.getString("isLogIn","false").equals("false")) {
            isLogIn=false;
        }else if (user.getString("isLogIn","false").equals("true")){
            isLogIn = true ;
        }*/
        isLogIn = user.getBoolean("islogin", false);

        // Picasso
        Picasso picasso = new Picasso.Builder(this)
                .loggingEnabled(true)
                // RGB565 可以配置全局的图片加载质量
                .defaultBitmapConfig(Bitmap.Config.RGB_565)
                // 调试标记
                //.indicatorsEnabled(true)
                .build();
        Picasso.setSingletonInstance(picasso);

        // 初缓始化OkHttpUtil
        context = this;
        // 实例化请求存
        Cache cache = new Cache(getCacheDir(), 50 * 1024 * 1024);
        // 添加网络拦截器
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // 获取到这次的这个请求
                Request request = chain.request();
                if (!NetWorkUtil.isConnected(BaseApp.this)) {
                    request = request.newBuilder()
                            // 当没有网络的时候，我们将请求设置为强制从缓存中获取
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                // 获取请求的Response
                Response response = chain.proceed(request);
                if (NetWorkUtil.isConnected(BaseApp.this)) {
                    // 获取请求中的缓存规则
                    String cacheControl = request.cacheControl().toString();
                    response = response.newBuilder()
                            // 修改Response结构，让Response支持缓存的形式
                            .removeHeader(HttpParams.CACHE_CONTROL)
                            .removeHeader(HttpParams.PARMA)
                            .addHeader(HttpParams.CACHE_CONTROL, cacheControl)
                            .build();
                } else {
                    // 没有网络的时候，缓存策略设置为直接从缓存中获取，哪怕缓存是过期的
                    response = response.newBuilder()
                            .removeHeader(HttpParams.CACHE_CONTROL)
                            .removeHeader(HttpParams.PARMA)
                            // 配置缓存策略 共有的，仅从缓存中获取，可接受最大过期时间
                            .addHeader(HttpParams.CACHE_CONTROL, "public, only-if-cached, max-stale=" + 2 * 60 * 60)
                            .build();
                }
                return response;
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                // 添加缓存
                .cache(cache)
                // 添加一个自定义的网络拦截器
                .addNetworkInterceptor(cacheInterceptor)

                .build();
        OkHttpUtils.initClient(client);


        String appKey = "5841343604e205645c001525";
        String channelId = "MYAPP";
        MobclickAgent.UMAnalyticsConfig umAnalyticsConfig = new MobclickAgent.UMAnalyticsConfig(this,appKey,channelId,MobclickAgent.EScenarioType.E_UM_NORMAL,true);
        MobclickAgent.startWithConfigure(umAnalyticsConfig);

    }

    public static void addActivity(Activity activity) {
        data.add(activity);
    }

    public static void deleteActivity(Activity activity) {
        data.remove(activity);
    }

    public static void removeAllActivity() {
        for (int i = 0; i < data.size(); i++) {
            data.remove(i);
        }
    }

    public static String getCacheControl() {
        return NetWorkUtil.isConnected(context) ? "max-age=15" : "only-if-cache,max-stale=" + 2 * 60 * 60;
    }

}

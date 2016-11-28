package com.phone1000.martialstudyself.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phone1000.martialstudyself.adapeters.HomeElvAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.model.HomeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 马金利 on 2016/11/28.
 */
public class JsonParseHome {


    public static void HomeElv(final HomeElvAdapter adapter, String url){
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    Type type = new TypeToken<List<HomeModel>>() {
                    }.getType();
                    Gson gson = new Gson();
                    List<HomeModel> data = gson.fromJson(jsonArray.toString(), type);
                    adapter.updateRes(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}

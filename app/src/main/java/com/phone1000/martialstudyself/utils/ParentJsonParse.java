package com.phone1000.martialstudyself.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.phone1000.martialstudyself.adapeters.ParentAdapter;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.model.ParentModel;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 马金利 on 2016/12/1.
 */
public class ParentJsonParse {

    private static final String TAG = ParentJsonParse.class.getSimpleName();

    public static void ParentParse(final ParentAdapter adapter, String url){
        RequestParams params = new RequestParams(url );
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " );
                Gson gson = new Gson();
                ParentModel model = gson.fromJson(result, ParentModel.class);
                List<ParentModel.ListBean> data = model.getList();
                Log.e(TAG, "onSuccess: " + data );
                adapter.updateRes(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });
    }


    public static void ParentParseLoad(final ParentAdapter adapter, String url){
        RequestParams params = new RequestParams(url );
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ParentModel model = gson.fromJson(result, ParentModel.class);
                List<ParentModel.ListBean> data = model.getList();
                adapter.addRes(data);
                adapter.notifyDataSetChanged();
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

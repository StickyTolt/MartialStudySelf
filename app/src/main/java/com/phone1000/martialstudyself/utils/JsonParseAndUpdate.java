package com.phone1000.martialstudyself.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.phone1000.martialstudyself.adapters.CommentAdapter;
import com.phone1000.martialstudyself.adapters.MoreItemAdapter;
import com.phone1000.martialstudyself.model.MoreModel;
import com.phone1000.martialstudyself.models.CommentModel;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by my on 2016/11/28.
 */
public class JsonParseAndUpdate {

    public static final int UPDATE = 99;
    public static final int ADD = 66 ;

    private static final String TAG = JsonParseAndUpdate.class.getSimpleName();

    public static void commentJPAU(final CommentAdapter adapter, String url) {

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: ");
                Gson gson = new Gson();
                CommentModel commentModel = gson.fromJson(result, CommentModel.class);
                Log.e(TAG, "onSuccess: 这里是comment解析的数据" + commentModel.getList().size() + commentModel.getList().get(0).getTopic_title());
                adapter.updateRes(commentModel.getList());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
    }

    public static void commentJPAA(final CommentAdapter adapter, String url) {

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: ");
                Gson gson = new Gson();
                CommentModel commentModel = gson.fromJson(result, CommentModel.class);
                Log.e(TAG, "onSuccess: 这里是comment解析的数据" + commentModel.getList().size() + commentModel.getList().get(0).getTopic_title());
                adapter.addRes(commentModel.getList());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
    }

    //
    public static void moreItemJPAU(final MoreItemAdapter adapter , String url, final int UPorADD) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                MoreModel moreModel = gson.fromJson(result, MoreModel.class);
                switch (UPorADD) {
                    case UPDATE:
                        adapter.updateRes(moreModel.getList());
                        break;
                    case ADD:
                        adapter.addRes(moreModel.getList());
                        break;
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

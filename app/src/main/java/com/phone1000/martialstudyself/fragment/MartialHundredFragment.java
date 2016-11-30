package com.phone1000.martialstudyself.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.MoreCyclopediaAcitivity;
import com.phone1000.martialstudyself.adapters.CyclopediaAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.interfaces.ICyclopediaMore;
import com.phone1000.martialstudyself.models.CyclopediaModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/11/27.
 */
@ContentView(R.layout.martial_hundred_fragment)
public class MartialHundredFragment extends Fragment implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener, ICyclopediaMore {

    public static final String TAG = MartialHundredFragment.class.getSimpleName();
    @ViewInject(R.id.hundred_elv)
    private ExpandableListView mELV;
    private CyclopediaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        adapter = new CyclopediaAdapter(null, getContext());
        mELV.setAdapter(adapter);
        adapter.setiCyclopediaMore(this);

        RequestParams params = new RequestParams(HttpUrl.CYCLOPEDIA_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: ");
                List<CyclopediaModel> data = new ArrayList<CyclopediaModel>();
                Gson gson = new Gson();
                try {
                    JSONArray array = new JSONArray(result);
                    for (int i = 0; i < array.length(); i++) {
                        CyclopediaModel cyclopediaModel = gson.fromJson(array.get(i).toString(), CyclopediaModel.class);
                        data.add(cyclopediaModel);
                    }
                    adapter.updateRes(data);

                    if (adapter.getGroupCount() > 1) {
                        for (int i = 0; i < adapter.getGroupCount(); i++) {
                            mELV.expandGroup(i);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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


        mELV.setOnChildClickListener(this);
        mELV.setOnGroupClickListener(this);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        List<Integer> childCount = adapter.getChildCount();
        List<CyclopediaModel> data = adapter.getData();
        if (groupPosition != 2 && groupPosition != 3 && childCount.get(groupPosition) == childPosition + 1) {
            // 更多的点击跳转
            jumpToMore(groupPosition);
        } else {
            //这里是条目的点击跳转

        }
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }


    private void jumpToMore(int groutPosition) {
        List<CyclopediaModel> data = adapter.getData();
        // url_end
        String link = data.get(groutPosition).getLink();
        String url = HttpUrl.CYCLOPEDIA_MORE + link.substring(link.indexOf("?"), link.length());
        Log.e(TAG, "jumpToMore: 这个就是传说中的网址+++" + url);
        // name
        String desc = data.get(groutPosition).getDesc();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("name", desc);
        Intent intent = new Intent(getActivity(), MoreCyclopediaAcitivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void requestGroupPosition(int groupPosition) {
        jumpToMore(groupPosition);
    }
}

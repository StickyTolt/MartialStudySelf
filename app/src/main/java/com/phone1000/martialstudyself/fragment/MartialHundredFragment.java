package com.phone1000.martialstudyself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phone1000.martialstudyself.R;
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
        adapter = new CyclopediaAdapter(null,getContext());
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
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

        return true;
    }

    @Override
    public void requestInfoId(String name) {
        Toast.makeText(getContext(), "这样子是正确的", Toast.LENGTH_SHORT).show();
    }
}

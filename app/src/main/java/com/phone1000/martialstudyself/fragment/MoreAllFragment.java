package com.phone1000.martialstudyself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapters.MoreItemAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.interfaces.INotifyAddRes;
import com.phone1000.martialstudyself.utils.JsonParseAndUpdate;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by my on 2016/11/30.
 */
@ContentView(R.layout.fragment_more)
public class MoreAllFragment extends Fragment implements INotifyAddRes {

    private static final String TAG = MoreAllFragment.class.getSimpleName();

    @ViewInject(R.id.fragment_more_lv)
    private ListView mLV;

    private int page = 1;
    private MoreItemAdapter adapter;


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

        adapter = new MoreItemAdapter(null, getContext());
        mLV.setAdapter(adapter);
        adapter.setiNotifyAddRes(this);
        JsonParseAndUpdate.moreItemJPAU(adapter, HttpUrl.MORE_ALL + page, JsonParseAndUpdate.UPDATE);
    }

    @Override
    public void addResForI() {
        page++;
        JsonParseAndUpdate.moreItemJPAU(adapter, HttpUrl.MORE_ALL + page, JsonParseAndUpdate.ADD);
    }
}

package com.phone1000.martialstudyself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phone1000.martialstudyself.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by my on 2016/11/30.
 */
@ContentView(R.layout.fragment_more)
public class MoreMaixueFragment extends Fragment {
    private static final String TAG = MoreMaixueFragment.class.getSimpleName();
    private RecyclerView mGV;
    @ViewInject(R.id.fragment_more_lv)
    private ListView mLV;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

    }
}

package com.phone1000.martialstudyself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapters.MoreAdapter;
import com.phone1000.martialstudyself.adapters.MoreItemAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.models.WushubaikeModel;
import com.phone1000.martialstudyself.utils.JsonParseAndUpdate;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by my on 2016/11/30.
 */
@ContentView(R.layout.fragment_more)
public class MoreMingjiaFragment extends Fragment {
    private static final String TAG = MoreMingjiaFragment.class.getSimpleName();
    @ViewInject(R.id.fragment_more_lv)
    private ListView mLV;



    private MoreAdapter gvAdapter;
    private MoreItemAdapter lvAdapter;

    private int page = 1 ;


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
        List<String> data = WushubaikeModel.getWushubaike().武术名家;

        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_more, null);
        GridView gridView = (GridView) header.findViewById(R.id.header_more_gv);
        gridView.setAdapter(new MoreAdapter(data,getContext()));
        mLV.addHeaderView(header);

        lvAdapter = new MoreItemAdapter(null,getContext());
        mLV.setAdapter(lvAdapter);
        JsonParseAndUpdate.moreItemJPAU(lvAdapter, HttpUrl.MORE_MINGJIA+page , JsonParseAndUpdate.UPDATE);

    }
}

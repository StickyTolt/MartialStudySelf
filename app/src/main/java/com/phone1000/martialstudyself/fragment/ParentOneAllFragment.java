package com.phone1000.martialstudyself.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.HomePositionActivity;
import com.phone1000.martialstudyself.adapeters.ParentAdapter;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.interfaces.LoadingIsShown;
import com.phone1000.martialstudyself.interfaces.ParentItemClickListener;
import com.phone1000.martialstudyself.model.ParentModel;
import com.phone1000.martialstudyself.utils.ParentJsonParse;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by 马金利 on 2016/11/30.
 */
@ContentView(R.layout.parent_one_all_fragment)
public class ParentOneAllFragment extends Fragment implements LoadingIsShown, AdapterView.OnItemClickListener {

    private static final String TAG = ParentOneAllFragment.class.getSimpleName();
    @ViewInject(R.id.parent_all_listview)
    private ListView mLisView;
    private ParentAdapter adapter;
    private int page = 1;


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
        adapter = new ParentAdapter(getContext(),null);
        mLisView.setAdapter(adapter);
        adapter.setIsShown(this);
        mLisView.setOnItemClickListener(this);
        ParentJsonParse.ParentParse(adapter,MyUrl.PARENT_ONE_ALL_URL + page);
    }

    @Override
    public void isShownLoad() {
        page++;
        ParentJsonParse.ParentParseLoad(adapter,MyUrl.PARENT_ONE_ALL_URL + page);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int info_id = adapter.getItem(position).getInfo_id();
        Intent intent = new Intent(getContext(), HomePositionActivity.class);
        intent.putExtra("id",info_id + "");
        startActivity(intent);
    }


}

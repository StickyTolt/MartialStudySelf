package com.phone1000.martialstudyself.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.HomePositionActivity;
import com.phone1000.martialstudyself.adapeters.ParentAdapter;
import com.phone1000.martialstudyself.adapeters.ParentGridViewAdapter;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.interfaces.GridViewItemClick;
import com.phone1000.martialstudyself.interfaces.LoadingIsShown;
import com.phone1000.martialstudyself.model.GridViewModel;
import com.phone1000.martialstudyself.utils.ParentJsonParse;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by 马金利 on 2016/11/30.
 */
@ContentView(R.layout.parent_one_happ_fragment)
public class ParentOneHappyFragment extends Fragment implements LoadingIsShown, GridViewItemClick, AdapterView.OnItemClickListener {

    @ViewInject(R.id.parent_happy_listview)
    private ListView mLisView;
    private ParentAdapter adapter;
    private int page = 1;

    private GridView mGridView;
    private ParentGridViewAdapter gvAdapter;

    private int name;
    private List<String> context;

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
        GridViewModel model = new GridViewModel();
        context = model.context;
        View header = LayoutInflater.from(getContext()).inflate(R.layout.parent_header, null);
        mGridView = ((GridView) header.findViewById(R.id.parent_header_gv));
        gvAdapter = new ParentGridViewAdapter(getContext(), context);
        gvAdapter.setItemClick(this);
        mGridView.setAdapter(gvAdapter);
        adapter = new ParentAdapter(getContext(),null);
        mLisView.addHeaderView(header);
        mLisView.setAdapter(adapter);
        adapter.setIsShown(this);
        mLisView.setOnItemClickListener(this);
        ParentJsonParse.ParentParse(adapter, MyUrl.PARENT_ONE_HAPPY_URL + MyUrl.PARENT_ONE_CONTEXT_END + page);
    }

    @Override
    public void isShownLoad() {
        page++;
        if (name == 0) {
            ParentJsonParse.ParentParseLoad(adapter, MyUrl.PARENT_ONE_HAPPY_URL + MyUrl.PARENT_ONE_CONTEXT_END + page);
        } else {
            ParentJsonParse.ParentParseLoad(adapter, MyUrl.PARENT_ONE_HAPPY_URL + "tag=" + context.get(name) + "&"+ MyUrl.PARENT_ONE_CONTEXT_END  + page);
        }
    }

    @Override
    public void itemClickListener(int tag) {
        name = tag;
        ObjectAnimator oa = ObjectAnimator.ofFloat(mLisView, "alpha", 0.0f, 0.3f, 0.6f, 0.9f, 1f);
        oa.setDuration(2000);
        oa.start();
        if (name == 0) {
            String url = MyUrl.PARENT_ONE_HAPPY_URL + MyUrl.PARENT_ONE_CONTEXT_END + page;
            ParentJsonParse.ParentParse(adapter, url);
        } else {
            String url = MyUrl.PARENT_ONE_HAPPY_URL + "tag=" + context.get(name) + "&"+ MyUrl.PARENT_ONE_CONTEXT_END  + page;
            ParentJsonParse.ParentParse(adapter, url);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int info_id = adapter.getItem(position).getInfo_id();
        Intent intent = new Intent(getContext(), HomePositionActivity.class);
        intent.putExtra("id",info_id + "");
        startActivity(intent);
    }
}

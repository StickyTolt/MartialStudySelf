package com.phone1000.martialstudyself.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * Created by 马金利 on 2016/12/1.
 */
@ContentView(R.layout.parent_one_all_fragment)
public class ParentTwoWaiGuoFragment extends Fragment implements LoadingIsShown, GridViewItemClick, AdapterView.OnItemClickListener {

    @ViewInject(R.id.parent_all_listview)
    private ListView mLisView;
    private ParentAdapter adapter;
    private int page = 1;

    private GridView mGridView;
    private ParentGridViewAdapter gvAdapter;
    private int name;
    private List<String> book;
    private List<String> tag_url;


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
        book = model.book;
        tag_url = model.url;
        View header = LayoutInflater.from(getContext()).inflate(R.layout.parent_header, null);
        mGridView = ((GridView) header.findViewById(R.id.parent_header_gv));
        gvAdapter = new ParentGridViewAdapter(getContext(), book);
        gvAdapter.setItemClick(this);
        mGridView.setAdapter(gvAdapter);
        mLisView.addHeaderView(header);
        adapter = new ParentAdapter(getContext(),null);
        mLisView.setAdapter(adapter);
        adapter.setIsShown(this);
        mLisView.setOnItemClickListener(this);
        ParentJsonParse.ParentParse(adapter, MyUrl.PARENT_TWO_WAIGUO_URL + MyUrl.PARENT_ONE_CONTEXT_END+ page);
    }

    @Override
    public void isShownLoad() {
        page++;
        if (name == 0) {
            ParentJsonParse.ParentParseLoad(adapter, MyUrl.PARENT_TWO_WAIGUO_URL + MyUrl.PARENT_ONE_CONTEXT_END + page);
        } else {
            ParentJsonParse.ParentParseLoad(adapter, MyUrl.PARENT_TWO_WAIGUO_URL + tag_url.get(name-1) + "&"+ MyUrl.PARENT_ONE_CONTEXT_END  + page);
        }
    }

    @Override
    public void itemClickListener(int tag) {
        name = tag;
        ObjectAnimator oa = ObjectAnimator.ofFloat(mLisView, "alpha", 0.0f, 0.3f, 0.6f, 0.9f, 1f);
        oa.setDuration(1000);
        oa.start();
        if (name == 0) {
            String url = MyUrl.PARENT_TWO_WAIGUO_URL + MyUrl.PARENT_ONE_CONTEXT_END + page;
            ParentJsonParse.ParentParse(adapter, url);
        } else {
            String url = MyUrl.PARENT_TWO_WAIGUO_URL +  tag_url.get(name-1)  + "&"+ MyUrl.PARENT_ONE_CONTEXT_END  + page;
            ParentJsonParse.ParentParse(adapter, url);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int info_id = adapter.getItem(position).getInfo_id();
        Intent intent = new Intent(getContext(), HomePositionActivity.class);
        intent.putExtra("id",info_id);
        startActivity(intent);
    }
}

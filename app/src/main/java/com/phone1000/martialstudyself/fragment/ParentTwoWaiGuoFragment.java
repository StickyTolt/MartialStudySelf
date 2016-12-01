package com.phone1000.martialstudyself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapeters.ParentAdapter;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.interfaces.LoadingIsShown;
import com.phone1000.martialstudyself.utils.ParentJsonParse;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 马金利 on 2016/12/1.
 */
@ContentView(R.layout.parent_one_all_fragment)
public class ParentTwoWaiGuoFragment extends Fragment implements LoadingIsShown {

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
        ParentJsonParse.ParentParse(adapter, MyUrl.PARENT_TWO_WAIGUO_URL + page);
    }

    @Override
    public void isShownLoad() {
        page++;
        ParentJsonParse.ParentParseLoad(adapter,MyUrl.PARENT_TWO_WAIGUO_URL + page);
    }
}

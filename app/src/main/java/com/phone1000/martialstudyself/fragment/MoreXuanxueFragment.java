package com.phone1000.martialstudyself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapters.MoreAdapter;
import com.phone1000.martialstudyself.adapters.MoreItemAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.interfaces.IItemMoreItem;
import com.phone1000.martialstudyself.interfaces.INotifyAddRes;
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
public class MoreXuanxueFragment extends Fragment implements INotifyAddRes, IItemMoreItem {
    private static final String TAG = MoreXuanxueFragment.class.getSimpleName();
    private RecyclerView mGV;
    @ViewInject(R.id.fragment_more_lv)
    private ListView mLV;

    private MoreItemAdapter adapter;

    private int page = 1 ;
    private List<String> data;


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
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_more_maixue, null);
        GridView gridView = (GridView) header.findViewById(R.id.header_more_gv);
        data = WushubaikeModel.getWushubaike().武林玄学;
        gridView.setAdapter(new MoreAdapter(data,getContext(),this));
        mLV.addHeaderView(header);

        adapter = new MoreItemAdapter(null,getContext());
        adapter.setiNotifyAddRes(this);
        mLV.setAdapter(adapter);
        JsonParseAndUpdate.moreItemJPAU(adapter, HttpUrl.MORE_XUANXUE+page,JsonParseAndUpdate.UPDATE);
    }

    @Override
    public void addResForI() {
        page++ ;
        JsonParseAndUpdate.moreItemJPAU(adapter,HttpUrl.MORE_XUANXUE +page,JsonParseAndUpdate.ADD);
    }

    @Override
    public void updateLV(int position) {
        String itemName = data.get(position);
        Toast.makeText(getContext(), "点击了+++"+ itemName, Toast.LENGTH_SHORT).show();
    }
}

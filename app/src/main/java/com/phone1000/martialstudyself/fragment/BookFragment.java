package com.phone1000.martialstudyself.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.martialstudyself.BaseApp;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.GongFaMiJiMoreActivity;
import com.phone1000.martialstudyself.adapters.BookContentAdapter;
import com.phone1000.martialstudyself.adapters.BookTopPagerAdapter;
import com.phone1000.martialstudyself.adapters.BookTopZeroFragmentPagerAdapter;
import com.phone1000.martialstudyself.constants.HttpParams;
import com.phone1000.martialstudyself.constants.HttpUrlSecond;
import com.phone1000.martialstudyself.model.LatestGongFa;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 马金利 on 2016/11/27.
 */
@ContentView(R.layout.book_fragment)
public class BookFragment extends Fragment implements BookTopPagerAdapter.SendViewPagerListener, View.OnClickListener {


    public static final String TAG = BookFragment.class.getSimpleName();


    @ViewInject(R.id.book_fragment_latest)
    private ListView mFragmentLatest;

    private final int WU_SHU_MEN_PAI = 0;
    private List<Fragment> fragments0;
    private final int WU_SHU_QI_XIE = 1;
    private List<Fragment> fragments1;
    private final int WAI_GUO_GONG_FU = 2;
    private List<Fragment> fragments2;

    private String[] dataMenPai0 = {"少林", "武当", "峨眉", "太极拳", "八卦掌", "形意拳", "心意拳", "意拳&大成拳", "八极拳", "长拳"};
    private String[] dataMenPai1 = {"南拳", "咏春拳", "通背拳", "劈挂拳", "三皇炮锤", "戳脚", "翻子拳", "查拳", "地躺拳", "象形拳"};
    private String[] dataMenPai2 = {"气功", "散打&搏击&格斗", "擒拿", "防身自卫", "摔跤", "其他功夫"};
    private String[] dataQiXie = {"刀术", "枪术", "剑术", "棍术", "斧法", "鞭法", "棒法", "其他器械"};
    private String[] dataWaiGuo0 = {"截拳道", "跆拳道", "泰拳", "拳击", "巴西柔术", "菲律宾短棍", "柔道", "空手道", "合气道", "剑道"};
    private String[] dataWaiGuo1 = {"忍术", "桑搏", "卡柔肯拳"};
    private List<String[]> data0 = new ArrayList<>();
    private List<String[]> data1 = new ArrayList<>();
    private List<String[]> data2 = new ArrayList<>();
    private BookContentAdapter adapter;
    private ViewPager mTopBody0;
    private ViewPager mTopBody1;
    private ViewPager mTopBody2;
    private TextView mTextMore;
    private TextView mTextFooter;


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
        adapter = new BookContentAdapter(getContext());
        mFragmentLatest.setAdapter(adapter);
        OkHttpUtils.get()
                .url(HttpUrlSecond.ZUIXINGONGFA)
                .addHeader(HttpParams.CACHE_CONTROL, BaseApp.getCacheControl())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //  Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        LatestGongFa gongfulist = gson.fromJson(response, LatestGongFa.class);
                        List<LatestGongFa.ListBean> data = gongfulist.getList();
                        adapter.updateRes(data);
                    }
                });
        //=============header=========================================
        View header = LayoutInflater.from(getContext()).inflate(R.layout.book_latest_header, null);

        mTextMore = ((TextView) header.findViewById(R.id.book_latest_top_more));
        mTextMore.setOnClickListener(this);


        mTopBody0 = ((ViewPager) header.findViewById(R.id.book_top_body0));
        data0.add(dataMenPai0);
        data0.add(dataMenPai1);
        data0.add(dataMenPai2);
        initViewPagerMenPai(mTopBody0);

        mTopBody1 = (ViewPager) header.findViewById(R.id.book_top_body1);
        data1.add(dataQiXie);
        initViewPagerQiXie(mTopBody1);

        mTopBody2 = (ViewPager) header.findViewById(R.id.book_top_body2);
        data2.add(dataWaiGuo0);
        data2.add(dataWaiGuo1);
        initViewPagerWaiGuo(mTopBody2);

        // mFragemntTop = (ListView) header.findViewById(R.id.book_fragment_top);
        //   BookTopPagerAdapter topAdapter = new BookTopPagerAdapter(getContext(), this);
        //   mFragemntTop.setAdapter(topAdapter);
        mFragmentLatest.addHeaderView(header);

        View footer = LayoutInflater.from(getContext()).inflate(R.layout.book_latest_footer, null);
        mTextFooter = ((TextView) footer.findViewById(R.id.footer_more_latest));
        mTextFooter.setOnClickListener(this);
        mFragmentLatest.addFooterView(footer);

    }

    @Override
    public void sendViewPager(RecyclerView pager) {
        GridLayoutManager layout = new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false);
        pager.setLayoutManager(layout);
        int position = (int) pager.getTag();
        switch (position) {
            case WU_SHU_MEN_PAI:
                //   pager.setAdapter(new BookTopItemAdapter(dataMenPai0, getContext()));
                // initViewPagerMenPai(pager);
                break;
            case WU_SHU_QI_XIE:
                //   pager.setAdapter(new BookTopItemAdapter(dataMenPai0, getContext()));
                // initViewPagerQiXie(pager);
                break;
            case WAI_GUO_GONG_FU:
                //  pager.setAdapter(new BookTopItemAdapter(dataMenPai0, getContext()));
                // initViewPagerWaiGuo(pager);
                break;
        }
    }

    private void initViewPagerWaiGuo(ViewPager pager) {
        fragments2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Fragment fragment = new BookTopPagerItemFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArray("title" + i, data2.get(i));
            fragment.setArguments(bundle);
            fragments2.add(fragment);
        }
        //  Log.e(TAG, "initViewPagerWaiGuo: " + fragments2.size() + fragments2);
        pager.setAdapter(new BookTopZeroFragmentPagerAdapter(getFragmentManager(), fragments2));
    }

    private void initViewPagerQiXie(ViewPager pager) {
        fragments1 = new ArrayList<>();
        Fragment fragment = new BookTopPagerItemFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("title0", data1.get(0));
        fragment.setArguments(bundle);
        fragments1.add(fragment);
        // Log.e(TAG, "initViewPagerQiXie: " + fragments1.size() + fragments1);
        pager.setAdapter(new BookTopZeroFragmentPagerAdapter(getFragmentManager(), fragments1));
    }

    private void initViewPagerMenPai(ViewPager pager) {
        fragments0 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Fragment fragment = new BookTopPagerItemFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArray("title" + i, data0.get(i));
            // bundle.putString("title", i + "");
            fragment.setArguments(bundle);
            fragments0.add(fragment);
        }
        //  Log.e(TAG, "initViewPagerMenPai: " + fragments0.size() + fragments0);
        pager.setAdapter(new BookTopZeroFragmentPagerAdapter(getFragmentManager(), fragments0));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), GongFaMiJiMoreActivity.class);
        switch (v.getId()) {
            case R.id.book_latest_top_more:
                //Intent intent = new Intent(getContext(), GongFaMiJiMoreActivity.class);
                startActivity(intent);
                break;
            case R.id.footer_more_latest:
                startActivity(intent);
                break;
        }
    }
}

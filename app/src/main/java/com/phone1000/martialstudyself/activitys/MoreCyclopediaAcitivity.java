package com.phone1000.martialstudyself.activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapters.MoreCyclopediaAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.fragment.MoreAllFragment;
import com.phone1000.martialstudyself.fragment.MoreJieziFragment;
import com.phone1000.martialstudyself.fragment.MoreMaixueFragment;
import com.phone1000.martialstudyself.fragment.MoreMenpaiFragment;
import com.phone1000.martialstudyself.fragment.MoreMingjiaFragment;
import com.phone1000.martialstudyself.fragment.MoreQikuFragment;
import com.phone1000.martialstudyself.fragment.MoreXuanxueFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_more_cyclopedia_acitivity)
public class MoreCyclopediaAcitivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final String TAG = MoreCyclopediaAcitivity.class.getSimpleName();
    @ViewInject(R.id.more_cyclopedia_viewpager)
    private ViewPager mViewPager;
    @ViewInject(R.id.more_cyclopedia_back)
    private ImageView mBack;
    @ViewInject(R.id.more_cyclopedia_tablayout)
    private TabLayout mTablayout;
    private MoreCyclopediaAdapter adapter;
    private List<Fragment> data;
    private List<String> urlList ;
    private int anInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        setData();
        adapter = new MoreCyclopediaAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(adapter);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTablayout.setupWithViewPager(mViewPager);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");

        urlList = new ArrayList<>();
        urlList.add(HttpUrl.MORE_ALL);
        urlList.add(HttpUrl.MORE_MENPAI);
        urlList.add(HttpUrl.MORE_MAIXUE);
        urlList.add(HttpUrl.MORE_MINGJIA);
        urlList.add(HttpUrl.MORE_QIKU);
        urlList.add(HttpUrl.MORE_JIEZI);
        urlList.add(HttpUrl.MORE_XUANXUE);

        for (int i = 0; i < data.size(); i++) {
            if (adapter.getPageTitle(i).equals(name)) {
                mViewPager.setCurrentItem(i);
                anInt = i;
            }
        }

        mViewPager.addOnPageChangeListener(this);

        mBack.setOnClickListener(this);
    }

    private void setData() {
        data = new ArrayList<>();
        data.add(new MoreAllFragment());
        data.add(new MoreMenpaiFragment());
        data.add(new MoreMaixueFragment());
        data.add(new MoreMingjiaFragment());
        data.add(new MoreQikuFragment());
        data.add(new MoreJieziFragment());
        data.add(new MoreXuanxueFragment());
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String url = urlList.get(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

package com.phone1000.martialstudyself.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapeters.ParentOneVPAdapter;
import com.phone1000.martialstudyself.fragment.ParentOneAllFragment;
import com.phone1000.martialstudyself.fragment.ParentOneContextFragment;
import com.phone1000.martialstudyself.fragment.ParentOneHappyFragment;
import com.phone1000.martialstudyself.fragment.ParentOneNewsFragment;
import com.phone1000.martialstudyself.fragment.ParentOnePictureFragment;
import com.phone1000.martialstudyself.fragment.ParentOneVideoFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_parent_one)
public class ParentOneActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.parent_one_tab)
    private TabLayout mTab;
    @ViewInject(R.id.parent_one_viewpager)
    private ViewPager mViewPager;
    private ParentOneVPAdapter adapter;

    @ViewInject(R.id.parent_one_back)
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_parent_one);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        adapter = new ParentOneVPAdapter(getSupportFragmentManager(),getData());
        mViewPager.setAdapter(adapter);
        mTab.setupWithViewPager(mViewPager);
        mBack.setOnClickListener(this);
    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        data.add(new ParentOneAllFragment());
        data.add(new ParentOneNewsFragment());
        data.add(new ParentOneHappyFragment());
        data.add(new ParentOneContextFragment());
        data.add(new ParentOnePictureFragment());
        data.add(new ParentOneVideoFragment());
        return data;
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}

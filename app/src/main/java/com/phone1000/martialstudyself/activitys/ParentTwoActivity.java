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
import com.phone1000.martialstudyself.adapeters.ParentTwoVPAdapter;
import com.phone1000.martialstudyself.bases.BaseActivity;
import com.phone1000.martialstudyself.fragment.ParentTwoAllFragment;
import com.phone1000.martialstudyself.fragment.ParentTwoMenPaiFragment;
import com.phone1000.martialstudyself.fragment.ParentTwoQiXieFragment;
import com.phone1000.martialstudyself.fragment.ParentTwoWaiGuoFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_parent_two)
public class ParentTwoActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.parent_two_tab)
    private TabLayout mTab;
    @ViewInject(R.id.parent_two_viewpager)
    private ViewPager mViewPager;

    private ParentTwoVPAdapter adapter;

    @ViewInject(R.id.parent_two_back)
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_parent_two);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        adapter = new ParentTwoVPAdapter(getSupportFragmentManager(),getData());
        mViewPager.setAdapter(adapter);
        mTab.setupWithViewPager(mViewPager);
        mBack.setOnClickListener(this);
    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        data.add(new ParentTwoAllFragment());
        data.add(new ParentTwoMenPaiFragment());
        data.add(new ParentTwoQiXieFragment());
        data.add(new ParentTwoWaiGuoFragment());
        return data;
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}

package com.phone1000.martialstudyself.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.phone1000.martialstudyself.BaseApp;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.StartActivity;
import com.phone1000.martialstudyself.adapeters.FirstStartAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_first_start)
public class FirstStartActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    @ViewInject(R.id.first_start_vp)
    private ViewPager mVP;
    private List<ImageView> data;
    @ViewInject(R.id.first_start_btn)
    private Button btn;
    private FirstStartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        x.view().inject(this);
        initView();
        BaseApp.setIsFirst(true);
    }

    private void initView() {
        setData();
        adapter = new FirstStartAdapter(data);
        mVP.setAdapter(adapter);
        mVP.addOnPageChangeListener(this);
        btn.setOnClickListener(this);
    }

    private void setData() {
        data = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        Glide.with(this).load(R.mipmap.kungfu1).into(imageView);
        ImageView imageView1 = new ImageView(this);
        Glide.with(this).load(R.mipmap.kungfu2).into(imageView1);
        ImageView imageView2 = new ImageView(this);
        Glide.with(this).load(R.mipmap.kungfu4).into(imageView2);
        ImageView imageView3 = new ImageView(this);
        Glide.with(this).load(R.mipmap.kungfu6).into(imageView3);
        data.add(imageView);
        data.add(imageView1);
        data.add(imageView2);
        data.add(imageView3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position==3) {
            btn.setVisibility(View.VISIBLE);
        }else {
            btn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            deleteDatabase("start");
            BaseApp.activity.finish();
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }
}

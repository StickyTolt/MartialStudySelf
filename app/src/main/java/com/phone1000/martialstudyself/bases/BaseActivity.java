package com.phone1000.martialstudyself.bases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.phone1000.martialstudyself.BaseApp;
import com.phone1000.martialstudyself.R;
import com.umeng.analytics.MobclickAgent;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        BaseApp.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApp.deleteActivity(this);
    }
}

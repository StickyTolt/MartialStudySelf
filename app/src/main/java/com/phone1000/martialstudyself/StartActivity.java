package com.phone1000.martialstudyself;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.phone1000.martialstudyself.bases.BaseActivity;

public class StartActivity extends BaseActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x200) {
//                Intent intent = new Intent(StartActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mHandler.sendEmptyMessageDelayed(0x200,2000);
    }
}

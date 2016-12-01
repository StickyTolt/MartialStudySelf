package com.phone1000.martialstudyself.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phone1000.martialstudyself.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;
@ContentView(R.layout.activity_first_start)
public class FirstStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}

package com.phone1000.martialstudyself.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.constants.MyUrl;

public class MiJiWebActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MiJiWebActivity.class.getSimpleName();
    private WebView mWebView;
    private TextView mText;
    private TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_ji_web);
        initView();
    }

    private void initView() {
        Intent intent = this.getIntent();
        int title = intent.getIntExtra("id", 0);
        int count = intent.getIntExtra("count", 0);
        //    Log.e(TAG, "initView: " + title);

        mBack = (TextView) findViewById(R.id.miji_web_back);
        mBack.setOnClickListener(this);

        mWebView = (WebView) findViewById(R.id.miji_web_web);
        WebViewClient client = new WebViewClient();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(client);
        mWebView.loadUrl(MyUrl.HOME_SECOND_BODY_URL + title);

        mText = (TextView) findViewById(R.id.miji_web_comments);
        mText.setText(count + "");
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}

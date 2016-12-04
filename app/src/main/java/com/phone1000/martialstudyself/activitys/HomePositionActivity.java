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
import com.phone1000.martialstudyself.bases.BaseActivity;
import com.phone1000.martialstudyself.constants.MyUrl;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_home_position)
public class HomePositionActivity extends BaseActivity {

    private static final String TAG = HomePositionActivity.class.getSimpleName();
    @ViewInject(R.id.home_position_title)
    private TextView title;
    @ViewInject(R.id.home_position_time)
    private TextView time;
    @ViewInject(R.id.home_position_author)
    private TextView author;
    private String id;
    @ViewInject(R.id.home_position_web)
    private WebView mWeb;
    @ViewInject(R.id.home_position_web_content)
    private WebView mWebContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_position);
        x.view().inject(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        WebViewClient client = new WebViewClient();
        mWeb.setWebViewClient(client);
        mWeb.loadUrl(MyUrl.HOME_SECOND_BODY_URL + id);
        mWebContent.loadUrl(MyUrl.HOME_COMMENT_START + id + MyUrl.HOME_COMMENT_END);

        setupView();
    }

    private void setupView() {
        RequestParams params = new RequestParams(MyUrl.HOME_POSITION_URL + id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    time.setText(jsonObject.getString("info_last_edit_time"));
                    title.setText(jsonObject.getString("info_title"));
                    author.setText(jsonObject.getString("info_author"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void backClick(View view) {
        this.finish();
    }
}

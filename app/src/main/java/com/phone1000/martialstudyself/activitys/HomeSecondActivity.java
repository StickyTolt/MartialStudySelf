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

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_home_second)
public class HomeSecondActivity extends AppCompatActivity {
    private static final String TAG = HomeSecondActivity.class.getSimpleName();
    @ViewInject(R.id.home_second_image)
    private ImageView image;
    @ViewInject(R.id.home_second_name)
    private TextView name;
    @ViewInject(R.id.home_second_time)
    private TextView time;
    @ViewInject(R.id.home_second_title)
    private TextView title;
    @ViewInject(R.id.home_second_context)
    private TextView context;
    private String id;
    private ImageOptions options;
    @ViewInject(R.id.home_second_web)
    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_second);
        x.view().inject(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        WebViewClient client = new WebViewClient();
        mWeb.setWebViewClient(client);
        mWeb.loadUrl(MyUrl.HOME_SECOND_START + id + MyUrl.HOME_SECOND_END);
        options= new ImageOptions.Builder().setCircular(true).build();
        setupView();

    }

    private void setupView() {
        RequestParams params = new RequestParams(MyUrl.HOME_SECOND_URL + id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    name.setText(jsonObject.getString("topic_add_user"));
                    time.setText(jsonObject.getString("topic_add_time"));
                    title.setText(jsonObject.getString("topic_title"));
                    context.setText(jsonObject.getString("reply_content"));
                    x.image().bind(image,jsonObject.getString("user_headimg"),options);
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

    public void backSecondClick(View view) {
        this.finish();
    }
}

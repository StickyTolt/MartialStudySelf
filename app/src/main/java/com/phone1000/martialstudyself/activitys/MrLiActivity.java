package com.phone1000.martialstudyself.activitys;

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

@ContentView(R.layout.activity_mr_li)
public class MrLiActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MrLiActivity.class.getSimpleName();
    @ViewInject(R.id.view_four_back)
    private ImageView mBack;
    @ViewInject(R.id.view_four_title)
    private TextView title;
    @ViewInject(R.id.view_four_time)
    private TextView time;
    @ViewInject(R.id.view_four_web)
    private WebView mWeb;
    @ViewInject(R.id.view_four_web_content)
    private WebView mWebContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mr_li);
        x.view().inject(this);
        initView();
        setupView();
    }

    private void setupView() {
        RequestParams params = new RequestParams(MyUrl.VIEWPAGER_FOUR_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    title.setText(jsonObject.getString("info_title"));
                    time.setText(jsonObject.getString("info_last_edit_time"));
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

    private void initView() {
        mBack.setOnClickListener(this);
        WebViewClient client = new WebViewClient();
        mWeb.setWebViewClient(client);
        mWebContent.setWebViewClient(client);
        mWeb.loadUrl(MyUrl.VIEWPAGER_WEB_URL);
        mWebContent.loadUrl(MyUrl.VIEWPAGER_WEB_COUTENT_URL);
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}

package com.phone1000.martialstudyself.activitys;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.bases.BaseActivity;
import com.phone1000.martialstudyself.constants.HttpUrl;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_item)
public class ItemActivity extends BaseActivity {
    private static final String TAG = ItemActivity.class.getSimpleName();
    @ViewInject(R.id.activity_item_image)
    private ImageView image ;
    @ViewInject(R.id.activity_item_name)
    private TextView name ;
    @ViewInject(R.id.activity_item_time)
    private TextView time ;
    @ViewInject(R.id.activity_item_title)
    private TextView title ;
    @ViewInject(R.id.activity_item_text)
    private TextView text ;
    @ViewInject(R.id.activity_item_webview)
    private WebView mWebView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setupView();
    }



    private void setupView() {
        String topicId = getIntent().getStringExtra("topicId");
        String url = HttpUrl.COMMENT_ITEM + topicId;

        String webUrl = HttpUrl.COMMENT_COMMENT_START + topicId + HttpUrl.COMMENT_COMMENT_END ;
        Log.e(TAG, "setupView:  我这样走私能看到么"  + webUrl );
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl(webUrl);

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    String nameStr = object.getString("topic_add_user");
                    String timeStr = object.getString("topic_add_time");
                    String titleStr = object.getString("topic_title");
                    String reply_content = object.getString("reply_content");
                    String user_headimg = object.getString("user_headimg");
                    int count = object.getInt("topic_reply_count");
                    Log.e(TAG, "onSuccess: " );
                    name.setText(nameStr);
                    time.setText(timeStr);
                    title.setText(titleStr);
                    text.setText(reply_content);
                    ImageOptions options = new ImageOptions.Builder()
                            .setCircular(true)
                            .build();
                    x.image().bind(image,user_headimg, options);

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
}

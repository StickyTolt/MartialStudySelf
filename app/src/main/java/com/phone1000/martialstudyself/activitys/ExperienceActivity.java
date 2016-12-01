package com.phone1000.martialstudyself.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapeters.ViewPagerAdapter;
import com.phone1000.martialstudyself.bases.BaseActivity;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.model.ViewPagerModel;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_experience)
public class ExperienceActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ExperienceActivity.class.getSimpleName();
    @ViewInject(R.id.view_pager_listview)
    private ListView mListView;
    @ViewInject(R.id.view_pager_back)
    private ImageView mBack;

    private TextView mDes;
    private TextView mTime;
    private TextView mTitle;
    private ImageView mImage;
    private ViewPagerAdapter adapter;
    private WebView mWeb;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_experience);
        x.view().inject(this);
        initView();
        setupView();
    }

    private void setupView() {
        RequestParams params = new RequestParams(MyUrl.VIEWPAGER_URL + id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " );
                Gson gson = new Gson();
                ViewPagerModel viewPagerModel = gson.fromJson(result, ViewPagerModel.class);
                List<ViewPagerModel.CatalogBean> data = viewPagerModel.getCatalog();
                adapter.updateRes(data);
                mDes.setText(viewPagerModel.getInfo().getInfo_des());
                mTime.setText(viewPagerModel.getInfo().getInfo_last_edit_time());
                mTitle.setText(viewPagerModel.getInfo().getInfo_title());
                x.image().bind(mImage,MyUrl.HEADER_URL + viewPagerModel.getInfo().getInfo_img_path());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });
    }

    private void initView() {
        mBack.setOnClickListener(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        View view = LayoutInflater.from(this).inflate(R.layout.view_pager_header, null);
        mDes = ((TextView) view.findViewById(R.id.view_pager_des));
        mTime = ((TextView) view.findViewById(R.id.view_pager_time));
        mTitle = ((TextView) view.findViewById(R.id.view_pager_title));
        mImage = ((ImageView) view.findViewById(R.id.view_pager_image));
        adapter = new ViewPagerAdapter(this,null);
        View footer = LayoutInflater.from(this).inflate(R.layout.view_pager_footer, null);
        mWeb = ((WebView) footer.findViewById(R.id.view_footer_web));
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(MyUrl.VIEWPAGER_WEB_START_URL + id + MyUrl.VIEWPAGER_WEB_END_URL);
        mListView.addFooterView(footer);
        mListView.setAdapter(adapter);
        mListView.addHeaderView(view);
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}

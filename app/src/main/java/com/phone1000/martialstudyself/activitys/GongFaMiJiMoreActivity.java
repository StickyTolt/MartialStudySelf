package com.phone1000.martialstudyself.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.martialstudyself.BaseApp;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapters.BookContentAdapter;
import com.phone1000.martialstudyself.constants.HttpParams;
import com.phone1000.martialstudyself.constants.HttpUrlSecond;
import com.phone1000.martialstudyself.model.LatestGongFa;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class GongFaMiJiMoreActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2<ListView>{

    private PullToRefreshListView mPullToRefresh;
    private ListView mListView;
    private BookContentAdapter adapter;
    private GridView mGridFenlei;
    private GridView mGridZiLei;
    private GridView mGridLeiXing;
    private String[] fenlei = {"全部", "武术门派", "武术器械", "外国功夫", "全部", "书籍图文", "视频教程", "电子书下载"};
    private String[] zilei = {};
    private String[] leixing = {"全部", "书籍图文", "视频教程", "电子书下载"};
    private List<String> nNames = new ArrayList<String>();

    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};

    private int page = 1;
    private Object dataStateDown;
    private String TAG = GongFaMiJiMoreActivity.class.getSimpleName();
    private TagFlowLayout mFlowLayout;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gong_fa_mi_ji_more);
        initView();
    }

    private void initView() {
        mInflater = LayoutInflater.from(this);
        mPullToRefresh = (PullToRefreshListView) findViewById(R.id.gongfamijilist);
        mPullToRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mPullToRefresh.setOnRefreshListener(this);
        mListView = mPullToRefresh.getRefreshableView();
        adapter = new BookContentAdapter(this);
        mListView.setAdapter(adapter);
        getData(State.DOWN);

        View inflate = LayoutInflater.from(this).inflate(R.layout.gongdamiji_header, null);
        //
        mFlowLayout = ((TagFlowLayout) inflate.findViewById(R.id.gongfamiji_fenlei));
        for (int i = 0; i < fenlei.length; i++) {
            nNames.add(fenlei[i]);
        }
        mFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tag,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });

//        mGridFenlei = (GridView) inflate.findViewById(R.id.gongfamiji_fenlei);
//        mGridFenlei.setAdapter(new GongFaMijiHeaderGridAdapter(fenlei, this));
//        mGridZiLei = (GridView) inflate.findViewById(R.id.gongfamiji_zilei);
//        mGridZiLei.setAdapter(new GongFaMijiHeaderGridAdapter(null, this));
//        mGridLeiXing = (GridView) inflate.findViewById(R.id.gongfamiji_leixing);
//        mGridLeiXing.setAdapter(new GongFaMijiHeaderGridAdapter(leixing, this));
        mListView.addHeaderView(inflate);

    }

    public void getData(final State state) {
        switch (state) {
            case DOWN:
                page = 1;
                break;
            case UP:
                page++;
                break;
        }

        OkHttpUtils.get()
                .url(HttpUrlSecond.GONGDAMIJI + "&page=" + page)
                .addHeader(HttpParams.CACHE_CONTROL, BaseApp.getCacheControl())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //  Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        LatestGongFa gongfulist = gson.fromJson(response, LatestGongFa.class);
                        List<LatestGongFa.ListBean> data = gongfulist.getList();
                        switch (state) {
                            case DOWN:
                                adapter.updateRes(data);
                                break;
                            case UP:
                                Log.e(TAG, "onResponse: " + data);
                                adapter.addRes(data);
                                break;
                        }

                        mPullToRefresh.onRefreshComplete();
                    }
                });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        getData(State.UP);
    }

    enum State {
        DOWN, UP
    }
}


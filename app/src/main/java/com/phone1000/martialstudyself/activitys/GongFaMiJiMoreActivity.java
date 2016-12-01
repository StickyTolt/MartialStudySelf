package com.phone1000.martialstudyself.activitys;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.martialstudyself.BaseApp;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapters.BookContentAdapter;
import com.phone1000.martialstudyself.constants.HttpParams;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.constants.HttpUrlSecond;
import com.phone1000.martialstudyself.model.LatestGongFa;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import okhttp3.Call;

public class GongFaMiJiMoreActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {

    private PullToRefreshListView mPullToRefresh;
    private ListView mListView;
    private BookContentAdapter adapter;
    private GridView mGridFenlei;
    private GridView mGridZiLei;
    private GridView mGridLeiXing;
    private String[] fenlei = {"全部", "武术门派", "武术器械", "外国功夫"};
    private String[] zileimenpai = {"全部", "少林", "武当", "峨眉", "太极拳", "八卦掌", "形意拳", "心意拳", "意拳&大成拳", "八极拳", "长拳", "南拳", "咏春拳", "通背拳", "劈挂拳", "三皇炮锤", "戳脚", "翻子拳", "查拳", "地躺拳", "象形拳", "气功", "散打&搏击&格斗", "擒拿", "防身自卫", "摔跤", "其他功夫"};
    private String[] zileiqiixe = {"全部", "刀术", "枪术", "剑术", "棍术", "斧法", "鞭法", "棒法", "其他器械"};
    private String[] zileiwaiguo = {"全部", "截拳道", "韩国跆拳道", "泰拳", "拳击", "巴西柔术", "菲律宾短棍", "日本柔道", "日本空手道", "日本合气道", "日本剑道", "日本忍术", "俄罗斯桑搏", "美国卡柔肯拳", "其他外国功夫"};
    private String[] leixing = {"全部", "书籍图文", "视频教程", "电子书下载"};
    private String GONGFAMIJI_URL = HttpUrlSecond.GONGDAMIJI;

    private int page = 1;
    private String TAG = GongFaMiJiMoreActivity.class.getSimpleName();
    private TagFlowLayout mFlowLayout;
    private TagFlowLayout mFlowLayoutZiLei;
    private TagFlowLayout mFlowLayoutLeiXing;
    private LayoutInflater mInflater;
    private LinearLayout mZiLeill;

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

        final View inflate = LayoutInflater.from(this).inflate(R.layout.gongdamiji_header, null);
        mZiLeill = ((LinearLayout) inflate.findViewById(R.id.gongfamiji_zilei_ll));
        mZiLeill.setVisibility(View.GONE);

        mFlowLayoutZiLei = ((TagFlowLayout) inflate.findViewById(R.id.gongfamiji_zilei));
        // 分类
        mFlowLayout = ((TagFlowLayout) inflate.findViewById(R.id.gongfamiji_fenlei));
        mFlowLayout.setAdapter(new TagAdapter<String>(fenlei) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tag,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Log.e(TAG, "onSelected: " + selectPosSet.size() + "--" + selectPosSet.toString());
                for (final int i : selectPosSet) {
                    switch (i) {
                        case 0:
                            mZiLeill.setVisibility(View.GONE);
                            break;
                        case 1:
                            mZiLeill.setVisibility(View.VISIBLE);
                            mFlowLayoutZiLei.setAdapter(new TagAdapter<String>(zileimenpai) {
                                @Override
                                public View getView(FlowLayout parent, int position, String s) {
                                    TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tag,
                                            mFlowLayoutZiLei, false);
                                    tv.setText(s);
                                    return tv;
                                }
                            });
                            break;
                        case 2:
                            mZiLeill.setVisibility(View.VISIBLE);
                            mFlowLayoutZiLei.setAdapter(new TagAdapter<String>(zileiqiixe) {
                                @Override
                                public View getView(FlowLayout parent, int position, String s) {
                                    TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tag,
                                            mFlowLayoutZiLei, false);
                                    tv.setText(s);
                                    return tv;
                                }
                            });
                            break;
                        case 3:
                            mZiLeill.setVisibility(View.VISIBLE);
                            mFlowLayoutZiLei.setAdapter(new TagAdapter<String>(zileiwaiguo) {
                                @Override
                                public View getView(FlowLayout parent, int position, String s) {
                                    TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tag,
                                            mFlowLayoutZiLei, false);
                                    tv.setText(s);
                                    return tv;
                                }
                            });
                            break;
                    }
                }
            }
        });
        // 子类

        // 类型
        mFlowLayoutLeiXing = ((TagFlowLayout) inflate.findViewById(R.id.gongfamiji_leixing));
        mFlowLayoutLeiXing.setAdapter(new TagAdapter<String>(fenlei) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tag,
                        mFlowLayoutLeiXing, false);
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
        mListView.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MiJiWebActivity.class);
        int info_id = adapter.getItem(position - 1).getInfo_id();
        int info_reply_count = adapter.getItem(position - 1).getInfo_reply_count();
        intent.putExtra("count", info_reply_count);
        intent.putExtra("id", info_id);
        startActivity(intent);
    }

    enum State {
        DOWN, UP
    }
}


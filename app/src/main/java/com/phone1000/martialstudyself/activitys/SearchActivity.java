package com.phone1000.martialstudyself.activitys;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.adapeters.ParentAdapter;
import com.phone1000.martialstudyself.adapeters.ParentGridViewAdapter;
import com.phone1000.martialstudyself.adapeters.SearchGridViewAdapter;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.interfaces.GridViewItemClick;
import com.phone1000.martialstudyself.interfaces.LoadingIsShown;
import com.phone1000.martialstudyself.model.GridViewModel;
import com.phone1000.martialstudyself.utils.ParentJsonParse;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_search)
public class SearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, GridViewItemClick, LoadingIsShown {
    private static final String TAG = SearchActivity.class.getSimpleName();
    @ViewInject(R.id.search_back)
    private ImageView mBack;
    @ViewInject(R.id.search_gridview)
    private GridView mGridView;
    @ViewInject(R.id.search_text)
    private EditText text;
    @ViewInject(R.id.seach_found)
    private ImageView mSearch;
    @ViewInject(R.id.search_listView)
    private ListView mListView;

    @ViewInject(R.id.relative_text)
    private RelativeLayout re_text;
    @ViewInject(R.id.search_text2)
    private TextView hot_search;
    @ViewInject(R.id.guanjianci)
    private LinearLayout guanjianci;

    private SearchGridViewAdapter adapter;
    private List<String> search;
    private ParentAdapter mAdapter;
    private int page = 1;
    private String mEdit;

    private int name;
    private EditText mResult;
    private ImageView mResultImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        GridViewModel model = new GridViewModel();
        search = model.search;
        mBack.setOnClickListener(this);
        adapter = new SearchGridViewAdapter(this, search);
        mGridView.setAdapter(adapter);
        mSearch.setOnClickListener(this);
        adapter.setItemClick(this);
        View view = LayoutInflater.from(this).inflate(R.layout.search_header, null);
        mResult = ((EditText) view.findViewById(R.id.result_text));
        mResultImg = ((ImageView) view.findViewById(R.id.result_found));
        mResultImg.setOnClickListener(this);
        mListView.addHeaderView(view);
        mAdapter = new ParentAdapter(this, null);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mAdapter.setIsShown(this);
        mEdit = text.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back:
                this.finish();
                break;
            case R.id.seach_found:
                loadData();
                break;
            case R.id.result_found:
                resultLoad();
                break;
        }

    }

    private void resultLoad() {
        String s = mResult.getText().toString();

        if (TextUtils.equals("", s)) {
            Toast.makeText(SearchActivity.this, "请输入要查找的内容", Toast.LENGTH_SHORT).show();
        } else {
            ParentJsonParse.ParentParse(mAdapter, MyUrl.SEARCH_URL + s+ "&"+ MyUrl.PARENT_ONE_CONTEXT_END + page);
        }
    }

    private void loadData() {
        if (TextUtils.equals("", mEdit)) {
            Toast.makeText(SearchActivity.this, "请输入要查找的内容", Toast.LENGTH_SHORT).show();
        } else {
            mResult.setText(mEdit);
            re_text.setVisibility(View.GONE);
            hot_search.setVisibility(View.GONE);
            guanjianci.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            ParentJsonParse.ParentParse(mAdapter, MyUrl.SEARCH_URL + mEdit+ "&"+ MyUrl.PARENT_ONE_CONTEXT_END + page);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int info_id = mAdapter.getItem(position).getInfo_id();
        Intent intent = new Intent(this, HomePositionActivity.class);
        intent.putExtra("id",info_id + "");
        startActivity(intent);
    }


    @Override
    public void itemClickListener(int tag) {
        re_text.setVisibility(View.GONE);
        hot_search.setVisibility(View.GONE);
        guanjianci.setVisibility(View.GONE);
        mListView.setVisibility(View.VISIBLE);
        name = tag;
        mResult.setText(search.get(name));
        String url = MyUrl.SEARCH_URL + search.get(name) + "&"  + MyUrl.PARENT_ONE_CONTEXT_END + page;
        ParentJsonParse.ParentParse(mAdapter, url);

    }

    @Override
    public void isShownLoad() {
        page++;
        if (TextUtils.equals("", mEdit)) {
            ParentJsonParse.ParentParseLoad(mAdapter, MyUrl.SEARCH_URL + search.get(name) + "&"  + MyUrl.PARENT_ONE_CONTEXT_END + page);
        } else {
            ParentJsonParse.ParentParseLoad(mAdapter, MyUrl.SEARCH_URL + mEdit+ "&"+ MyUrl.PARENT_ONE_CONTEXT_END + page);
        }

    }
}

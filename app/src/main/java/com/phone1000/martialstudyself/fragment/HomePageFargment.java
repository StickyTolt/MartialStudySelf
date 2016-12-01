package com.phone1000.martialstudyself.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.ExperienceActivity;
import com.phone1000.martialstudyself.activitys.HomePositionActivity;
import com.phone1000.martialstudyself.activitys.HomeSecondActivity;
import com.phone1000.martialstudyself.activitys.MoreCyclopediaAcitivity;
import com.phone1000.martialstudyself.activitys.MrLiActivity;
import com.phone1000.martialstudyself.activitys.ParentOneActivity;
import com.phone1000.martialstudyself.activitys.ParentTwoActivity;
import com.phone1000.martialstudyself.adapeters.HomeElvAdapter;
import com.phone1000.martialstudyself.adapeters.HomeViewPagerAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.interfaces.HomeParentListener;
import com.phone1000.martialstudyself.interfaces.ViewPagerClickListener;
import com.phone1000.martialstudyself.model.HomeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/11/27.
 */
@ContentView(R.layout.home_page_fragment)
public class HomePageFargment extends Fragment implements ViewPager.OnPageChangeListener, Handler.Callback, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener,  HomeParentListener, ViewPagerClickListener {

    private static final int PLAY = 100;
    @ViewInject(R.id.home_page_elv)
    private ExpandableListView mElv;


    public static final String TAG = HomePageFargment.class.getSimpleName();
    private HomeElvAdapter adapter;
    private DisplayMetrics metrics = new DisplayMetrics();
    private ViewPager mViewPager;
    private HomeViewPagerAdapter mVPadapter;
    private List<ImageView> imgList = new ArrayList<>();
    private LinearLayout mPoint;

    private List<String> title = new ArrayList<>();

    int[] imgId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    private int position;

    private int curIndex;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < imgId.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imgId[i]);
            imgList.add(imageView);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupView();
    }

    private void initPoint() {
        View view = new View(getContext());
        view.setBackgroundResource(R.mipmap.dot_normal);
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, metrics);
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, metrics);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) width, (int) height);

        params.leftMargin = 5;
        view.setLayoutParams(params);
        mPoint.addView(view);
    }

    private void setupView() {
        RequestParams params = new RequestParams(HttpUrl.HOME_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: ");
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    Type type = new TypeToken<List<HomeModel>>() {
                    }.getType();
                    Gson gson = new Gson();
                    List<HomeModel> data = gson.fromJson(jsonArray.toString(), type);
                    for (int i = 0; i < data.get(0).getList().size(); i++) {

                        title.add(data.get(0).getList().get(i).getLink());
                        x.image().bind(imgList.get(i), MyUrl.HEADER_URL + "/" + data.get(0).getList().get(i).getPic());
                        initPoint();
                    }
                    ImageView imageView = new ImageView(getContext());
                    ImageView imageViewLast = new ImageView(getContext());
                    x.image().bind(imageView, MyUrl.HEADER_URL + "/" + data.get(0).getList().get(0).getPic());
                    x.image().bind(imageViewLast, MyUrl.HEADER_URL + "/" + data.get(0).getList().get(3).getPic());
                    imgList.add(0,imageViewLast);
                    imgList.add(imageView);
                    Log.e(TAG, "onSuccess: " + imgList.size() );
                    mPoint.getChildAt(0).setBackgroundResource(R.mipmap.dot_enable);
                    mVPadapter = new HomeViewPagerAdapter(imgList);
                    mViewPager.setAdapter(mVPadapter);
                    mViewPager.setCurrentItem(1);
                    mVPadapter.setListener(HomePageFargment.this);
                    mVPadapter.notifyDataSetChanged();
                    adapter.updateRes(data);
                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                        mElv.expandGroup(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished:");
            }
        });
    }

    private void initView() {
        View mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.home_header, null);
        mViewPager = (ViewPager) mHeaderView.findViewById(R.id.header_viewpager);
        mViewPager.addOnPageChangeListener(this);
//        mViewPager.setOnClickListener(this);
        mPoint = (LinearLayout) mHeaderView.findViewById(R.id.header_point);

        adapter = new HomeElvAdapter(getContext(), null);
        mElv.setAdapter(adapter);
        mElv.addHeaderView(mHeaderView);
        mElv.setOnChildClickListener(this);
        mElv.setOnGroupClickListener(this);
        handler = new Handler(this);
        handler.sendEmptyMessageDelayed(PLAY, 3000);
        adapter.setListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        this.position = position;
        if (mPoint.getChildCount() != 0) {
            switch (position) {
                case 1:
                    mPoint.getChildAt(0).setBackgroundResource(R.mipmap.dot_enable);
                    mPoint.getChildAt(1).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(2).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(3).setBackgroundResource(R.mipmap.dot_normal);
                    break;
                case 2:
                    mPoint.getChildAt(0).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(1).setBackgroundResource(R.mipmap.dot_enable);
                    mPoint.getChildAt(2).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(3).setBackgroundResource(R.mipmap.dot_normal);
                    break;
                case 3:
                    mPoint.getChildAt(0).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(1).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(2).setBackgroundResource(R.mipmap.dot_enable);
                    mPoint.getChildAt(3).setBackgroundResource(R.mipmap.dot_normal);
                    break;
                case 4:
                    mPoint.getChildAt(0).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(1).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(2).setBackgroundResource(R.mipmap.dot_normal);
                    mPoint.getChildAt(3).setBackgroundResource(R.mipmap.dot_enable);
                    break;
            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (position < 1 && state == 0) {
            position = 4;
            mViewPager.setCurrentItem(position, false);

        } else if (position > 4 && state == 0) {
            position = 1;
            mViewPager.setCurrentItem(position, false);
        }
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if (groupPosition == 3) {
            String info_id = adapter.getChild(groupPosition + 1, childPosition).getTopic_id();
            Intent intent = new Intent(getContext(), HomeSecondActivity.class);
            intent.putExtra("id", info_id);
            Log.e(TAG, "onChildClick: " + info_id);
            startActivity(intent);
        } else {
            String topic_id = adapter.getChild(groupPosition + 1, childPosition).getInfo_id();
            Intent intent = new Intent(getContext(), HomePositionActivity.class);
            intent.putExtra("id", topic_id);
            Log.e(TAG, "onChildClick: " + topic_id);
            startActivity(intent);
        }


        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

        return true;
    }




    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case PLAY:
                mViewPager.setCurrentItem(position +1);
                handler.sendEmptyMessageDelayed(PLAY, 3000);
                break;
        }
        return false;
    }

    @Override
    public void parentMoreClick(int group) {
        Intent intent = new Intent();
        switch (group) {
            case 1:
                intent.setClass(getContext(),ParentOneActivity.class);
                break;
            case 2:
                intent.setClass(getContext(),ParentTwoActivity.class);
                break;
            case 3:
                Bundle bundle = new Bundle();
                bundle.putString("name","全部");
                intent.putExtras(bundle);
                intent.setClass(getContext(),MoreCyclopediaAcitivity.class);
                break;
            /*case 4:
               *//* FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                TalkMartialFragment talkMartialFragment = new TalkMartialFragment();
                transaction.replace(R.id.main_container,talkMartialFragment,TalkMartialFragment.TAG);
                transaction.commit();*//*
                break;*/
        }
        startActivity(intent);
    }

    @Override
    public void imageClickListener() {
        Intent intent = new Intent();
        switch (mViewPager.getCurrentItem()) {
            case 1:
                intent.setClass(getContext(),ExperienceActivity.class);
                intent.putExtra("id",5514 +"");
                break;
            case 2:
                intent.setClass(getContext(),ExperienceActivity.class);
                intent.putExtra("id",10726 +"");
                break;
            case 3:
                intent.setClass(getContext(),ExperienceActivity.class);
                intent.putExtra("id",10966 +"");
                break;
            case 4:
                intent.setClass(getContext(), MrLiActivity.class);
                break;
        }
        startActivity(intent);
    }
}

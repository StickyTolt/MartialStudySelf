package com.phone1000.martialstudyself.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.phone1000.martialstudyself.BaseApp;
import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.CommentActivity;
import com.phone1000.martialstudyself.activitys.ItemActivity;
import com.phone1000.martialstudyself.activitys.LogInActivity;
import com.phone1000.martialstudyself.adapters.CommentAdapter;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.interfaces.ICommentItem;
import com.phone1000.martialstudyself.utils.JsonParseAndUpdate;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 马金利 on 2016/11/27.
 */
@ContentView(R.layout.talk_martial_fragment)
public class TalkMartialFragment extends Fragment implements ICommentItem, View.OnClickListener {
    @ViewInject(R.id.talk_martial_fragment_rv)
    private RecyclerView mRV;
    @ViewInject(R.id.talk_martial_youbiao)
    private ImageView youbiao;
    @ViewInject(R.id.talk_martial_rl)
    private RelativeLayout mLL;

    public static final String TAG = TalkMartialFragment.class.getSimpleName();
    private CommentAdapter adapter;
    private int PAGE_NUM = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CommentAdapter(null, getContext());
        mRV.setAdapter(adapter);
        adapter.setCommentItem(this);
        JsonParseAndUpdate.commentJPAU(adapter, HttpUrl.COMMENT_URL + PAGE_NUM);
        youbiao.setOnClickListener(this);

        mLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogIn();
            }
        });
    }

    @Override
    public void requestTopicId(int topicId) {
        Intent intent = new Intent(getActivity(), ItemActivity.class);
        intent.putExtra("topicId", topicId + "");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        startLogIn();
    }

    private void startLogIn(){
        if (!BaseApp.isLogIn) {
            Dialog alertDialog = new AlertDialog.Builder(getContext()).
                    //设置标题
                            setTitle("自学武术").
                    //设置内容
                            setMessage("您还未登录，是否现在登录？").
                    //设置按钮事件
                            setPositiveButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                        }
                    }).setNegativeButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            startActivity(new Intent(getActivity(), LogInActivity.class));
                        }
                    }).
                    //创建
                            create();
            //显示
            alertDialog.show();
        }else {
            startActivity(new Intent(getActivity(), CommentActivity.class));
        }
    }

}

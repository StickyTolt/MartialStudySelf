package com.phone1000.martialstudyself.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.ItemActivity;
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
public class TalkMartialFragment extends Fragment implements ICommentItem {
    @ViewInject(R.id.talk_martial_fragment_rv)
    private RecyclerView mRV;

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
    }

    @Override
    public void requestTopicId(int topicId) {
        String url = HttpUrl.COMMENT_ITEM + topicId;
        Intent intent = new Intent(getActivity(), ItemActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}

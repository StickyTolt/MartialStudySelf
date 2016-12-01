package com.phone1000.martialstudyself.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.activitys.GongFaMiJiMoreActivity;
import com.phone1000.martialstudyself.adapters.BookTopItemAdapter;

/**
 * Created by chen on 2016/11/28.
 */
public class BookTopPagerItemFragment extends Fragment implements AdapterView.OnItemClickListener {



    private static final String TAG = BookTopPagerItemFragment.class.getSimpleName();
    private View layout;
    private GridView mGridView;
    private Bundle agrs;
    private String[] stringArray = {};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agrs = this.getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.topitem_fragment, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        //    mGridView = ((GridView) layout.findViewById(R.id.book_top_item_gridview));
        mGridView = ((GridView) layout.findViewById(R.id.book_top_item_gridview));
        Bundle arguments = getArguments();
        for (int i = 0; i < 3; i++) {
            stringArray = arguments.getStringArray("title" + i);
            if (stringArray != null) {
                mGridView.setAdapter(new BookTopItemAdapter(stringArray, getContext()));
            }
        }
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), GongFaMiJiMoreActivity.class);
        TextView viewById = (TextView) mGridView.getChildAt(position).findViewById(R.id.book_top_item_menpai);
        Log.e(TAG, "onItemClick: " + viewById.getText());
        // Log.e(TAG, "onItemClick: " + stringArray[position]);
        startActivity(intent);
    }
}

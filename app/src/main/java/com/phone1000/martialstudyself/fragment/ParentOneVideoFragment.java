package com.phone1000.martialstudyself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.martialstudyself.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by 马金利 on 2016/11/30.
 */
@ContentView(R.layout.parent_one_video_fragment)
public class ParentOneVideoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }
}

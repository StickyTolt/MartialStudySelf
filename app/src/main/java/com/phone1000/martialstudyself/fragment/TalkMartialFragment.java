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
 * Created by 马金利 on 2016/11/27.
 */
@ContentView(R.layout.talk_martial_fragment)
public class TalkMartialFragment extends Fragment{

    public static final String TAG = TalkMartialFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return  x.view().inject(this,inflater,container);
    }
}

package com.phone1000.martialstudyself.adapeters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/11/30.
 */
public class ParentTwoVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> data ;

    private List<String> title;
    public ParentTwoVPAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
        title = new ArrayList<>();
        title.add("全部");
        title.add("武术门派");
        title.add("武术器械");
        title.add("外国功夫");
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}

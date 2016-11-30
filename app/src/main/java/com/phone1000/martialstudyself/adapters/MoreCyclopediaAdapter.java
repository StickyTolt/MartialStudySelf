package com.phone1000.martialstudyself.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/11/30.
 */
public class MoreCyclopediaAdapter extends FragmentPagerAdapter {

    private List<Fragment> data ;
    private List<String> titles;

    public MoreCyclopediaAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        if (list == null) {
            list = new ArrayList<>();
        }
        data = list;
        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("武术门派");
        titles.add("经络脉穴");
        titles.add("武术名家");
        titles.add("兵器库");
        titles.add("说文解字");
        titles.add("武林玄学");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}

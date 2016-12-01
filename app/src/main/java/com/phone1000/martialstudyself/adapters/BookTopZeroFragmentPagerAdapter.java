package com.phone1000.martialstudyself.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chen on 2016/11/28.
 */
public class BookTopZeroFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragements;

    public BookTopZeroFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragements) {
        super(fm);
        this.fragements = fragements;
    }

    @Override
    public Fragment getItem(int position) {
        return fragements.get(position);
    }

    @Override
    public int getCount() {
        return fragements.size();
    }

}

package com.phone1000.martialstudyself.adapeters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 马金利 on 2016/11/28.
 */
public class HomeViewPagerAdapter extends PagerAdapter {

    private List<ImageView> data;

    public HomeViewPagerAdapter(List<ImageView> data) {
        this.data = data;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        position %= data.size();
//        if (position < 0) {
//            position = data.size() + position;
//        }
        ImageView view = data.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(data.get(position));
        return data.get(position);
    }

    @Override
    public int getCount() {
        //Integer.MAX_VALUE 设置成最大，用户看不到边界
        return data.size() ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(data.get(position));
    }
}

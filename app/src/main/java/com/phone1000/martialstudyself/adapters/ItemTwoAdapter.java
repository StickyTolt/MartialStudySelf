package com.phone1000.martialstudyself.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.models.CyclopediaModel;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/11/29.
 */
public class ItemTwoAdapter extends BaseAdapter {

    private static final String TAG = ItemTwoAdapter.class.getSimpleName();
    private List<CyclopediaModel.ListBean> data;
    private LayoutInflater inflater;
    private ImageOptions options;

    public ItemTwoAdapter(List<CyclopediaModel.ListBean> data, Context context) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        options = new ImageOptions.Builder()
                .setCircular(true)
                .setFailureDrawableId(R.mipmap.kungfu2)
                .setLoadingDrawableId(R.mipmap.kungfu4)
                .build();

    }

    public void updateRes(List<CyclopediaModel.ListBean> listBeen) {
        if (listBeen != null) {
            data.clear();
            data.addAll(listBeen);
            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CyclopediaModel.ListBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cyclopedia_two_child, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(getItem(position).getInfo_title());
        String url = "http://www.wushu520.com" + getItem(position).getInfo_img_path();
        Log.e(TAG, "getView: 这个就是那些加载不出来的网址！！！" + url );
        x.image().bind(holder.image, url, options);
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.two_child_text)
        TextView name;
        @ViewInject(R.id.two_child_image)
        ImageView image;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }

}

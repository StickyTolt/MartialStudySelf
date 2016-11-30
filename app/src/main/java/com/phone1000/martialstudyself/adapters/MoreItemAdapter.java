package com.phone1000.martialstudyself.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.interfaces.INotifyAddRes;
import com.phone1000.martialstudyself.model.MoreModel;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/11/30.
 */
public class MoreItemAdapter extends BaseAdapter {

    private List<MoreModel.ListBean> data;
    private LayoutInflater inflater;
    private ImageOptions options;
    private INotifyAddRes iNotifyAddRes;

    public void setiNotifyAddRes(INotifyAddRes iNotifyAddRes) {
        this.iNotifyAddRes = iNotifyAddRes;
    }

    public MoreItemAdapter(List<MoreModel.ListBean> data, Context context) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        if (data.size() > 0) {
            this.data.add(new MoreModel.ListBean());
        }
        this.inflater = LayoutInflater.from(context);
        options = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.kungfu1)
                .setLoadingDrawableId(R.mipmap.kungfu6)
                .build();
    }

    public void updateRes(List<MoreModel.ListBean> listBeen) {
        if (listBeen != null) {
            data.clear();
            data.addAll(listBeen);
            data.add(new MoreModel.ListBean());
            notifyDataSetChanged();
        }
    }

    public void addRes(List<MoreModel.ListBean> list) {
        if (list != null) {
            data.remove(data.size() - 1);
            data.addAll(list);
            data.add(new MoreModel.ListBean());
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position != data.size() - 1 ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MoreModel.ListBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case 0:
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_cyclorpedia_child, parent, false);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                MoreModel.ListBean item = getItem(position);
                holder.childTitle.setText(item.getInfo_title());
                holder.childCount.setText("  " + item.getInfo_read_count());
                holder.childQipao.setText("  " + item.getInfo_reply_count());
                x.image().bind(holder.childImage, "http://www.wushu520.com" + item.getInfo_img_path(), options);

                break;
            case 1:
                convertView = inflater.inflate(R.layout.item_upliad, parent, false);
                if (iNotifyAddRes != null) {
                    iNotifyAddRes.addResForI();
                }

                ObjectAnimator animator = ObjectAnimator.ofFloat(((ImageView) convertView.findViewById(R.id.item_upload_image)), "rotation", 0, 3600);
                animator.setDuration(4000);
                animator.start();
                break;
        }
        return convertView;
    }


    private static class ViewHolder {
        @ViewInject(R.id.item_cyclopedia_child_title)
        TextView childTitle;
        @ViewInject(R.id.item_cyclopedia_child_name)
        TextView childName;
        @ViewInject(R.id.item_cyclopedia_child_qipao)
        TextView childQipao;
        @ViewInject(R.id.item_cyclopedia_child_see_count)
        TextView childCount;
        @ViewInject(R.id.item_cyclopedia_child_image)
        ImageView childImage;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }

}

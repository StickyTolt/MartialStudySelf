package com.phone1000.martialstudyself.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.constants.HttpUrlSecond;
import com.phone1000.martialstudyself.model.LatestGongFa;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/11/29.
 */
public class BookContentAdapter extends BaseAdapter {

    private List<LatestGongFa.ListBean> data;
    private LayoutInflater inflater;

    public BookContentAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    public void updateRes(List<LatestGongFa.ListBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public LatestGongFa.ListBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_latest_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(getItem(position).getInfo_title());
        holder.comments.setText(getItem(position).getInfo_reply_count() + "");
        holder.readcount.setText(getItem(position).getInfo_read_count() + "");
        String info_img_path = getItem(position).getInfo_img_path();
        String[] split = info_img_path.split(",");
        if (split.length == 0) {
            holder.image.setVisibility(View.GONE);
        }
        Picasso.with(holder.image.getContext()).load(HttpUrlSecond.IMAGE_BASE + split[0])
                .into(holder.image);
//        if (getItem(position).getInfo_type() == 2) {
//            holder.video.setVisibility(View.VISIBLE);
//        }

        return convertView;
    }

    public void addRes(List<LatestGongFa.ListBean> data) {
        if (data != null) {
            this.data.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    private static class ViewHolder {
        TextView title, comments, readcount;
        ImageView image, video;

        public ViewHolder(View itemview) {
            title = (TextView) itemview.findViewById(R.id.child_title);
            comments = (TextView) itemview.findViewById(R.id.child_reply_count);
            readcount = (TextView) itemview.findViewById(R.id.child_read_count);

            image = (ImageView) itemview.findViewById(R.id.child_image);
            video = (ImageView) itemview.findViewById(R.id.child_video);
        }
    }

}

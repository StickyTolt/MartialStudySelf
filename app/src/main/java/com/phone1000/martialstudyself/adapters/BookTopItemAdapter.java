package com.phone1000.martialstudyself.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.MaskTransformation;

import static com.phone1000.martialstudyself.R.mipmap.menpai0;

/**
 * Created by chen on 2016/11/29.
 */
public class BookTopItemAdapter extends BaseAdapter {

    private String[] data;
    private LayoutInflater inflater;

    public BookTopItemAdapter(String[] data, Context context) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_item_grid_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.image
        // x.image().bind(holder,);
        //holder.image.se
        switch (position % 4) {
            case 0:
                Picasso.with(holder.image.getContext()).load(R.mipmap.menpai0)
                        .transform(new MaskTransformation(holder.image.getContext(), R.mipmap.circle))
                        .into(holder.image);
                break;
            case 1:
                Picasso.with(holder.image.getContext()).load(R.mipmap.menpai1)
                        .transform(new MaskTransformation(holder.image.getContext(), R.mipmap.circle)).into(holder.image);
                break;
            case 2:
                Picasso.with(holder.image.getContext()).load(R.mipmap.menpai2)
                        .transform(new MaskTransformation(holder.image.getContext(), R.mipmap.circle)).into(holder.image);
                break;
            case 3:
                Picasso.with(holder.image.getContext()).load(R.mipmap.menpai3)
                        .transform(new MaskTransformation(holder.image.getContext(), R.mipmap.circle)).into(holder.image);
                break;
        }
        holder.name.setText(data[position]);
        return convertView;
    }


    private static class ViewHolder {
        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.book_top_item_img);
            name = (TextView) itemView.findViewById(R.id.book_top_item_menpai);
        }
    }
}

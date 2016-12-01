package com.phone1000.martialstudyself.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;

/**
 * Created by chen on 2016/11/28.
 */
public class BookTopPagerAdapter extends BaseAdapter {
    private String[] data = {"武术门派", "武术器械", "外国功夫"};
    private LayoutInflater inflater;
    private SendViewPagerListener listener;

    public BookTopPagerAdapter(Context context, SendViewPagerListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView = inflater.inflate(R.layout.book_top_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(getItem(position));
        holder.content.setTag(position);

       listener.sendViewPager(holder.content);
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        RecyclerView content;

        public ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.book_top_title);
            content = (RecyclerView) itemView.findViewById(R.id.book_top_body);
        }
    }

    public interface SendViewPagerListener {
        void sendViewPager(RecyclerView pager);
    }
}

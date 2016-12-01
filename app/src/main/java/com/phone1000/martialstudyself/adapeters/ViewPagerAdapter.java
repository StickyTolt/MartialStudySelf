package com.phone1000.martialstudyself.adapeters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.model.ViewPagerModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/12/1.
 */
public class ViewPagerAdapter extends BaseAdapter {

    private List<ViewPagerModel.CatalogBean> data;
    private LayoutInflater inflater;

    public ViewPagerAdapter(Context context,List<ViewPagerModel.CatalogBean> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }

    }

    public void updateRes(List<ViewPagerModel.CatalogBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public ViewPagerModel.CatalogBean getItem(int position) {
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
            convertView = inflater.inflate(R.layout.view_pager_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(getItem(position).getInfo_title());
        return convertView;
    }

    public class ViewHolder{
        @ViewInject(R.id.view_item_title)
        TextView title;
        public ViewHolder(View itemView) {
            x.view().inject(this,itemView);
        }
    }
}

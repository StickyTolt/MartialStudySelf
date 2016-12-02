package com.phone1000.martialstudyself.adapeters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.interfaces.GridViewItemClick;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/12/1.
 */
public class ParentGridViewAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = ParentGridViewAdapter.class.getSimpleName();
    private List<String> data;
    private LayoutInflater inflater;
    private List<Integer> checkORno;

    private GridViewItemClick itemClick;

    public void setItemClick(GridViewItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public ParentGridViewAdapter(Context context, List<String> data) {
        inflater = LayoutInflater.from(context);
        if (data == null) {
            this.data = new ArrayList<>();
        }
        this.data = data;
        checkORno = new ArrayList<>();
        checkORno.add(1);
        for (int i = 0; i < data.size() - 1; i++) {
            checkORno.add(0);
        }

    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public String getItem(int position) {
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
            convertView = inflater.inflate(R.layout.parent_header_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mBox.setText(getItem(position));
        if (checkORno.get(position) == 1) {
            holder.mBox.setChecked(true);
        } else {
            holder.mBox.setChecked(false);
        }

        holder.mBox.setTag(position);
        holder.mBox.setOnCheckedChangeListener(this);
        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int tag = (int) buttonView.getTag();
        Log.e(TAG, "onCheckedChanged: 你点击的条目是！！！！！----" + tag);
        if (isChecked && checkORno.get(tag) == 0) {
            checkORno.set(tag, 1);
            for (int i = 0; i < checkORno.size(); i++) {
                if (i != tag) {
                    checkORno.set(i, 0);
                }
            }
            if (itemClick !=null) {
                itemClick.itemClickListener(tag);
            }
            notifyDataSetChanged();
        }else if (!isChecked&&checkORno.get(tag)==1){
            buttonView.setChecked(true);
        }
    }

    public class ViewHolder {

        @ViewInject(R.id.parent_header_checkbox)
        CheckBox mBox;

        public ViewHolder(View itemView) {
            x.view().inject(this, itemView);
        }
    }
}

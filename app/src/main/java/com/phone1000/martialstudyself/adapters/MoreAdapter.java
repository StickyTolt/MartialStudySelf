package com.phone1000.martialstudyself.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.interfaces.IItemMoreItem;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/11/30.
 */
public class MoreAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = MoreAdapter.class.getSimpleName();
    private List<String> data;
    private LayoutInflater inflater;
    private List<Integer> checkORno;
    private IItemMoreItem iItemMoreItem;

    public MoreAdapter(List<String> data, Context context,IItemMoreItem iItemMoreItem) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        checkORno = new ArrayList<>();
        checkORno.add(1);
        this.iItemMoreItem = iItemMoreItem;
        for (int i = 0; i < data.size() - 1; i++) {
            checkORno.add(0);
        }
    }


    @Override
    public int getCount() {
        return data.size();
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
            convertView = inflater.inflate(R.layout.item_more_child, parent, false);
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
            notifyDataSetChanged();
            if (iItemMoreItem != null) {
                iItemMoreItem.updateLV(tag);
            }
        }else if (!isChecked&&checkORno.get(tag)==1){
            buttonView.setChecked(true);
        }

    }

    public class ViewHolder {

        @ViewInject(R.id.item_more_child_box)
        CheckBox mBox;

        public ViewHolder(View itemView) {
            x.view().inject(this, itemView);
        }
    }
}

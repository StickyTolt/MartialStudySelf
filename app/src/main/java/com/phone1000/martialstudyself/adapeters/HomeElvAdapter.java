package com.phone1000.martialstudyself.adapeters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.model.HomeModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/11/28.
 */
public class HomeElvAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private List<HomeModel> data;
    private LayoutInflater inflater;
    private boolean isChecked = false;
    private int[] size = {7,7,7,7};



    public HomeElvAdapter(Context context, List<HomeModel> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<HomeModel> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getGroupCount() {
        return data != null ? data.size() - 1 : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

            return size[groupPosition];

    }

    @Override
    public HomeModel getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public HomeModel.ListBean getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderParent holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_parent_item, parent, false);
            holder = new ViewHolderParent(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderParent) convertView.getTag();
        }
        holder.parent_title.setText(getGroup(groupPosition + 1).getDesc());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_child_item, parent, false);
            holder = new ViewHolderChild(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderChild) convertView.getTag();
        }
        if (groupPosition == 3) {
            holder.child_title.setText(getChild(groupPosition + 1, childPosition).getTopic_title());
            holder.child_reply_count.setText(getChild(groupPosition + 1, childPosition).getTopic_reply_count());
            holder.child_author.setText(getChild(groupPosition + 1, childPosition).getTopic_add_user());
            if (childPosition == 6 && size[groupPosition] != 14) {
                holder.child_more.setVisibility(View.VISIBLE);
            } else {
                holder.child_more.setVisibility(View.GONE);
            }
        } else {
            holder.child_title.setText(getChild(groupPosition + 1, childPosition).getInfo_title());
            holder.child_read_count.setText(getChild(groupPosition + 1, childPosition).getInfo_read_count());
            holder.child_reply_count.setText(getChild(groupPosition + 1, childPosition).getInfo_reply_count());
            x.image().bind(holder.child_image, HttpUrl.HEADER_URL + getChild(groupPosition + 1, childPosition).getInfo_img_path());
            if (childPosition == 6 && size[groupPosition] != 14) {
                holder.child_more.setVisibility(View.VISIBLE);
            } else {
                holder.child_more.setVisibility(View.GONE);
            }
        }
        if (isLastChild) {
//            holder.child_more;
        }
        holder.child_more.setTag(groupPosition);
        holder.child_more.setOnClickListener(this);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        size[position] = 14;
        notifyDataSetChanged();
    }


    public static class ViewHolderParent {
        @ViewInject(R.id.parent_title)
        TextView parent_title;

        public ViewHolderParent(View itemView) {
            x.view().inject(this, itemView);
        }
    }



    public static class ViewHolderChild {

        @ViewInject(R.id.child_title)
        TextView child_title;
        @ViewInject(R.id.child_read_count)
        TextView child_read_count;
        @ViewInject(R.id.child_reply_count)
        TextView child_reply_count;
        @ViewInject(R.id.child_image)
        ImageView child_image;
        @ViewInject(R.id.child_more)
        ImageView child_more;
        @ViewInject(R.id.child_author)
        TextView child_author;

        public ViewHolderChild(View itemView) {
            x.view().inject(this, itemView);
        }

    }


}

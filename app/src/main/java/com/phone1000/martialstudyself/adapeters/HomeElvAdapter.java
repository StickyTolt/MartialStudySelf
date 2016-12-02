package com.phone1000.martialstudyself.adapeters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.constants.HttpUrl;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.interfaces.HomeParentListener;
import com.phone1000.martialstudyself.model.HomeModel;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/11/28.
 */
public class HomeElvAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private static final String TAG = HomeElvAdapter.class.getSimpleName();
    private final ImageOptions options;
    private List<HomeModel> data;
    private LayoutInflater inflater;
    private int[] size = {7, 7, 7, 7};
    private HomeParentListener listener;

    public void setListener(HomeParentListener listener) {
        this.listener = listener;
    }

    public HomeElvAdapter(Context context, List<HomeModel> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        options = new ImageOptions.Builder().setFailureDrawableId(R.mipmap.kungfu2).build();
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
    public int getChildType(int groupPosition, int childPosition) {
        int type = 0;
        if (groupPosition + 1 == 4) {
            type = 1;
        }
        return type;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
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
        holder.parent_more.setTag(groupPosition + 1);
        holder.parent_more.setOnClickListener(this);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        switch (getChildType(groupPosition, childPosition)) {
            case 0:
                ViewHolderChild holder = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.home_child_item, parent, false);
                    holder = new ViewHolderChild(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolderChild) convertView.getTag();
                }

                if (childPosition == 6 && size[groupPosition] != 14) {
                    holder.child_more.setVisibility(View.VISIBLE);
                } else {
                    holder.child_more.setVisibility(View.GONE);
                    holder.child_jump.setVisibility(View.GONE);
                }

                holder.child_title.setText(getChild(groupPosition + 1, childPosition).getInfo_title());
                holder.child_read_count.setText(getChild(groupPosition + 1, childPosition).getInfo_read_count());
                holder.child_reply_count.setText(getChild(groupPosition + 1, childPosition).getInfo_reply_count());
                x.image().bind(holder.child_image, MyUrl.HEADER_URL + getChild(groupPosition + 1, childPosition).getInfo_img_path(), options);
                if (childPosition == 6 && size[groupPosition] != 14) {
                    holder.child_more.setVisibility(View.VISIBLE);
                } else {
                    holder.child_more.setVisibility(View.GONE);
                    holder.child_jump.setVisibility(View.GONE);
                }

                if (isLastChild && childPosition != 6) {
                    holder.child_jump.setTag(groupPosition+ 1);
                    holder.child_jump.setVisibility(View.VISIBLE);
                    holder.child_jump.setOnClickListener(this);
                }
                holder.child_more.setTag(groupPosition);
                holder.child_more.setOnClickListener(this);
                break;
            case 1:
                ViewHolderChildOne holderOne = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.home_child_item_one, parent, false);
                    holderOne = new ViewHolderChildOne(convertView);
                    convertView.setTag(holderOne);
                } else {
                    holderOne = (ViewHolderChildOne) convertView.getTag();
                }

                if (childPosition == 6 && size[groupPosition] != 14) {
                    holderOne.child_more_one.setVisibility(View.VISIBLE);
                } else {
                    holderOne.child_more_one.setVisibility(View.GONE);
                    holderOne.child_jump_one.setVisibility(View.GONE);
                }

                holderOne.child_title_one.setText(getChild(groupPosition + 1, childPosition).getTopic_title());
                holderOne.child_reply_count_one.setText(getChild(groupPosition + 1, childPosition).getTopic_reply_count());
                holderOne.child_author_one.setText(getChild(groupPosition + 1, childPosition).getTopic_add_user());

                if (isLastChild && childPosition != 6) {
                    holderOne.child_jump_one.setTag(groupPosition + 1);
                    holderOne.child_jump_one.setVisibility(View.VISIBLE);
                    holderOne.child_jump_one.setOnClickListener(this);
                }
                holderOne.child_more_one.setTag(groupPosition);
                holderOne.child_more_one.setOnClickListener(this);
                break;
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.child_more_one:
                int position_one = (int) v.getTag();
                size[position_one] = 14;
                Log.e(TAG, "onClick: " + position_one);
                notifyDataSetChanged();
                break;
            case R.id.child_more:
                int position = (int) v.getTag();
                size[position] = 14;
                Log.e(TAG, "onClick: " + position);
                notifyDataSetChanged();
                break;
            case R.id.parent_more:
                int group = (int) v.getTag();
                Log.e(TAG, "onClick: parent " + group );
                if (listener != null) {
                    listener.parentMoreClick(group);
                }
                break;
            case R.id.child_jump:
                int group_jump = (int) v.getTag();
                Log.e(TAG, "onClick: child " + group_jump );
                if (listener != null) {
                    listener.parentMoreClick(group_jump);
                }
                break;
            case R.id.child_jump_one:
                int group_jump_one = (int) v.getTag();
                Log.e(TAG, "onClick: childone " + group_jump_one );
                if (listener != null) {
                    listener.parentMoreClick(group_jump_one);
                }
                break;
        }

    }


    public static class ViewHolderParent {
        @ViewInject(R.id.parent_title)
        TextView parent_title;
        @ViewInject(R.id.parent_more)
        TextView parent_more;

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
        @ViewInject(R.id.child_jump)
        TextView child_jump;

        public ViewHolderChild(View itemView) {
            x.view().inject(this, itemView);
        }

    }

    public static class ViewHolderChildOne {
        @ViewInject(R.id.child_title_one)
        TextView child_title_one;
        @ViewInject(R.id.child_reply_count_one)
        TextView child_reply_count_one;
        @ViewInject(R.id.child_author_one)
        TextView child_author_one;
        @ViewInject(R.id.child_jump_one)
        TextView child_jump_one;
        @ViewInject(R.id.child_more_one)
        ImageView child_more_one;

        public ViewHolderChildOne(View itemView) {
            x.view().inject(this, itemView);
        }
    }


}

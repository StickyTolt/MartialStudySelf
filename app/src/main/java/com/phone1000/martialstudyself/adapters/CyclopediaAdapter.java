package com.phone1000.martialstudyself.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.interfaces.ICyclopediaMore;
import com.phone1000.martialstudyself.models.CyclopediaModel;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/11/29.
 */
public class CyclopediaAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private static final String TAG = CyclopediaAdapter.class.getSimpleName();
    private ImageOptions options;
    private List<CyclopediaModel> data;
    private LayoutInflater inflater;
    private List<Integer> childCount;
    private List<Integer> moreType;
    private ICyclopediaMore iCyclopediaMore;

    public void setiCyclopediaMore(ICyclopediaMore iCyclopediaMore) {
        this.iCyclopediaMore = iCyclopediaMore;
    }

    public CyclopediaAdapter(List<CyclopediaModel> data, Context context) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        childCount = new ArrayList<>();
        childCount.add(8);
        childCount.add(8);
        childCount.add(1);
        childCount.add(1);
        childCount.add(8);
        childCount.add(8);

        options = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.kungfu1)
                .setLoadingDrawableId(R.mipmap.kungfu6)
                .build();
        moreType = new ArrayList<>();
        moreType.add(11);
        moreType.add(11);
        moreType.add(11);
        moreType.add(11);
        moreType.add(11);
        moreType.add(11);
    }

    public void updateRes(List<CyclopediaModel> list){
        if (list != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }

    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        int type = 0;
        if (groupPosition != 2 && groupPosition != 3) {
            type = childCount.get(groupPosition) - 1 > childPosition ? 0 : 1;
        }else {
            type = 2;
        }
        return type;
    }

    @Override
    public int getChildTypeCount() {
        return 3;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childCount.get(groupPosition);
    }

    @Override
    public CyclopediaModel getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public CyclopediaModel.ListBean getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderParent holderParent = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cyclorpedia_parent, parent, false);
            holderParent = new ViewHolderParent(convertView);
            convertView.setTag(holderParent);
        } else {
            holderParent = (ViewHolderParent) convertView.getTag();
        }

        holderParent.title.setText(getGroup(groupPosition).getDesc());
        holderParent.more.setTag(data.get(groupPosition).getDesc());
        holderParent.more.setOnClickListener(this);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        switch (getChildType(groupPosition, childPosition)) {
            case 0:
                ViewHolderChild holderChild = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_cyclorpedia_child, parent, false);
                    holderChild = new ViewHolderChild(convertView);
                    convertView.setTag(holderChild);
                } else {
                    holderChild = ((ViewHolderChild) convertView.getTag());
                }

                CyclopediaModel.ListBean child = getChild(groupPosition, childPosition);
                holderChild.childCount.setText("  " + child.getInfo_read_count());
                holderChild.childQipao.setText("  " + child.getInfo_reply_count());
                holderChild.childTitle.setText(child.getInfo_title());
                String url = "http://www.wushu520.com/" + child.getInfo_img_path();
                x.image().bind(holderChild.childImage, url, options);

                break;
            case 1:
                ViewHolderOne holderOne = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_cyclopedia_more, parent, false);
                    holderOne = new ViewHolderOne(convertView);
                    convertView.setTag(holderOne);
                } else {
                    holderOne = (ViewHolderOne) convertView.getTag();
                }
                int infoId = getChild(groupPosition, childPosition).getInfo_id();
                holderOne.oneText.setTag(data.get(groupPosition).getDesc());
                holderOne.oneImage.setTag(groupPosition);

                holderOne.oneImage.setOnClickListener(this);
                holderOne.oneText.setOnClickListener(this);

                switch (moreType.get(groupPosition)) {
                    case 11:
                        holderOne.oneImage.setVisibility(View.VISIBLE);
                        break;
                }
                if (holderOne.oneImage.getVisibility() == View.GONE) {
                    holderOne.oneText.setVisibility(View.VISIBLE);
                } else {
                    holderOne.oneText.setVisibility(View.GONE);
                }
                break;
            case 2:
                convertView = inflater.inflate(R.layout.item_cyclopedia_two_child,parent,false);

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
            case R.id.item_cyclorpedia_parent_more:
            case R.id.item_cyclopedia_more_text:
                // 这里做接口跳转
                Log.e(TAG, "onClick: 这里是传输的数据，千万别错+++" + v.getTag());
                if (iCyclopediaMore != null) {
                    iCyclopediaMore.requestInfoId((String) v.getTag());
                }
                break;
            case R.id.item_cyclopedia_more_image:
                int tag = (int) v.getTag();
                childCount.remove(tag);
                childCount.add(tag, data.get(tag).getList().size());
                moreType.remove(tag);
                moreType.add(tag, 22);
                notifyDataSetChanged();
                v.setVisibility(View.GONE);
                break;
        }
    }



    private static class ViewHolderParent {
        @ViewInject(R.id.item_cyclorpedia_parent_title)
        TextView title;
        @ViewInject(R.id.item_cyclorpedia_parent_more)
        TextView more;

        public ViewHolderParent(View view) {
            x.view().inject(this, view);
        }
    }

    private static class ViewHolderChild {
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

        public ViewHolderChild(View view) {
            x.view().inject(this, view);
        }
    }

    private static class ViewHolderOne {
        @ViewInject(R.id.item_cyclopedia_more_image)
        ImageView oneImage;
        @ViewInject(R.id.item_cyclopedia_more_text)
        TextView oneText;

        public ViewHolderOne(View view) {
            x.view().inject(this, view);
        }
    }

}

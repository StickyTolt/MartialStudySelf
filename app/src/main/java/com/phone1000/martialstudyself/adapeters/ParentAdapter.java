package com.phone1000.martialstudyself.adapeters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.constants.MyUrl;
import com.phone1000.martialstudyself.interfaces.LoadingIsShown;
import com.phone1000.martialstudyself.interfaces.ParentItemClickListener;
import com.phone1000.martialstudyself.model.ParentModel;
import com.phone1000.martialstudyself.models.CommentModel;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/12/1.
 */
public class ParentAdapter extends BaseAdapter {

    private final ImageOptions options;
    private List<ParentModel.ListBean> data;
    private LayoutInflater inflater;
    private LoadingIsShown isShown;


    public void setIsShown(LoadingIsShown isShown) {
        this.isShown = isShown;
    }

    public ParentAdapter(Context context, List<ParentModel.ListBean> data) {
        inflater = LayoutInflater.from(context);
        if (data!=null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
        options = new ImageOptions.Builder().setFailureDrawableId(R.mipmap.kungfu2).build();
    }

    public void updateRes(List<ParentModel.ListBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            // 在最后添加一个空数据，用来制造尾部加载
            ParentModel.ListBean listBean = new ParentModel.ListBean();
            this.data.add(listBean);
            notifyDataSetChanged();
        }
    }

    public void addRes(List<ParentModel.ListBean> data) {
        if (data != null) {
            this.data.remove(data.size()-1);
            this.data.addAll(data);
            this.data.add(new ParentModel.ListBean());
            notifyDataSetChanged();
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position < data.size() - 1 ? 0 : 1;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public ParentModel.ListBean getItem(int position) {
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
                    convertView = inflater.inflate(R.layout.parent_item,parent,false);
                    holder =new ViewHolder(convertView);
                    convertView.setTag(holder);
                }else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.read.setText(getItem(position).getInfo_read_count() + "");
                holder.reply.setText(getItem(position).getInfo_reply_count() + "");
                holder.title.setText(getItem(position).getInfo_title() + "");
                x.image().bind(holder.image, MyUrl.HEADER_URL + getItem(position).getInfo_img_path(),options);

                break;
            case 1:
                ViewHolderOne holderOne = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.parent_item_load,parent,false);
                    holderOne =new ViewHolderOne(convertView);
                    convertView.setTag(holderOne);
                }else {
                    holderOne = (ViewHolderOne) convertView.getTag();
                }
                ObjectAnimator animator = ObjectAnimator.ofFloat(holderOne.image_one, "rotation", 0, 3600);
                animator.setDuration(4000);
                animator.start();
                if (isShown!=null) {
                    isShown.isShownLoad();
                }
                break;
        }

        return convertView;
    }

    public class ViewHolder{
        @ViewInject(R.id.one_title)
        TextView title;
        @ViewInject(R.id.one_reply_count)
        TextView reply;
        @ViewInject(R.id.one_read_count)
        TextView read;
        @ViewInject(R.id.one_image)
        ImageView image;
        public ViewHolder(View itemView) {
            x.view().inject(this,itemView);
        }
    }
    public class ViewHolderOne{
        @ViewInject(R.id.parent_image_load)
        ImageView image_one;
        public ViewHolderOne(View itemView) {
            x.view().inject(this,itemView);
        }
    }
}

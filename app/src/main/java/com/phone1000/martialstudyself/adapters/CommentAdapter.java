package com.phone1000.martialstudyself.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.martialstudyself.R;
import com.phone1000.martialstudyself.interfaces.ICommentItem;
import com.phone1000.martialstudyself.models.CommentModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/11/28.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> implements View.OnClickListener {


    private static final String TAG = CommentAdapter.class.getSimpleName();
    private List<CommentModel.ListBean> data ;
    private LayoutInflater inflater ;
    private RecyclerView mRecyclerView ;
    private ICommentItem commentItem ;

    public CommentAdapter(List<CommentModel.ListBean> data, Context context) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    public void setCommentItem(ICommentItem commentItem) {
        this.commentItem = commentItem;
    }

    public void updateRes(List<CommentModel.ListBean> list){
        if (list != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       CommentModel.ListBean listBean = data.get(position);
        holder.title.setText(listBean.getTopic_title());
        holder.name.setText(listBean.getTopic_add_user());
        holder.content.setText(listBean.getTopic_content_des());
        holder.time.setText(listBean.getTopic_add_time());
        holder.talkNum.setText(" "+ listBean.getTopic_reply_count());
        if (listBean.getTopic_top()==1 || listBean.getTopic_finer()==1) {
            holder.lefrImg.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {

        int position = mRecyclerView.getChildAdapterPosition(v);
        if (commentItem!=null) {
            commentItem.requestTopicId(data.get(position).getTopic_id());
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.item_comment_title)
        TextView title ;
        @ViewInject(R.id.item_comment_content)
        TextView content ;
        @ViewInject(R.id.item_comment_name)
        TextView name ;
        @ViewInject(R.id.item_comment_time)
        TextView time ;
        @ViewInject(R.id.item_comment_talk_num)
        TextView talkNum ;
        @ViewInject(R.id.item_comment_title_left_image)
        ImageView lefrImg ;

        public ViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);
        }
    }
}

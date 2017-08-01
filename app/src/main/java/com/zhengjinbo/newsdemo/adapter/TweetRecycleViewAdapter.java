package com.zhengjinbo.newsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.bean.TweetListBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by zhengjinbo on 17/7/30.
 */

public class TweetRecycleViewAdapter
        extends RecyclerView.Adapter<TweetRecycleViewAdapter.TweetViewHolder>
{
    private       Context        mContext;
    private final LayoutInflater mInflater;
    private List<TweetListBean.TweetlistBean> mList = new ArrayList<TweetListBean.TweetlistBean>();
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * 获取数据集合
     */
    public List<TweetListBean.TweetlistBean> getList() {
        return mList;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener itemClickLitener){
        this.mOnItemClickLitener = itemClickLitener;
    }

    public TweetRecycleViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }



    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TweetViewHolder tweetViewHolder = new TweetViewHolder(mInflater.inflate(getItemLayoutId(),
                                                                               parent,
                                                                               false));
        return tweetViewHolder;
    }

    @Override
    public void onBindViewHolder(final TweetViewHolder holder, int position) {
        TweetListBean.TweetlistBean tweetlistBean = mList.get(position);
        holder.mTvName.setText(tweetlistBean.getAuthor());
        holder.mTvContent.setText(Html.fromHtml(tweetlistBean.getBody()));
        holder.mTvPubDate.setText(tweetlistBean.getPubDate());
        Picasso.with(mContext).load(tweetlistBean.getPortrait()).into(holder.mCircleImg);
        int commentCount = tweetlistBean.getCommentCount();
        if (commentCount==0){
            holder.mTvComment.setText("评论");
        }else {
            holder.mTvComment.setText(commentCount + "");
        }


        //设置点击事件回调
        if (mOnItemClickLitener != null){
            //点击
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,layoutPosition);
                }
            });
            //长按点击事件
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView,layoutPosition);
                    return false;
                }
            });
        }

    }

    private int getItemLayoutId() {
        return R.layout.item_tweet_list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 添加全部集合进去
     * @param newslist
     */
    public void addList(List<TweetListBean.TweetlistBean> newslist) {
        mList.clear();
        mList.addAll(newslist);
        notifyDataSetChanged();
    }

    /**
     * 添加加载更多的集合
     * @param newslist
     */
    public void addMoreList(List<TweetListBean.TweetlistBean> newslist) {
        mList.addAll(newslist);
        notifyDataSetChanged();
    }

    class TweetViewHolder
            extends RecyclerView.ViewHolder
    {


        private  TextView mTvName;
        private  TextView mTvContent;
        private  TextView mTvPubDate;
        private  TextView mTvComment;
        private final GridView mGvImg;
        private final CircleImageView mCircleImg;

        public TweetViewHolder(View view) {
            super(view);
            mTvName = (TextView) view.findViewById(R.id.tv_name);
            mTvContent = (TextView) view.findViewById(R.id.tv_content);
            mTvPubDate = (TextView) view.findViewById(R.id.tv_pubDate);
            mTvComment = (TextView) view.findViewById(R.id.tv_comment);
            mGvImg = (GridView) view.findViewById(R.id.gv_img);
            mCircleImg = (CircleImageView) view.findViewById(R.id.circleImg);
        }
    }
}

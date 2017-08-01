package com.zhengjinbo.newsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.bean.NewsListBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zengxianghui900 on 17/7/30.
 */

public class NewsRecycleViewAdapter
        extends RecyclerView.Adapter<NewsRecycleViewAdapter.NewsViewHolder>
{
    private       Context        mContext;
    private final LayoutInflater mInflater;
    private List<NewsListBean.NewslistBean> mList = new ArrayList<NewsListBean.NewslistBean>();
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * 获取数据集合
     */
    public List<NewsListBean.NewslistBean> getList() {
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

    public NewsRecycleViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }



    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsViewHolder newsViewHolder = new NewsViewHolder(mInflater.inflate(getItemLayoutId(),
                                                                             parent,
                                                                             false));

        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        NewsListBean.NewslistBean newslistBean = mList.get(position);
        holder.mTvTitle.setText(newslistBean.getTitle());
        holder.mTvAuthor.setText(newslistBean.getAuthor());
        holder.mTvPublish.setText(newslistBean.getPubDate());
        holder.mTvComment.setText("评论: " + newslistBean.getCommentCount() );
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
        return R.layout.item_news_list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 添加全部集合进去
     * @param newslist
     */
    public void addList(List<NewsListBean.NewslistBean> newslist) {
        mList.clear();
        mList.addAll(newslist);
        notifyDataSetChanged();
    }

    /**
     * 添加加载更多的集合
     * @param newslist
     */
    public void addMoreList(List<NewsListBean.NewslistBean> newslist) {
        mList.addAll(newslist);
        notifyDataSetChanged();
    }

    class NewsViewHolder
            extends RecyclerView.ViewHolder
    {

        TextView mTvTitle;
        TextView mTvAuthor;
        TextView mTvPublish;
        TextView mTvComment;

        public NewsViewHolder(View view) {
            super(view);
            mTvTitle = (TextView) view.findViewById(R.id.tv_title);
            mTvAuthor = (TextView) view.findViewById(R.id.tv_author);
            mTvPublish = (TextView) view.findViewById(R.id.tv_publish);
            mTvComment = (TextView) view.findViewById(R.id.tv_comment);
        }
    }
}

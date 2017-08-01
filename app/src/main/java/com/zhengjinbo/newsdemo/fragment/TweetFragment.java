package com.zhengjinbo.newsdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhengjinbo.newsdemo.adapter.TweetRecycleViewAdapter;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.AppConstants;
import com.zhengjinbo.newsdemo.base.BaseFragment;
import com.zhengjinbo.newsdemo.bean.TweetListBean;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;
import com.zhengjinbo.newsdemo.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjinbo.
 */
public class TweetFragment
        extends BaseFragment
        implements TweetRecycleViewAdapter.OnItemClickLitener
{
    @BindView(R.id.tvTitle)
    TextView      mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar       mToolbar;
    @BindView(R.id.x_view)
    XRecyclerView mXView;
    //是否第一次加载数据,默认true
    private boolean isFirstRequest = true;
    //是否加载更多
    private boolean isLoadMore = false;
    //默认加载的页码
    private int mPage=1;
    private TweetRecycleViewAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_tweet;
    }

    @Override
    protected void initData() {
        initTitle();
        initAdatper();
        initRequestData(mPage);
    }

    private void initAdatper() {
        mAdapter = new TweetRecycleViewAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXView.setLayoutManager(layoutManager);
        mXView.setAdapter(mAdapter);
    }

    private void initRequestData(int page) {
        //显示加载对话框
        if (isFirstRequest) {
            showDialog();
        }
        //获取动弹列表
        NewsService        service      = HttpUtils.requestNetData(NewsService.BASE_URL, NewsService.class);
        Call<TweetListBean> tweetListCall = service.getTweetList(mPage, 20);
        tweetListCall.enqueue(new Callback<TweetListBean>() {
            @Override
            public void onResponse(Call<TweetListBean> call, Response<TweetListBean> response) {
                List<TweetListBean.TweetlistBean> tweetList = response.body()
                                                                      .getTweetlist();
                if (isLoadMore){
                    mAdapter.addMoreList(tweetList);
                    //加载更多完成
                    mXView.loadMoreComplete();
                    //重置标识
                    isLoadMore = false;
                }else {
                    mAdapter.addList(tweetList);
                    //刷新完成
                    mXView.refreshComplete();
                }
                if (isFirstRequest) {
                    //隐藏加载对话框
                    hideDialog();
                    isFirstRequest=false;
                }
            }

            @Override
            public void onFailure(Call<TweetListBean> call, Throwable t) {
                ToastUtils.showShortToast(getActivity(), t.getMessage());
                if (isFirstRequest) {
                    hideDialog();
                }
            }
        });

    }


    /**
     * 初始化标题
     */
    private void initTitle() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString(AppConstants.KEY_TWEET);
            mTvTitle.setText(title);
        }
    }

    @Override
    protected void initListener() {
        mXView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                initRequestData(mPage);         //加载第一页就好
            }

            @Override
            public void onLoadMore() {
                //上拉加载更多
                isLoadMore = true;
                mPage++;
                initRequestData(mPage);

            }
        });


        //mXView的条目点击事件
        mAdapter.setOnItemClickLitener(this);
    }

    //---------xlRecyclerView点击回调-------------------
    @Override
    public void onItemClick(View view, int position) {
        
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    //---------xlRecyclerView点击回调-------------------
}

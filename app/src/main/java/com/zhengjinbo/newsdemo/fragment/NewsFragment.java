package com.zhengjinbo.newsdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.activity.NewsDetailActivity;
import com.zhengjinbo.newsdemo.adapter.NewsRecycleViewAdapter;
import com.zhengjinbo.newsdemo.base.AppConstants;
import com.zhengjinbo.newsdemo.base.BaseFragment;
import com.zhengjinbo.newsdemo.bean.NewsListBean;
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
public class NewsFragment extends BaseFragment implements NewsRecycleViewAdapter.OnItemClickLitener {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.x_view)
    XRecyclerView mXView;
    //是否第一次加载数据,默认true
    private boolean isFirstRequest = true;
    //是否加载更多
    private boolean isLoadMore = false;
    //默认加载的页码
    private int mPage = 1;
    private NewsRecycleViewAdapter mAdapter;
    private String access_token ="";
    //是否第一次加载数据,默认true
    private boolean isFirstHidden = true;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            access_token = mActivity.access_token;
            if (isFirstHidden && !TextUtils.isEmpty(access_token)){
                isFirstHidden = false;
                initRequestData(mPage);
            }

        }


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {

        //初始化标题
        initTitle();
        //初始化Adapter
        initXViewAdapter();
        //请求网络数据
        initRequestData(mPage);
    }

    private void initRequestData(int page) {
        //显示加载对话框
        if (isFirstRequest) {
            showDialog();
        }

        //获取新闻分类
        NewsService service = HttpUtils.requestNetData(NewsService.BASE_URL, NewsService.class);
        Call<NewsListBean> newsListCall = service.getNewsList(mPage, 20,access_token);
        newsListCall.enqueue(new Callback<NewsListBean>() {
            @Override
            public void onResponse(Call<NewsListBean> call, Response<NewsListBean> response) {
                List<NewsListBean.NewslistBean> newsList = response.body()
                        .getNewslist();
                if (isLoadMore) {
                    mAdapter.addMoreList(newsList);
                    //加载更多完成
                    mXView.loadMoreComplete();
                    //重置标识
                    isLoadMore = false;
                } else {
                    mAdapter.addList(newsList);
                    //刷新完成
                    mXView.refreshComplete();
                }
                if (isFirstRequest) {
                    //隐藏加载对话框
                    hideDialog();
                    isFirstRequest = false;
                }
            }

            @Override
            public void onFailure(Call<NewsListBean> call, Throwable t) {
                ToastUtils.showShortToast(getActivity(), t.getMessage());
                if (isFirstRequest) {
                    hideDialog();
                }
            }
        });

    }

    private void initXViewAdapter() {
        mAdapter = new NewsRecycleViewAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXView.setLayoutManager(layoutManager);
        mXView.setAdapter(mAdapter);
    }

    private void initTitle() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String news = arguments.getString(AppConstants.KEY_NEWS);
            mTvTitle.setText(news);
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

    //----------------recyclerView条目点击事件回调 start---------------
    @Override
    public void onItemClick(View view, int position) {
        List<NewsListBean.NewslistBean> list = mAdapter.getList();
        NewsListBean.NewslistBean bean = list.get(position-1);
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(AppConstants.NEWS_DETAIL_ID_KEY, bean.getId());
        intent.putExtra(AppConstants.NEWS_DETAIL_COMMENT_KEY, bean.getCommentCount());
        intent.putExtra(AppConstants.ACCESS_TOKEN,access_token);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
    //----------------recyclerView条目点击事件回调 end---------------
}

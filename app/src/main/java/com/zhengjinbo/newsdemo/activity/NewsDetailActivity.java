package com.zhengjinbo.newsdemo.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.AppConstants;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.bean.NewsDetailBean;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;
import com.zhengjinbo.newsdemo.utils.ToastUtils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zengxianghui900 on 17/7/31.
 */
public class NewsDetailActivity
        extends BaseActivity
{
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tvTitle)
    TextView  mTvTitle;
    @BindView(R.id.tv_comment)
    TextView  mTvComment;
    @BindView(R.id.toolbar)
    Toolbar   mToolbar;
    @BindView(R.id.tv_newsTitle)
    TextView  mTvNewsTitle;
    @BindView(R.id.tv_newsAuthor)
    TextView  mTvNewsAuthor;
    @BindView(R.id.tv_newsPubDate)
    TextView  mTvNewsPubDate;
    @BindView(R.id.tv_newsContent)
    TextView  mTvNewsContent;
    private long mNewsId;
    private int mNewsComments;
    private String access_token;

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initData() {
        //获取传递过来的数据
        getIntentData();
        //初始化标题
        initTitle();
        //获取数据
        initRequestData();
    }

    /**
     * 获取数据
     */
    private void initRequestData() {
        showDialog();
        NewsService newsService = HttpUtils.requestNetData(NewsService.BASE_URL, NewsService.class);
        Call<NewsDetailBean> detailCall = newsService.getNewsDetail(mNewsId,access_token);
        detailCall.enqueue(new Callback<NewsDetailBean>() {
            @Override
            public void onResponse(Call<NewsDetailBean> call, Response<NewsDetailBean> response) {
                NewsDetailBean body = response.body();
                initPageData(body);
                hideDialog();
            }

            @Override
            public void onFailure(Call<NewsDetailBean> call, Throwable t) {
                ToastUtils.showShortToast(NewsDetailActivity.this,t.getMessage());
                hideDialog();
            }
        });

    }

    /**
     * 初始化页面数据
     * @param body
     */
    private void initPageData(NewsDetailBean body) {
        if (body != null) {
            mTvNewsTitle.setText(body.getTitle());
            mTvNewsAuthor.setText(body.getAuthor());
            mTvNewsPubDate.setText(body.getPubDate());
            mTvNewsContent.setText(Html.fromHtml(body.getBody()));
            mTvNewsContent.setClickable(true);
        }
    }

    /**
     * 获取传递过来的数据
     */
    private void getIntentData() {
        Intent intent = getIntent();
        if (intent!= null){
            mNewsId = intent.getIntExtra(AppConstants.NEWS_DETAIL_ID_KEY,0);
            mNewsComments = intent.getIntExtra(AppConstants.NEWS_DETAIL_COMMENT_KEY, 0);
            access_token=intent.getStringExtra(AppConstants.ACCESS_TOKEN);
        }
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        mTvTitle.setText(AppConstants.NEW_DETAIL_TITLE);
        mTvComment.setText(mNewsComments+"");

    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}

package com.zhengjinbo.newsdemo.http;

import com.zhengjinbo.newsdemo.bean.NewsDetailBean;
import com.zhengjinbo.newsdemo.bean.NewsListBean;
import com.zhengjinbo.newsdemo.bean.PersonInfoBean;
import com.zhengjinbo.newsdemo.bean.PortraitUpdateBean;
import com.zhengjinbo.newsdemo.bean.TokenBean;
import com.zhengjinbo.newsdemo.bean.TweetListBean;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zhengjinbo.
 */

public interface NewsService {
    String URL_REGISTER = "http://www.tngou.net/api/oauth2/reg/";
    String URL_PERSON_INFO = "http://www.oschina.net/action/openapi/user/";
    String BASE_URL = "http://www.oschina.net/action/openapi/";

    //修改头像
    String PORTTAIT_UPDATE_URL  ="http://www.oschina.net/action/openapi/portrait_update/";


    /**
     * 新闻列表
     */
    @GET("news_list")
    Call<NewsListBean> getNewsList(@Query("page") int page, @Query("pageSize") int pageSize, @Query("access_token") String access_token);


    /**
     * 新闻详情
     */
    @GET("news_detail")
    Call<NewsDetailBean> getNewsDetail(@Query("id") long id, @Query("access_token") String access_token);


    /**
     * 动弹列表
     */
    @GET("tweet_list")
    Call<TweetListBean> getTweetList(@Query("page") int page, @Query("pageSize") int pageSize, @Query("access_token") String access_token);


    //登陆授权
    @GET("news/login")
    Call<TokenBean> send(@QueryMap Map<String, String> map);

    //个人信息
    @GET("news/personInfo")
    Call<PersonInfoBean> getPersonInfo(@QueryMap Map<String, String> map);
    //头像更新
//    @POST("news/portrait_update")
//    Call<PortraitUpdateBean> updatePortrait(@Url String url, @Query("access_token") String access_token);

    @Multipart
    @POST("news/portrait_update")
    Call<PortraitUpdateBean> updatePortrait(@Query("access_token") String access_token,
                              @Part MultipartBody.Part file);


}

package com.zhengjinbo.newsdemo.http;

import com.zhengjinbo.newsdemo.VO.RegisterVO;
import com.zhengjinbo.newsdemo.bean.NewsClassifyBean;
import com.zhengjinbo.newsdemo.bean.NewsPaperBean;
import com.zhengjinbo.newsdemo.bean.RegisterBean;
import com.zhengjinbo.newsdemo.bean.TokenBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by zhengjinbo.
 */

public interface NewsService {
    String BASE_URL = "http://www.tngou.net/api/";
    String BASE_URL_TEST = "http://news-at.zhihu.com/api/4/";
    String URL_REGISTER = "http://www.tngou.net/api/oauth2/reg/";


    //注册
    @POST("news/register")
    Call<RegisterBean> send(@Body RegisterVO registerVO);

    //登陆
    @GET("news/login")
    Call<TokenBean> send(@QueryMap Map<String,String> map);


    //这个为测试
    @GET("news/latest")
    Call<NewsPaperBean> getNewsClassifyTest();

    /** 新闻分类 */
    @GET("top/classify")
    Call<NewsClassifyBean.TngouBean> getNewsClassify();

//    /** 最新新闻列表 要指定泛型*/
//    @GET("top/list")
//    Call<T> getNewsClassifyList(@Query("id") int id,@Query("page") int page,@Query("rows") int rows);
//
//     /** 最新新闻列表 要指定泛型*/
//    @GET("top/news")
//    Call<T> getNewsLastest(@Query("id") int id,@Query("rows") int rows);
//
//       /** 搜索新闻 要指定泛型*/
//    @GET("search")
//    Call<T> searchNews(@Query("keyword") String keyWord,@Query("name") String name,@Query("page") int page,@Query("rows") int rows);




}

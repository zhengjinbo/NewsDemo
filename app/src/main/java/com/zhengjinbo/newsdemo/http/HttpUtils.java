package com.zhengjinbo.newsdemo.http;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengjinbo.
 */
public class HttpUtils {

    public static <B> B requestNetData(String baseUrl,Class<B> clazz){
        Retrofit rtf = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return rtf.create(clazz);
    }

    private static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor())
                .addInterceptor(interceptor)
                .build();
    }

    static class CustomInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain)
                throws IOException
        {
            Request request = chain.request();
            HttpUrl httpUrl = request.url().newBuilder()
                              //       .addQueryParameter("access_token", "7ba23777-2dca-4e54-8acb-fcf7bb588d55")
                                     .addQueryParameter("dataType", "json")
                                     .build();
            request = request.newBuilder().url(httpUrl).build();
            return chain.proceed(request);
        }
    }

}

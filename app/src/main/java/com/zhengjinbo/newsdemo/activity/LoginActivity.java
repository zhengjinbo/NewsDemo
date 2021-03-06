package com.zhengjinbo.newsdemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.bean.TokenBean;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册授权登陆
 * Created by zhengjinbo.
 */

public class LoginActivity extends BaseActivity {
    private static final String CLIENT_ID = "BtZoBtnOjnUc3tPlkwXs";
    private static final String CLIENT_SECRET = "lMfgxMRZUqGItiEmsgTEddWgNTHqWk4R";
    private static final String REDIRECT_URL = "http://www.travelease.com.cn";
    private static final String ACCESS_TOKEN_URL = "http://www.oschina.net/action/openapi/token";
    private static final String AUTHORIZE_URL = "https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id=BtZoBtnOjnUc3tPlkwXs&redirect_uri=http://www.travelease.com.cn";
    private static Boolean isFrist = true;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.btn_back)
    TextView btn_back;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView webView;

    TokenBean tokenBean;



    @Override
    protected int getLayout() {
        return R.layout.activity_login;

    }

    @Override
    protected void initData() {
        mToolbar.setVisibility(View.GONE);

        String url = String.format(AUTHORIZE_URL, new Object[]{CLIENT_ID,
                REDIRECT_URL});

        webView.getSettings().setJavaScriptEnabled(true);
        // 触摸焦点起作用
        webView.requestFocus();
        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebViewClient());

    }

    @Override
    protected void initListener() {
    }


    /**
     * 解析URL
     *
     * @param url
     * @param map
     */
    private void parseUrl(String url, Map<String, String> map) {

        NewsService newsService = HttpUtils.requestNetData(url + "/", NewsService.class);
        Call<TokenBean> call = newsService.send(map);
        call.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                 tokenBean = response.body();


            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (tokenBean!= null) {
                //跳转到主界面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("tokenBean", tokenBean);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }else{
                //退出系统
                System.exit(0);
            }
            return true;
        }
        return false;
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            //     showDialog();
            String callback_url = REDIRECT_URL + "/?";
            if (url.startsWith(callback_url)) { //匹配callback_url
                if (isFrist) {
                    isFrist = false;
                    String[] stringsOne = url.split("&");
                    String[] stringsTwo = stringsOne[0].split("=");
                    String code = stringsTwo[1];
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("client_id", CLIENT_ID);
                    map.put("client_secret", CLIENT_SECRET);
                    map.put("grant_type", "authorization_code");
                    map.put("redirect_uri", REDIRECT_URL);
                    map.put("code", code);
                    map.put("dataType", "json");

                    parseUrl(ACCESS_TOKEN_URL, map);
                }
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //         hideDialog();
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}

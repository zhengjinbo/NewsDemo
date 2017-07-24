package com.zhengjinbo.newsdemo.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.VO.LoginVO;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.bean.LoginBean;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjinbo.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.btn_back)
    TextView btn_back;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPassWord)
    EditText etPassWord;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_register)
    TextView mTvRegister;

    String account, password;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;

    }

    @Override
    protected void initData() {
        mTvTitle.setText("登陆");
        btn_back.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initListener() {
        btn_login.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        btn_back.setOnClickListener(this);

    }

    //控件的点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_login:
                account = etUserName.getText().toString().trim();
                password = etPassWord.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(getApplicationContext(), "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginVO loginVO = new LoginVO();
                loginVO.setName(account);
                loginVO.setPassword(password);
                loginVO.setClient_id("9539058");
                loginVO.setClient_secret("0fc7ec71da9c30ff76d70a73dd8a32f2");

                //登陆请求操作
                loginRequest(loginVO);

                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void loginRequest(LoginVO loginVO) {

        showDialog();

        NewsService newsService = HttpUtils.requestNetData(NewsService.URL_LOGIN, NewsService.class);
        Call<LoginBean> call = newsService.send(loginVO);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                hideDialog();
                LoginBean loginBean = response.body();
                Log.e("zjb=登陆接口返回onResponse", "///" + new Gson().toJson(loginBean));
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                hideDialog();
                Log.e("zjb=登陆接口返回onFailure", t.toString());

            }
        });

    }
}

package com.zhengjinbo.newsdemo.activity;

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
import com.zhengjinbo.newsdemo.vo.RegisterVO;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.bean.RegisterBean;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjinbo.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_back)
    TextView btn_back;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPassWord)
    EditText etPassWord;
    @BindView(R.id.btn_register)
    Button btn_register;

    private String email, account, password;


    @Override
    protected int getLayout() {
        return R.layout.activity_register;

    }

    @Override
    protected void initData() {
        mTvTitle.setText("注册");
        btn_back.setVisibility(View.VISIBLE);

        //        etEmail.setText("zjbzheng@163.com");
        //        etUserName.setText("zhengjinbo");
        //        etPassWord.setText("zhengjinbo");


    }

    @Override
    protected void initListener() {
        btn_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    //控件的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_register:
                //注册
                //                client_id: 9539058;
                //                client_secret: 0fc7ec71da9c30ff76d70a73dd8a32f2.

                email = etEmail.getText().toString().trim();
                account = etUserName.getText().toString().trim();
                password = etPassWord.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(RegisterActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegisterVO registerVO = new RegisterVO();
                registerVO.setClient_id("9539058");
                registerVO.setClient_secret("0fc7ec71da9c30ff76d70a73dd8a32f2");
                registerVO.setAccount(account);
                registerVO.setPassword(password);
                registerVO.setEmail(email);

                initRequestData(registerVO);
                break;
        }
    }

    //注册接口
    private void initRequestData(RegisterVO registerVO) {
        showDialog();
        //注册账号
        NewsService service = HttpUtils.requestNetData(NewsService.URL_REGISTER, NewsService.class);
        Call<RegisterBean> call = service.send(registerVO);
        call.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                //模拟数据
                initFakeData();
                RegisterBean registerBean = response.body();
                Log.e("zjb注册接口返回onResponse", "//" + new Gson().toJson(registerBean));
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {
                //隐藏加载对话框
                hideDialog();
                Log.e("zjb注册接口返回onFailure", t.toString());
            }
        });


    }

    private void initFakeData() {
    }
}

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
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.bean.NewsClassifyBean;
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
                initRequestData();
                break;
        }
    }

    //注册接口
    private void initRequestData() {
        showDialog();
        //注册账号
        NewsService service = HttpUtils.requestNetData(NewsService.BASE_URL_TEST, NewsService.class);
        Call<NewsClassifyBean.TngouBean> classify = service.getNewsClassify();
        classify.enqueue(new Callback<NewsClassifyBean.TngouBean>() {
            @Override
            public void onResponse(Call<NewsClassifyBean.TngouBean> call, Response<NewsClassifyBean.TngouBean> response) {
                NewsClassifyBean.TngouBean body = response.body();
                Log.e("zjb注册接口返回", "//"+ new Gson().toJson(body));
                //模拟数据
                initFakeData();
                //隐藏加载对话框
                hideDialog();
            }



            @Override
            public void onFailure(Call<NewsClassifyBean.TngouBean> call, Throwable t) {

            }
        });
    }

    private void initFakeData() {
    }
}

package com.zhengjinbo.newsdemo.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import butterknife.BindView;

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
                Log.e("zjb_登陆","---------------");
                break;
            case R.id.tv_register:
                Log.e("zjb_注册","---------------");

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);


                break;
        }

    }
}

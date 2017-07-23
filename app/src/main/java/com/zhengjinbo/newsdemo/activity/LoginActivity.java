package com.zhengjinbo.newsdemo.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                //登陆请求操作
                loginRequest();

                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void loginRequest() {

    }
}

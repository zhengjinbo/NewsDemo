package com.zhengjinbo.newsdemo.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.bean.PersonInfoBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhengjinbo.
 * 个人信息
 */

public class PersonInfoActivity extends BaseActivity {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.tv_idname)
    TextView tv_idname;
    @BindView(R.id.tv_account)
    TextView tv_account;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.tv_url)
    TextView tv_url;

    private PersonInfoBean personInfoBean;


    @Override
    protected int getLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("个人信息");
        btn_back.setVisibility(View.VISIBLE);
        personInfoBean = (PersonInfoBean) getIntent().getSerializableExtra("personInfoBean");

        //展示个人信息
        setData(personInfoBean);
    }


    @Override
    protected void initListener() {
    }


    @OnClick(R.id.btn_back)
    public void back() {
        finish();
    }

    private void setData(PersonInfoBean personInfoBean) {
        tv_idname.setText(personInfoBean.getId() + "");
        tv_account.setText(personInfoBean.getName());
        if ("male".equals(personInfoBean.getGender())) {
            tv_gender.setText("女");
        } else {
            tv_gender.setText("男");
        }
        tv_email.setText(personInfoBean.getEmail());
        tv_location.setText(personInfoBean.getLocation());
        tv_url.setText(personInfoBean.getUrl());
    }

}

package com.zhengjinbo.newsdemo.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseActivity;
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




    @Override
    protected int getLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("个人信息");
        btn_back.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initListener() {
    }



    @OnClick(R.id.btn_back)
    public void back(){
        finish();
    }


}

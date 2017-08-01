package com.zhengjinbo.newsdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.activity.LoginActivity;
import com.zhengjinbo.newsdemo.activity.PersonInfoActivity;
import com.zhengjinbo.newsdemo.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by zhengjinbo
 */
public class MeFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.btn_personInfo)
    Button btn_personInfo;

    private  static final int REQUEST_CODE = 1;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode ==10086){
            Log.e("onActivityResult","---------------");
            String access_token = data.getStringExtra("access_token");
            Log.e("access_token","---------------"+access_token);
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString("me");
            mTvTitle.setText(title);
        }
    }




    @Override
    protected void initListener() {
        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到用户授权界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,REQUEST_CODE);

            }
        });

        btn_personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(intent);
            }
        });





    }



}

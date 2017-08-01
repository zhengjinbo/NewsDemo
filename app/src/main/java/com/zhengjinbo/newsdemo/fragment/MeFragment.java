package com.zhengjinbo.newsdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.activity.LoginActivity;
import com.zhengjinbo.newsdemo.activity.PersonInfoActivity;
import com.zhengjinbo.newsdemo.base.BaseFragment;
import com.zhengjinbo.newsdemo.bean.PersonInfoBean;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjinbo
 */
public class MeFragment extends BaseFragment {
    private static final int REQUEST_CODE = 1;
    private static final int INFO_CODE = 2;
    private static Boolean isLock = true;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_message)
    TextView mTvMessage;

    String access_token;
    PersonInfoBean personInfoBean;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == 10086) {
            access_token = data.getStringExtra("access_token");
            if (!TextUtils.isEmpty(access_token)) {
                mTvMessage.setText("点击查看个人信息");
                isLock = false;
                //获取个人信息
                requestPersonInfo();

            }
        }

    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        //        Bundle arguments = getArguments();
        //        if (arguments != null) {
        //            String title = arguments.getString("me");
        //            mTvTitle.setText(title);
        //        }

        mToolbar.setVisibility(View.GONE);

    }


    @Override
    protected void initListener() {
        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLock) {
                    //跳转到用户授权界面
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    //修改头像
                    Toast.makeText(getActivity(), "修改头像", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mTvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLock) {
                    //获取个人信息
                    Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("personInfoBean",personInfoBean);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, INFO_CODE);
                }

            }
        });


    }

    private void requestPersonInfo() {
        showDialog();
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", access_token);
        map.put("dataType", "json");

        NewsService newsService = HttpUtils.requestNetData(NewsService.URL_PERSON_INFO, NewsService.class);
        Call<PersonInfoBean> call = newsService.getPersonInfo(map);
        call.enqueue(new Callback<PersonInfoBean>() {
            @Override
            public void onResponse(Call<PersonInfoBean> call, Response<PersonInfoBean> response) {
                hideDialog();
                personInfoBean = response.body();
                Log.e("获取个人信息接口返回",new Gson().toJson(personInfoBean));

            }

            @Override
            public void onFailure(Call<PersonInfoBean> call, Throwable t) {
                hideDialog();
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }


}

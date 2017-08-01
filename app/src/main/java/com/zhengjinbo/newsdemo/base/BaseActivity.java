package com.zhengjinbo.newsdemo.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhengjinbo.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        mBind = ButterKnife.bind(this);
        initDialog();
        initData();

        initListener();

    }

    /**
     * 抽象方法，获取布局
     * @return
     */
    protected abstract int getLayout();


    /**
     * 抽象方法，初始化数据
     */
    protected abstract void initData();

    /**
     * 抽象方法，初始化监听事件
     */
    protected abstract void initListener();

    /**
     * 初始化数据加载dialog
     */
    private void initDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("数据加载中...");
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 显示数据加载对话框
     */
    protected void showDialog(){
        if (mProgressDialog!= null && !mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    /**
     * 隐藏数据加载对话框
     */
    protected void hideDialog(){
        if (mProgressDialog!= null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        if (mProgressDialog !=null) {
            mProgressDialog = null;
        }
    }
}

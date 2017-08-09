package com.zhengjinbo.newsdemo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.AppConstants;
import com.zhengjinbo.newsdemo.base.BaseActivity;
import com.zhengjinbo.newsdemo.base.FragmentCommon;
import com.zhengjinbo.newsdemo.bean.TokenBean;
import com.zhengjinbo.newsdemo.fragment.MeFragment;
import com.zhengjinbo.newsdemo.fragment.NewsFragment;
import com.zhengjinbo.newsdemo.fragment.TweetFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhengjinbo.
 * 主界面
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private static final int REQUEST_CODE = 100;
    @BindView(R.id.bottom_navigation_bar)
    public BottomNavigationBar mBottomNavigationBar;
    public String access_token = "";
    protected TokenBean tokenBean;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    private ArrayList<Fragment> mFragmentList;
    private long mExitTime;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        tokenBean = (TokenBean) getIntent().getSerializableExtra("tokenBean");
        access_token = tokenBean.getAccess_token();
        Log.e("access_token", access_token);

        initBottomNaviBar();
        initFragmentList();
        //动态注册权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

    }

    @Override
    protected void initListener() {
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权

                } else {
                    //用户拒绝授权
                }
                break;
        }
    }


    /**
     * 初始化底部选项卡
     */
    private void initBottomNaviBar() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_nav_news_actived,
                AppConstants.TAB_NEWS).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_nav_tweet_actived,
                        AppConstants.TAB_TWEET).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_nav_my_pressed,
                        AppConstants.TAB_ME).setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    /**
     * 初始化fragment
     */
    private void initFragmentList() {
        mFragmentList = new ArrayList<Fragment>();
        NewsFragment newsFragment = (NewsFragment) FragmentCommon.newInstance(AppConstants.TAB_NEWS);
        TweetFragment tweetFragment = (TweetFragment) FragmentCommon.newInstance(AppConstants.TAB_TWEET);

        MeFragment meFragment = (MeFragment) FragmentCommon.newInstance(AppConstants.TAB_ME);
        mFragmentList.add(newsFragment);
        mFragmentList.add(tweetFragment);
        mFragmentList.add(meFragment);
        setDefaultFragment();
    }

    /**
     * 设置默认的fragment
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, mFragmentList.get(0)).commitAllowingStateLoss();
    }


    @Override
    public void onTabSelected(int position) {
        if (mFragmentList != null) {
            if (position < mFragmentList.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment fragment = mFragmentList.get(position);
                if (fragment.isAdded()) {
                    transaction.show(fragment);
                } else {
                    transaction.add(R.id.fl_content, fragment);
                }
                transaction.commitAllowingStateLoss();
            }
            hideUnselected(position);
        }
    }

    /**
     * 当调用mBottomNavigationBar.selectTab(int index)时不会走onTabUnselected()方法，
     * 所以要在onTabSelected()中对未选中的进行隐藏
     *
     * @param position 选中的索引
     */
    private void hideUnselected(int position) {
        for (int i = 0; i < mFragmentList.size(); i++) {
            if (i != position) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment fragment = mFragmentList.get(i);
                if (fragment.isAdded() && !fragment.isHidden()) {
                    transaction.hide(fragment);
                    transaction.commitAllowingStateLoss();
                }
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (mFragmentList != null) {
            if (position < mFragmentList.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment fragment = mFragmentList.get(position);
                if (!fragment.isHidden()) {
                    transaction.hide(fragment);
                    transaction.commitAllowingStateLoss();
                }
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出系统",
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }


            return true;
        }
        return false;
    }


}

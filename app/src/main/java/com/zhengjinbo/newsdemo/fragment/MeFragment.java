package com.zhengjinbo.newsdemo.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.zhengjinbo.newsdemo.utils.ImageUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**我的
 * Created by zhengjinbo
 */
public class MeFragment extends BaseFragment {
    protected static final int CHOOSE_PICTURE = 0;
    private static final int CROP_SMALL_PICTURE = 2;
    private static final int REQUEST_CODE = 100;
    private static final int INFO_CODE = 200;
    protected static Uri tempUri;
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
        switch (requestCode) {
            case REQUEST_CODE:
                access_token = data.getStringExtra("access_token");
                if (!TextUtils.isEmpty(access_token)) {
                    mTvMessage.setText("点击查看个人信息");
                    isLock = false;
                    //获取个人信息
                    requestPersonInfo();

                }
                break;
            case CHOOSE_PICTURE:
                startPhotoZoom(data.getData());
                break;

            case CROP_SMALL_PICTURE:
                if (data != null) {
                    setImageToView(data);
                }
                break;

        }

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
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

                    Intent openAlbumIntent = new Intent(
                            Intent.ACTION_GET_CONTENT);
                    openAlbumIntent.setType("image/*");
                    startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
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
                    bundle.putSerializable("personInfoBean", personInfoBean);
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
                Log.e("获取个人信息接口返回", new Gson().toJson(personInfoBean));


            }

            @Override
            public void onFailure(Call<PersonInfoBean> call, Throwable t) {
                hideDialog();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = ImageUtil.toRoundBitmap(photo, tempUri);
            mIvAvatar.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {


        String imagePath = ImageUtil.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // ...
        }
    }

}

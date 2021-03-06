package com.zhengjinbo.newsdemo.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhengjinbo.newsdemo.R;
import com.zhengjinbo.newsdemo.base.BaseFragment;
import com.zhengjinbo.newsdemo.bean.PersonInfoBean;
import com.zhengjinbo.newsdemo.bean.PortraitUpdateBean;
import com.zhengjinbo.newsdemo.http.HttpUtils;
import com.zhengjinbo.newsdemo.http.NewsService;
import com.zhengjinbo.newsdemo.utils.ImageUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zhengjinbo.newsdemo.http.HttpUtils.requestNetData;

/**
 * 我的
 * Created by zhengjinbo.
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
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_message)
    TextView mTvMessage;

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


    String access_token = "";
    PersonInfoBean personInfoBean;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case CHOOSE_PICTURE:
                if (data != null) {
                    startPhotoZoom(data.getData());
                }
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
        access_token = mActivity.access_token;
        isLock = true;
        mToolbar.setVisibility(View.GONE);

        //获取个人信息
        requestPersonInfo();

    }

    @Override
    protected void initListener() {
        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLock) {
                    //跳转到用户授权界面
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    //修改头!像
                    Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    openAlbumIntent.setType("image/*");
                    startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                }

            }
        });




    }

    /**
     * 获取个人信息
     */
    private void requestPersonInfo() {
        showDialog();
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", access_token);
        map.put("dataType", "json");

        NewsService newsService = requestNetData(NewsService.URL_PERSON_INFO, NewsService.class);
        Call<PersonInfoBean> call = newsService.getPersonInfo(map);
        call.enqueue(new Callback<PersonInfoBean>() {
            @Override
            public void onResponse(Call<PersonInfoBean> call, Response<PersonInfoBean> response) {
                hideDialog();
                personInfoBean = response.body();
                Glide.with(mContext).load(personInfoBean.getAvatar()).into(mIvAvatar);
                Log.e("getAvatar()",personInfoBean.getAvatar()+"");
                mTvMessage.setText("点击修改头像");
                isLock = false;
                //展示个人信息
                setData(personInfoBean);
            }

            @Override
            public void onFailure(Call<PersonInfoBean> call, Throwable t) {
                hideDialog();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
        if (imagePath != null) {
            // ...上传头像
            NewsService newsService = HttpUtils.requestNetData(NewsService.PORTTAIT_UPDATE_URL, NewsService.class);
            File file = new File(imagePath);
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part partBody = MultipartBody.Part.createFormData("portrait", file.getName(), body);
            Call<PortraitUpdateBean> call = newsService.updatePortrait(access_token, partBody);
            call.enqueue(new Callback<PortraitUpdateBean>() {
                @Override
                public void onResponse(Call<PortraitUpdateBean> call, Response<PortraitUpdateBean> response) {
                    PortraitUpdateBean portraitUpdateBean = response.body();
                    Toast.makeText(mActivity, portraitUpdateBean.getError(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<PortraitUpdateBean> call, Throwable t) {
                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        }

    }

}

package com.zhengjinbo.newsdemo.bean;

import java.io.Serializable;

/**
 * Created by zhengjinbo.
 */

public class PersonInfoBean implements Serializable {

    //    {
    //        id: 899**,
    //        email: "****@gmail.com",
    //                name: "彭博",
    //            gender: "male",
    //            avatar: "http://www.oschina.net/uploads/user/****",
    //            location: "广东 深圳",
    //            url: "http://home.oschina.net/****"
    //    }
    //
    //    获取失败
    //    {
    //        error: "invalid_token",
    //                error_description: "Invalid access token: 7fade311-d844-4159-9890-c8f0511337e5"
    //    }


    private int id;
    private String email;
    private String name;
    private String gender;
    private String avatar;
    private String location;
    private String url;
    private String error;
    private String error_description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }


}

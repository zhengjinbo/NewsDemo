package com.zhengjinbo.newsdemo.vo;

import java.io.Serializable;

/**
 * Created by zhengjinbo.
 */

public class RegisterVO implements Serializable {
    //OAuth2 客户id
    private String client_id;
    //安全密文
    private String client_secret;
    //邮件
    private String email;
    //账号
    private String account;
    //密码
    private String password;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

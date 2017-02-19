package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class User extends Model<User> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户帐号")
    public static final String USER_ACCOUNT = "user_account";

    @Column(type = ColumnType.VARCHAR, length = 11, comment = "用户电话")
    public static final String USER_PHONE = "user_phone";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户邮箱")
    public static final String USER_EMAIL = "user_email";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户密码")
    public static final String USER_PASSWORD = "user_password";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "微博uid")
    public static final String WEIBO_UID = "weibo_uid";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "微博token")
    public static final String WEIBO_ACCESS_TOKEN = "weibo_access_token";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "微信uid")
    public static final String WECHAT_UID = "wechat_uid";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "微信token")
    public static final String WECHAT_ACCESS_TOKEN = "wechat_access_token";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "外键编号", updatable = false)
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户类型", updatable = false)
    public static final String USER_TYPE = "user_type";

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getUser_account() {
        return getStr(USER_ACCOUNT);
    }

    public void setUser_account(String user_account) {
        set(USER_ACCOUNT, user_account);
    }

    public String getUser_phone() {
        return getStr(USER_PHONE);
    }

    public void setUser_phone(String user_phone) {
        set(USER_PHONE, user_phone);
    }

    public String getUser_email() {
        return getStr(USER_EMAIL);
    }

    public void setUser_email(String user_email) {
        set(USER_EMAIL, user_email);
    }

    public String getUser_password() {
        return getStr(USER_PASSWORD);
    }

    public void setUser_password(String user_password) {
        set(USER_PASSWORD, user_password);
    }

    public String getWeibo_uid() {
        return getStr(WEIBO_UID);
    }

    public void setWeibo_uid(String weibo_uid) {
        set(WEIBO_UID, weibo_uid);
    }

    public String getWeibo_access_token() {
        return getStr(WEIBO_ACCESS_TOKEN);
    }

    public void setWeibo_access_token(String weibo_access_token) {
        set(WEIBO_ACCESS_TOKEN, weibo_access_token);
    }

    public String getWechat_uid() {
        return getStr(WECHAT_UID);
    }

    public void setWechat_uid(String wechat_uid) {
        set(WECHAT_UID, wechat_uid);
    }

    public String getWechat_access_token() {
        return getStr(WECHAT_ACCESS_TOKEN);
    }

    public void setWechat_access_token(String wechat_access_token) {
        set(WECHAT_ACCESS_TOKEN, wechat_access_token);
    }

    public String getObject_id() {
        return getStr(OBJECT_ID);
    }

    public void setObject_id(String object_id) {
        set(OBJECT_ID, object_id);
    }

    public String getUser_type() {
        return getStr(USER_TYPE);
    }

    public void setUser_type(String user_type) {
        set(USER_TYPE, user_type);
    }

}
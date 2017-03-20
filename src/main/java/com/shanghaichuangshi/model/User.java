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

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户名称")
    public static final String USER_NAME = "user_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "用户头像")
    public static final String USER_AVATAR = "user_avatar";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "用户头像")
    public static final String USER_AVATAR_THUMBNAIL = "user_avatar_thumbnail";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "用户头像")
    public static final String USER_AVATAR_ORIGINAL = "user_avatar_original";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "微信OpenId")
    public static final String WECHAT_OPEN_ID = "wechat_open_id";

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

    public String getUser_name() {
        return getStr(USER_NAME);
    }

    public void setUser_name(String user_name) {
        set(USER_NAME, user_name);
    }

    public String getUser_avatar() {
        return getStr(USER_AVATAR);
    }

    public void setUser_avatar(String user_avatar) {
        set(USER_AVATAR, user_avatar);
    }

    public String getUser_avatar_thumbnail() {
        return getStr(USER_AVATAR_THUMBNAIL);
    }

    public void setUser_avatar_thumbnail(String user_avatar_thumbnail) {
        set(USER_AVATAR_THUMBNAIL, user_avatar_thumbnail);
    }

    public String getUser_avatar_original() {
        return getStr(USER_AVATAR_ORIGINAL);
    }

    public void setUser_avatar_original(String user_avatar_original) {
        set(USER_AVATAR_ORIGINAL, user_avatar_original);
    }

    public String getWechat_open_id() {
        return getStr(WECHAT_OPEN_ID);
    }

    public void setWechat_open_id(String wechat_open_id) {
        set(WECHAT_OPEN_ID, wechat_open_id);
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
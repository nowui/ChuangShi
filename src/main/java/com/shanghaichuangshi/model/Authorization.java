package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnTypeEnum;
import com.shanghaichuangshi.util.DateUtil;

import java.util.Date;

public class Authorization extends Model<Authorization> {

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "授权编号")
    public static final String AUTHORIZATION_ID = "authorization_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 350, comment = "授权token")
    public static final String AUTHORIZATION_TOKEN = "authorization_token";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 10, comment = "授权平台")
    public static final String AUTHORIZATION_PLATFORM = "authorization_platform";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 10, comment = "授权版本")
    public static final String AUTHORIZATION_VERSION = "authorization_version";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 20, comment = "授权ip地址")
    public static final String AUTHORIZATION_IP_ADDRESS = "authorization_ip_address";

    @Column(type = ColumnTypeEnum.DATETIME, length = 0, comment = "授权创建时间")
    public static final String AUTHORIZATION_CREATE_TIME = "authorization_create_time";

    @Column(type = ColumnTypeEnum.DATETIME, length = 0, comment = "授权失效时间")
    public static final String AUTHORIZATION_EXPIRE_TIME = "authorization_expire_time";

    public String getAuthorization_id() {
        return getStr(AUTHORIZATION_ID);
    }

    public void setAuthorization_id(String authorization_id) {
        set(AUTHORIZATION_ID, authorization_id);
    }

    public String getAuthorization_token() {
        return getStr(AUTHORIZATION_TOKEN);
    }

    public void setAuthorization_token(String authorization_token) {
        set(AUTHORIZATION_TOKEN, authorization_token);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getAuthorization_platform() {
        return getStr(AUTHORIZATION_PLATFORM);
    }

    public void setAuthorization_platform(String authorization_platform) {
        set(AUTHORIZATION_PLATFORM, authorization_platform);
    }

    public String getAuthorization_version() {
        return getStr(AUTHORIZATION_VERSION);
    }

    public void setAuthorization_version(String authorization_version) {
        set(AUTHORIZATION_VERSION, authorization_version);
    }

    public String getAuthorization_ip_address() {
        return getStr(AUTHORIZATION_IP_ADDRESS);
    }

    public void setAuthorization_ip_address(String authorization_ip_address) {
        set(AUTHORIZATION_IP_ADDRESS, authorization_ip_address);
    }

    public String getAuthorization_create_time() {
        return DateUtil.getDateTimeString(getDate(AUTHORIZATION_CREATE_TIME));
    }

    public void setAuthorization_create_time(Date authorization_create_time) {
        set(AUTHORIZATION_CREATE_TIME, authorization_create_time);
    }

    public String getAuthorization_expire_time() {
        return DateUtil.getDateTimeString(getDate(AUTHORIZATION_EXPIRE_TIME));
    }

    public void setAuthorization_expire_time(Date authorization_expire_time) {
        set(AUTHORIZATION_EXPIRE_TIME, authorization_expire_time);
    }

}
package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColumnType;
import com.shanghaichuangshi.util.DateUtil;

import java.util.Date;

public class Authorization extends Model<Authorization> {

    @Table()
    public static final String TABLE_AUTHORIZATION = "table_authorization";

    @Id
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权编号")
    public static final String AUTHORIZATION_ID = "authorization_id";

    @Column(type = ColumnType.VARCHAR, length = 300, comment = "授权token")
    public static final String AUTHORIZATION_TOKEN = "authorization_token";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "授权创建时间")
    public static final String AUTHORIZATION_CREATE_TIME = "authorization_create_time";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "授权失效时间")
    public static final String AUTHORIZATION_EXPIRE_TIME = "authorization_expire_time";

    public String getAuthorization_id() {
        return getString(AUTHORIZATION_ID);
    }

    public void setAuthorization_id(String authorization_id) {
        set(AUTHORIZATION_ID, authorization_id);
    }

    public String getAuthorization_token() {
        return getString(AUTHORIZATION_TOKEN);
    }

    public void setAuthorization_token(String authorization_token) {
        set(AUTHORIZATION_TOKEN, authorization_token);
    }

    public String getUser_id() {
        return getString(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
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
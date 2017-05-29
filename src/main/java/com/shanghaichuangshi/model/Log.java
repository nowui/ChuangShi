package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class Log extends Model<Log> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "日志编号")
    public static final String LOG_ID = "log_id";

    @Column(type = ColumnType.VARCHAR, length = 255, comment = "日志请求地址")
    public static final String LOG_URL = "log_url";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "日志请求参数")
    public static final String LOG_REQUEST = "log_request";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "日志请求返回")
    public static final String LOG_RESPONSE = "log_response";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权编号")
    public static final String AUTHORIZATION_ID = "authorization_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 3, comment = "日志状态码")
    public static final String LOG_CODE = "log_code";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "日志请求平台")
    public static final String LOG_PLATFORM = "log_platform";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "日志请求版本")
    public static final String LOG_VERSION = "log_version";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "日志请求ip地址")
    public static final String LOG_IP_ADDRESS = "log_ip_address";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "日志请求响应时间")
    public static final String LOG_RUN_TIME = "log_run_time";

    public String getLog_id() {
        return getStr(LOG_ID);
    }

    public void setLog_id(String log_id) {
        set(LOG_ID, log_id);
    }

    public String getLog_url() {
        return getStr(LOG_URL);
    }

    public void setLog_url(String log_url) {
        set(LOG_URL, log_url);
    }

    public String getLog_request() {
        return getStr(LOG_REQUEST);
    }

    public void setLog_request(String log_request) {
        set(LOG_REQUEST, log_request);
    }

    public String getLog_response() {
        return getStr(LOG_RESPONSE);
    }

    public void setLog_response(String log_response) {
        set(LOG_RESPONSE, log_response);
    }

    public String getAuthorization_id() {
        return getStr(AUTHORIZATION_ID);
    }

    public void setAuthorization_id(String authorization_id) {
        set(AUTHORIZATION_ID, authorization_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getLog_code() {
        return getStr(LOG_CODE);
    }

    public void setLog_code(String log_code) {
        set(LOG_CODE, log_code);
    }

    public String getLog_platform() {
        return getStr(LOG_PLATFORM);
    }

    public void setLog_platform(String log_platform) {
        set(LOG_PLATFORM, log_platform);
    }

    public String getLog_version() {
        return getStr(LOG_VERSION);
    }

    public void setLog_version(String log_version) {
        set(LOG_VERSION, log_version);
    }

    public String getLog_ip_address() {
        return getStr(LOG_IP_ADDRESS);
    }

    public void setLog_ip_address(String log_ip_address) {
        set(LOG_IP_ADDRESS, log_ip_address);
    }

    public String getLog_run_time() {
        return getStr(LOG_RUN_TIME);
    }

    public void setLog_run_time(String log_run_time) {
        set(LOG_RUN_TIME, log_run_time);
    }

}
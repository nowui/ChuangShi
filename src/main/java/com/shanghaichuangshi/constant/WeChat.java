package com.shanghaichuangshi.constant;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;

public class WeChat {

    public static String app_id = "";
    public static String app_secret = "";
    public static String token = "";
    public static String rncoding_aes_key = "";
    public static String mch_id = "";
    public static String mch_key = "";
    public static String body = "";
    public static String redirect_uri = "";
    public static String notify_url = "";
    public static String wx_app_id = "";
    public static String wx_app_secret = "";
    public static String wx_mch_id = "";
    public static String wx_mch_key = "";
    public static int commission_level = 0;

    static {
        PropKit.clear();
        PropKit.use("WeChat.properties");

        app_id = PropKit.get("app_id");
        app_secret = PropKit.get("app_secret");
        token = PropKit.get("token");
        rncoding_aes_key = PropKit.get("rncoding_aes_key");
        mch_id = PropKit.get("mch_id");
        mch_key = PropKit.get("mch_key");
        body = PropKit.get("body");
        redirect_uri = PropKit.get("redirect_uri");
        notify_url = PropKit.get("notify_url");
        wx_app_id = PropKit.get("wx_app_id");
        wx_app_secret = PropKit.get("wx_app_secret");
        wx_mch_id = PropKit.get("wx_mch_id");
        wx_mch_key = PropKit.get("wx_mch_key");
        commission_level = PropKit.getInt("commission_level");
    }

    public static ApiConfig getApiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAppId(app_id);
        apiConfig.setAppSecret(app_secret);
        apiConfig.setToken(token);
        apiConfig.setEncryptMessage(false);
        apiConfig.setEncodingAesKey(rncoding_aes_key);

        return apiConfig;
    }

}

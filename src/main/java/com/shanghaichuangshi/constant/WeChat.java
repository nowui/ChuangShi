package com.shanghaichuangshi.constant;

import com.jfinal.kit.PropKit;

public class WeChat {

    public static String app_id = "";
    public static String app_secret = "";
    public static String token = "";
    public static String rncoding_aes_key = "";
    public static String mch_id = "";
    public static String mch_key = "";

    static {
        PropKit.clear();
        PropKit.use("WeChat.properties");

        app_id = PropKit.get("app_id");
        app_secret = PropKit.get("app_secret");
        token = PropKit.get("token");
        rncoding_aes_key = PropKit.get("rncoding_aes_key");
        mch_id = PropKit.get("mch_id");
        mch_key = PropKit.get("mch_key");
    }

}

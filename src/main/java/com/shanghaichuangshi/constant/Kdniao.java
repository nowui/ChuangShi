package com.shanghaichuangshi.constant;

import com.jfinal.kit.PropKit;

public class Kdniao {

    public static String EBusinessID = "";
    public static String AppKey = "";
    public static String ReqURL = "";

    static {
        PropKit.clear();
        PropKit.use("Kdniao.properties");

        EBusinessID = PropKit.get("EBusinessID");
        AppKey = PropKit.get("AppKey");
        ReqURL = PropKit.get("ReqURL");
    }

}

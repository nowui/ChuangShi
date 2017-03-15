package com.shanghaichuangshi.constant;

import com.jfinal.kit.PropKit;

public class Jdbc {

    public static String driver_class = "";
    public static String jdbc_url = "";
    public static String user = "";
    public static String password = "";
    public static Integer initial_size = 0;
    public static Integer min_idle = 0;
    public static Integer max_activee = 0;

    static {
        PropKit.clear();
        PropKit.use("Jdbc.properties");

        driver_class = PropKit.get("driver_class");
        jdbc_url = PropKit.get("jdbc_url");
        user = PropKit.get("user");
        password = PropKit.get("password");
        initial_size = Integer.valueOf(PropKit.get("initial_size"));
        min_idle = Integer.valueOf(PropKit.get("min_idle"));
        max_activee = Integer.valueOf(PropKit.get("max_activee"));
    }

}

package com.shanghaichuangshi;

import com.shanghaichuangshi.config.Config;

import com.shanghaichuangshi.config.Mysql;
import com.shanghaichuangshi.controller.ApplicationController;
import com.shanghaichuangshi.route.RouteMatcher;

public class WebConfig extends Config {

    public void configMysql(Mysql mysql) {
        mysql.setDriverClass("com.mysql.jdbc.Driver");
        mysql.setUrl("jdbc:mysql://localhost:3306/HongLuoMeng?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
        mysql.setUsername("root");
        mysql.setPassword("shengli.2010");
    }

    public void configRouteMatcher(RouteMatcher routeMatcher) {
        routeMatcher.add("/app", ApplicationController.class);
    }
}

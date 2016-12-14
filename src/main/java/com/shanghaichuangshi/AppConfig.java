package com.shanghaichuangshi;

import com.shanghaichuangshi.config.Config;

import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.controller.ApplicationController;
import com.shanghaichuangshi.route.RouteMatcher;

public class AppConfig extends Config {

    public void configConstant(Constant constant) {
        constant.setDriverClass("com.mysql.jdbc.Driver");
        constant.setUrl("jdbc:mysql://localhost:3306/HongLuoMeng?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
        constant.setUsername("root");
        constant.setPassword("shengli.2010");
    }

    public void configRouteMatcher(RouteMatcher routeMatcher) {
        routeMatcher.add("/app", ApplicationController.class);
    }
}

package com.shanghaichuangshi;

import com.shanghaichuangshi.config.Config;

import com.shanghaichuangshi.controller.ApplicationController;
import com.shanghaichuangshi.route.RouteMatcher;

public class AppConfig extends Config {

//    public void configConstant(Constant constant) {
//
//    }

    public void configRouteMatcher(RouteMatcher routeMatcher) {
        routeMatcher.add("/app", ApplicationController.class);
    }
}

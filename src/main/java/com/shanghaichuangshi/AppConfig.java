package com.shanghaichuangshi;

import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.route.Route;

import com.shanghaichuangshi.controller.ApplicationController;

public class AppConfig extends Config {

//    public void configConstant(Constant constant) {
//
//    }

    public void configRoute(Route route) {
        route.add("/app", ApplicationController.class);
    }
}

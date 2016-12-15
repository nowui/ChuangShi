package com.shanghaichuangshi.config;

import com.shanghaichuangshi.route.RouteMatcher;

public abstract class Config {

    public abstract void configMysql(Mysql mysql);

    public abstract void configRouteMatcher(RouteMatcher routeMatcher);

}

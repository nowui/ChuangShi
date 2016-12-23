package com.shanghaichuangshi.config;

import com.shanghaichuangshi.route.RouteMatcher;

import java.util.List;

public abstract class Config {

    public abstract void configCertificate(Certificate certificate);

    public abstract void configMysql(Mysql mysql);

    public abstract void configRouteMatcher(RouteMatcher routeMatcher);

    public abstract void configUncheckTokenUrl(List<String> uncheckTokenUrlList);

    public abstract void configUncheckRequestUrl(List<String> uncheckRequestUrlList);

}

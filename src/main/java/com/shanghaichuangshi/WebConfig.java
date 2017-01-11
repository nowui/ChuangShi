package com.shanghaichuangshi;

import com.shanghaichuangshi.config.Certificate;
import com.shanghaichuangshi.config.Config;

import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.controller.AdminController;
import com.shanghaichuangshi.controller.CategoryController;
import com.shanghaichuangshi.controller.CodeController;
import com.shanghaichuangshi.controller.RoleController;
import com.shanghaichuangshi.route.RouteMatcher;

import java.util.List;

public class WebConfig extends Config {

    public void configCertificate(Certificate certificate) {
        certificate.setPublic_key("");
        certificate.setPrivate_key("");
    }

    public void configRouteMatcher(RouteMatcher routeMatcher) {
        routeMatcher.add("/code", CodeController.class);
        routeMatcher.add("/role", RoleController.class);
        routeMatcher.add("/category", CategoryController.class);
        routeMatcher.add("/admin", AdminController.class);
    }

    public void configUncheckTokenUrl(List<String> uncheckTokenUrlList) {
        uncheckTokenUrlList.add(Url.MEMBER_LOGIN);
    }

    public void configUncheckRequestUrl(List<String> uncheckRequestUrlList) {

    }

}
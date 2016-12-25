package com.shanghaichuangshi;

import com.shanghaichuangshi.config.Certificate;
import com.shanghaichuangshi.config.Config;

import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.controller.ApplicationController;
import com.shanghaichuangshi.route.RouteMatcher;

import java.util.List;

public class WebConfig extends Config {

    public void configCertificate(Certificate certificate) {
        certificate.setPublic_key("");
        certificate.setPrivate_key("");
    }

    public void configRouteMatcher(RouteMatcher routeMatcher) {
        routeMatcher.add("/app", ApplicationController.class);
    }

    public void configUncheckTokenUrl(List<String> uncheckTokenUrlList) {
        uncheckTokenUrlList.add(Url.MEMBER_LOGIN);
    }

    public void configUncheckRequestUrl(List<String> uncheckRequestUrlList) {

    }

}

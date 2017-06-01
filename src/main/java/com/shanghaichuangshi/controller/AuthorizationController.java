package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.service.AuthorizationService;

import java.util.List;

public class AuthorizationController extends Controller {

    private final AuthorizationService authorizationService = new AuthorizationService();

    @ActionKey(Url.AUTHORIZATION_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Authorization model = getParameter(Authorization.class);

        model.validate(Authorization.AUTHORIZATION_TOKEN);

        int count = authorizationService.count(model.getAuthorization_token());

        List<Authorization> authorizationList = authorizationService.list(model.getAuthorization_token(), getM(), getN());

        renderSuccessJson(count, authorizationList);
    }

    @ActionKey(Url.AUTHORIZATION_ADMIN_FIND)
    public void adminFind() {
        Authorization model = getParameter(Authorization.class);

        model.validate(Authorization.AUTHORIZATION_ID);

        Authorization authorization = authorizationService.find(model.getAuthorization_id());

        renderSuccessJson(authorization.removeSystemInfo());
    }

}
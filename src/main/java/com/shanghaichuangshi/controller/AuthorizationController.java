package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.service.AuthorizationService;

import java.util.List;

public class AuthorizationController extends Controller {

    private static final AuthorizationService AUTHORIZATIONService = new AuthorizationService();

    @ActionKey(Url.AUTHORIZATION_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Authorization model = getParameter(Authorization.class);

        model.validate(Authorization.AUTHORIZATION_TOKEN);

        List<Authorization> AUTHORIZATIONList = AUTHORIZATIONService.list(model, getM(), getN());

        renderSuccessJson(AUTHORIZATIONList);
    }

    @ActionKey(Url.AUTHORIZATION_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Authorization model = getParameter(Authorization.class);

        model.validate(Authorization.AUTHORIZATION_TOKEN);

        int count = AUTHORIZATIONService.count(model);

        List<Authorization> AUTHORIZATIONList = AUTHORIZATIONService.list(model, getM(), getN());

        renderSuccessJson(count, AUTHORIZATIONList);
    }

    @ActionKey(Url.AUTHORIZATION_FIND)
    public void find() {
        Authorization model = getParameter(Authorization.class);

        model.validate(Authorization.AUTHORIZATION_ID);

        Authorization AUTHORIZATION = AUTHORIZATIONService.find(model);

        AUTHORIZATION.removeUnfindable();

        renderSuccessJson(AUTHORIZATION);
    }

    @ActionKey(Url.AUTHORIZATION_ADMIN_FIND)
    public void adminFind() {
        Authorization model = getParameter(Authorization.class);

        model.validate(Authorization.AUTHORIZATION_ID);

        Authorization AUTHORIZATION = AUTHORIZATIONService.find(model);

        renderSuccessJson(AUTHORIZATION);
    }

}
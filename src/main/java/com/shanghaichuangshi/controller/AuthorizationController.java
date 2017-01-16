package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.service.AuthorizationService;

import java.util.List;

public class AuthorizationController extends Controller {

    private final AuthorizationService authorizationService = new AuthorizationService();

    @Path(Url.AUTHORIZATION_LIST)
    public void list() {
        Authorization authorizationModel = getModel(Authorization.class);

        authorizationModel.validate(Authorization.PAGE_INDEX, Authorization.PAGE_SIZE);

        List<Authorization> authorizationList = authorizationService.list(authorizationModel);

        renderJson(authorizationList);
    }

    @Path(Url.AUTHORIZATION_ADMIN_LIST)
    public void adminList() {
        Authorization authorizationModel = getModel(Authorization.class);

        authorizationModel.validate(Authorization.AUTHORIZATION_TOKEN, Authorization.PAGE_INDEX, Authorization.PAGE_SIZE);

        int count = authorizationService.count(authorizationModel);

        List<Authorization> authorizationList = authorizationService.list(authorizationModel);

        renderJson(count, authorizationList);
    }

    @Path(Url.AUTHORIZATION_FIND)
    public void find() {
        Authorization authorizationModel = getModel(Authorization.class);

        authorizationModel.validate(Authorization.AUTHORIZATION_ID);

        Authorization authorization = authorizationService.find(authorizationModel);

        authorization.removeUnfindable();

        renderJson(authorization);
    }

    @Path(Url.AUTHORIZATION_ADMIN_FIND)
    public void adminFind() {
        Authorization authorizationModel = getModel(Authorization.class);

        authorizationModel.validate(Authorization.AUTHORIZATION_ID);

        Authorization authorization = authorizationService.find(authorizationModel);

        renderJson(authorization);
    }

}
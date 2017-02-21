package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.service.RoleService;

import java.util.List;

public class RoleController extends Controller {

    private static final RoleService ROLEService = new RoleService();

    @ActionKey(Url.ROLE_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_NAME);

        List<Role> ROLEList = ROLEService.list(model, getM(), getN());

        renderSuccessJson(ROLEList);
    }

    @ActionKey(Url.ROLE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_NAME);

        int count = ROLEService.count(model);

        List<Role> ROLEList = ROLEService.list(model, getM(), getN());

        renderSuccessJson(count, ROLEList);
    }

    @ActionKey(Url.ROLE_FIND)
    public void find() {
        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_ID);

        Role ROLE = ROLEService.find(model);

        ROLE.removeUnfindable();

        renderSuccessJson(ROLE);
    }

    @ActionKey(Url.ROLE_ADMIN_FIND)
    public void adminFind() {
        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_ID);

        Role ROLE = ROLEService.find(model);

        renderSuccessJson(ROLE);
    }

    @ActionKey(Url.ROLE_SAVE)
    public void save() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_NAME);

        ROLEService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ROLEL_UPDATE)
    public void update() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_ID, Role.ROLE_NAME);

        ROLEService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ROLE_DELETE)
    public void delete() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_ID);

        ROLEService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
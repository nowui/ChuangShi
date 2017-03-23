package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.service.RoleService;

import java.util.List;

public class RoleController extends Controller {

    private final RoleService roleService = new RoleService();

    @ActionKey(Url.ROLE_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_NAME);

        List<Role> roleList = roleService.list(model, getM(), getN());

        renderSuccessJson(roleList);
    }

    @ActionKey(Url.ROLE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_NAME);

        int count = roleService.count(model);

        List<Role> roleList = roleService.list(model, getM(), getN());

        renderSuccessJson(count, roleList);
    }

    @ActionKey(Url.ROLE_FIND)
    public void find() {
        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_ID);

        Role role = roleService.find(model.getRole_id());

        renderSuccessJson(role.format());
    }

    @ActionKey(Url.ROLE_ADMIN_FIND)
    public void adminFind() {
        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_ID);

        Role role = roleService.find(model.getRole_id());

        renderSuccessJson(role);
    }

    @ActionKey(Url.ROLE_SAVE)
    public void save() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_NAME);

        roleService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ROLEL_UPDATE)
    public void update() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_ID, Role.ROLE_NAME);

        roleService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ROLE_DELETE)
    public void delete() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_ID);

        roleService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
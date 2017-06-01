package com.shanghaichuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.service.RoleService;

import java.util.List;
import java.util.Map;

public class RoleController extends Controller {

    private final RoleService roleService = new RoleService();

    @ActionKey(Url.ROLE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_NAME);

        int count = roleService.count(model.getRole_name());

        List<Role> roleList = roleService.list(model.getRole_name(), getM(), getN());

        renderSuccessJson(count, roleList);
    }

    @ActionKey(Url.ROLE_ADMIN_CATEGORY_LIST)
    public void adminCategoryList() {
        List<Map<String, Object>> resultList = roleService.categoryList();

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.ROLE_ADMIN_FIND)
    public void adminFind() {
        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_ID);

        Role role = roleService.find(model.getRole_id());

        renderSuccessJson(role.removeSystemInfo());
    }

    @ActionKey(Url.ROLE_ADMIN_RESOURCE_FIND)
    public void adminResourceFind() {
        Role model = getParameter(Role.class);

        model.validate(Role.ROLE_ID);

        List<Map<String, Object>> resultList = roleService.resourceFind(model.getRole_id());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.ROLE_ADMIN_SAVE)
    public void adminSave() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_NAME);

        roleService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ROLE_ADMIN_RESOURCE_SAVE)
    public void adminResourceSave() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_ID);

        validate("resource_id_list");

        JSONObject jsonObject = getAttr(Constant.REQUEST_PARAMETER);

        roleService.resourceSave(model.getRole_id(), jsonObject.getJSONArray("resource_id_list"), request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ROLEL_ADMIN_UPDATE)
    public void adminUpdate() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_ID, Role.ROLE_NAME);

        roleService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ROLE_ADMIN_DELETE)
    public void adminDelete() {
        Role model = getParameter(Role.class);
        String request_user_id = getRequest_user_id();

        model.validate(Role.ROLE_ID);

        roleService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
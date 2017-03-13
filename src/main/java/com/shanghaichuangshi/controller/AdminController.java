package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.service.AdminService;

import java.util.List;
import java.util.Map;

public class AdminController extends Controller {

    private static final AdminService adminService = new AdminService();

    @ActionKey(Url.ADMIN_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Admin model = getParameter(Admin.class);

        model.validate(Admin.ADMIN_NAME);

        List<Admin> adminList = adminService.list(model, getM(), getN());

        renderSuccessJson(adminList);
    }

    @ActionKey(Url.ADMIN_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Admin model = getParameter(Admin.class);

        model.validate(Admin.ADMIN_NAME);

        int count = adminService.count(model);

        List<Admin> adminList = adminService.list(model, getM(), getN());

        renderSuccessJson(count, adminList);
    }

    @ActionKey(Url.ADMIN_FIND)
    public void find() {
        Admin model = getParameter(Admin.class);

        model.validate(Admin.ADMIN_ID);

        Admin admin = adminService.find(model.getAdmin_id());

        renderSuccessJson(admin.format());
    }

    @ActionKey(Url.ADMIN_ADMIN_FIND)
    public void adminFind() {
        Admin model = getParameter(Admin.class);

        model.validate(Admin.ADMIN_ID);

        Admin admin = adminService.find(model.getAdmin_id());

        renderSuccessJson(admin);
    }

    @ActionKey(Url.ADMIN_SAVE)
    public void save() {
        Admin model = getParameter(Admin.class);
        User userModel = getParameter(User.class);
        String request_user_id = getRequest_user_id();

        model.validate(Admin.ADMIN_NAME);
        userModel.validate(User.USER_ACCOUNT, User.USER_PASSWORD);

        adminService.save(model, userModel, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ADMINL_UPDATE)
    public void update() {
        Admin model = getParameter(Admin.class);
        User userModel = getParameter(User.class);
        String request_user_id = getRequest_user_id();

        model.validate(Admin.ADMIN_ID, Admin.ADMIN_NAME, Admin.USER_ID);
        userModel.validate(User.USER_ACCOUNT, User.USER_PASSWORD);

        adminService.update(model, userModel, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ADMIN_DELETE)
    public void delete() {
        Admin model = getParameter(Admin.class);
        String request_user_id = getRequest_user_id();

        model.validate(Admin.ADMIN_ID);

        adminService.delete(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ADMIN_LOGIN)
    public void login() {
        User model = getParameter(User.class);
        String request_user_id = getRequest_user_id();

        Map<String, Object> resultMap = adminService.login(model, getPlatform(), getVersion(), getIp_address(), request_user_id);

        renderSuccessJson(resultMap);
    }

}
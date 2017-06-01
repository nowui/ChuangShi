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

    private final AdminService adminService = new AdminService();

    @ActionKey(Url.ADMIN_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Admin model = getParameter(Admin.class);

        model.validate(Admin.ADMIN_NAME);

        int count = adminService.count(model.getAdmin_name());

        List<Admin> adminList = adminService.list(model.getAdmin_name(), getM(), getN());

        renderSuccessJson(count, adminList);
    }

    @ActionKey(Url.ADMIN_ADMIN_FIND)
    public void adminFind() {
        Admin model = getParameter(Admin.class);

        model.validate(Admin.ADMIN_ID);

        Admin admin = adminService.find(model.getAdmin_id());

        renderSuccessJson(admin.removeSystemInfo());
    }

    @ActionKey(Url.ADMIN_ADMIN_SAVE)
    public void adminSave() {
        Admin model = getParameter(Admin.class);
        User userModel = getParameter(User.class);
        String request_user_id = getRequest_user_id();

        model.validate(Admin.ADMIN_NAME);
        userModel.validate(User.USER_ACCOUNT, User.USER_PASSWORD);

        adminService.save(model, userModel, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ADMINL_ADMIN_UPDATE)
    public void adminUpdate() {
        Admin model = getParameter(Admin.class);
        User userModel = getParameter(User.class);
        String request_user_id = getRequest_user_id();

        model.validate(Admin.ADMIN_ID, Admin.ADMIN_NAME);
        userModel.validate(User.USER_ACCOUNT, User.USER_PASSWORD);

        adminService.update(model, userModel, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ADMIN_ADMIN_DELETE)
    public void adminDelete() {
        Admin model = getParameter(Admin.class);
        String request_user_id = getRequest_user_id();

        model.validate(Admin.ADMIN_ID);

        adminService.delete(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ADMIN_ADMIN_LOGIN)
    public void adminLogin() {
        User model = getParameter(User.class);
        String request_user_id = getRequest_user_id();

        Map<String, Object> resultMap = adminService.login(model.getUser_account(), model.getUser_password(), getPlatform(), getVersion(), getIp_address(), request_user_id);

        renderSuccessJson(resultMap);
    }

    @ActionKey(Url.ADMIN_ADMIN_MENU)
    public void adminMenu() {
        String request_user_id = getRequest_user_id();

        List<Map<String, Object>> resultList = adminService.menu(request_user_id);

        renderSuccessJson(resultList);
    }

}
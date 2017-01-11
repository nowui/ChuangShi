package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Admin;
import com.shanghaichuangshi.service.AdminService;

import java.util.List;

public class AdminController extends Controller {

    private final AdminService adminService = new AdminService();

    @Path(Url.ADMIN_LIST)
    public void list() {
        Admin adminModel = getModel(Admin.class);

        adminModel.validate(Admin.PAGE_INDEX, Admin.PAGE_SIZE);

        List<Admin> adminList = adminService.list(adminModel);

        renderJson(adminList);
    }

    @Path(Url.ADMIN_ADMIN_LIST)
    public void adminList() {
        Admin adminModel = getModel(Admin.class);

        adminModel.validate(Admin.PAGE_INDEX, Admin.PAGE_SIZE);

        int count = adminService.count(adminModel);

        List<Admin> adminList = adminService.list(adminModel);

        renderJson(count, adminList);
    }

    @Path(Url.ADMIN_FIND)
    public void find() {
        Admin adminModel = getModel(Admin.class);

        adminModel.validate(Admin.ADMIN_ID);

        Admin admin = adminService.find(adminModel);

        renderJson(admin);
    }

    @Path(Url.ADMIN_ADMIN_FIND)
    public void adminFind() {
        Admin adminModel = getModel(Admin.class);

        adminModel.validate(Admin.ADMIN_ID);

        Admin admin = adminService.find(adminModel);

        renderJson("");
    }

    @Path(Url.ADMIN_SAVE)
    public void save() {
        Admin adminModel = getModel(Admin.class);

        adminModel.validate(Admin.ADMIN_NAME);

        adminService.save(adminModel);

        renderJson("");
    }

    @Path(Url.ADMINL_UPDATE)
    public void update() {
        Admin adminModel = getModel(Admin.class);

        adminModel.validate(Admin.ADMIN_ID, Admin.ADMIN_NAME);

        adminService.update(adminModel);

        renderJson("");
    }

    @Path(Url.ADMIN_DELETE)
    public void delete() {
        Admin adminModel = getModel(Admin.class);

        adminModel.validate(Admin.ADMIN_ID);

        adminService.delete(adminModel);

        renderJson("");
    }

}
package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Role;
import com.shanghaichuangshi.service.RoleService;

import java.util.List;

public class RoleController extends Controller {

    private final RoleService roleService = new RoleService();

    @Path(Url.ROLE_LIST)
    public void list() {
        Role roleModel = getModel(Role.class);

        roleModel.validate(Role.PAGE_INDEX, Role.PAGE_SIZE);

        List<Role> roleList = roleService.list(roleModel);

        renderJson(roleList);
    }

    @Path(Url.ROLE_ADMIN_LIST)
    public void adminList() {
        Role roleModel = getModel(Role.class);

        roleModel.validate(Role.ROLE_NAME, Role.PAGE_INDEX, Role.PAGE_SIZE);

        int count = roleService.count(roleModel);

        List<Role> roleList = roleService.list(roleModel);

        renderJson(count, roleList);
    }

    @Path(Url.ROLE_FIND)
    public void find() {
        Role roleModel = getModel(Role.class);

        roleModel.validate(Role.ROLE_ID);

        Role role = roleService.find(roleModel);

        role.removeUnfindable();

        renderJson(role);
    }

    @Path(Url.ROLE_ADMIN_FIND)
    public void adminFind() {
        Role roleModel = getModel(Role.class);

        roleModel.validate(Role.ROLE_ID);

        Role role = roleService.find(roleModel);

        renderJson("");
    }

    @Path(Url.ROLE_SAVE)
    public void save() {
        Role roleModel = getModel(Role.class);

        roleModel.validate(Role.ROLE_NAME);

        roleService.save(roleModel);

        renderJson("");
    }

    @Path(Url.ROLEL_UPDATE)
    public void update() {
        Role roleModel = getModel(Role.class);

        roleModel.validate(Role.ROLE_ID, Role.ROLE_NAME);

        roleService.update(roleModel);

        renderJson("");
    }

    @Path(Url.ROLE_DELETE)
    public void delete() {
        Role roleModel = getModel(Role.class);

        roleModel.validate(Role.ROLE_ID);

        roleService.delete(roleModel);

        renderJson("");
    }

}
package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.service.UserService;

import java.util.List;

public class UserController extends Controller {

    private final UserService userService = new UserService();

    @Path(Url.USER_LIST)
    public void list() {
        User userModel = getModel(User.class);

        userModel.validate(User.PAGE_INDEX, User.PAGE_SIZE);

        List<User> userList = userService.list(userModel);

        for (User user : userList) {
            user.keep(User.USER_ID);
        }

        renderJson(userList);
    }

    @Path(Url.USER_ADMIN_LIST)
    public void adminList() {
        User userModel = getModel(User.class);

        userModel.validate(User.PAGE_INDEX, User.PAGE_SIZE);

        int count = userService.count(userModel);

        List<User> userList = userService.list(userModel);

        for (User user : userList) {
            user.keep(User.USER_ID);
        }

        renderJson(count, userList);
    }

    @Path(Url.USER_FIND)
    public void find() {
        User userModel = getModel(User.class);

        userModel.validate(User.USER_ID);

        User user = userService.find(userModel);

        renderJson(user);
    }

    @Path(Url.USER_ADMIN_FIND)
    public void adminFind() {
        User userModel = getModel(User.class);

        userModel.validate(User.USER_ID);

        User user = userService.find(userModel);

        renderJson("");
    }

    @Path(Url.USER_SAVE)
    public void save() {
        User userModel = getModel(User.class);

        userService.save(userModel);

        renderJson("");
    }

    @Path(Url.USERL_UPDATE)
    public void update() {
        User userModel = getModel(User.class);

        userModel.validate(User.USER_ID);

        userService.update(userModel);

        renderJson("");
    }

    @Path(Url.USER_DELETE)
    public void delete() {
        User userModel = getModel(User.class);

        userModel.validate(User.USER_ID);

        userService.delete(userModel);

        renderJson("");
    }

}
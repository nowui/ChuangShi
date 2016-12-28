package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class ApplicationController extends Controller {

    private UserService userService = new UserService();

    @Path("/app/index")
    public void index() {
        User uu = new User();
        uu.setUser_id("00e600a0a7de4d158098e54982608598");
        uu.set("page_index", 1);
        uu.set("page_size", 10);
        uu.search(User.USER_ID, User.USER_ACCOUNT);
        List<User> userList = userService.list(uu);

        User user = userService.find(uu);

//        user.save();

        renderJson(10, user);
    }

    @Path("/app/detail")
    public void detail() {

        renderJson("ApplicationController detail");
    }

}

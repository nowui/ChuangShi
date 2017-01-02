package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.service.UserService;

import java.util.List;

public class ApplicationController extends Controller {

    private UserService userService = new UserService();

    @Path("/app/index")
    public void index() {
        User uu = new User();
        uu.setUser_id("00e600a0a7de4d158098e54982608598");
        uu.set("page_index", 1);
        uu.set("page_size", 1);
        List<User> userList = userService.list(uu);

        renderJson(10, userList);
    }

}

package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.User;

import java.util.ArrayList;
import java.util.List;

public class ApplicationController extends Controller {

    @Path("/app/index")
    public void index() {
        List<User> userList = new User().list("select * from table_user", new ArrayList<Object>());
        for (User u : userList) {
            u.keep(User.USER_ID, User.USER_NAME);
        }

        User user = new User().findById("00e600a0a7de4d158098e54982608598");

//        User user = new User().find("select * from table_user", new ArrayList<Object>());

        user.save();

        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT COUNT(*) FROM ").append("table_user ");

        renderJson(10, user);
    }

    @Path("/app/detail")
    public void detail() {

        renderJson("ApplicationController detail");
    }

}

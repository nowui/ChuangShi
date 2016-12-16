package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.model.User;

import java.util.ArrayList;
import java.util.List;

public class ApplicationController extends Controller {

    @Path("/app/index")
    public void index() {
        List<User> userList = new User().list("select * from table_user", new ArrayList<Object>());
        for (User u : userList) {
            u.keep(User.COLUMN_USER_ID, User.COLUMN_USER_NAME);
        }

        User user = new User().find("select * from table_user", new ArrayList<Object>());
        user.keep(User.COLUMN_USER_ID);

        user.save();

        renderJson(10, userList);
    }

    @Path("/app/detail")
    public void detail() {

        renderJson("ApplicationController detail");
    }

}

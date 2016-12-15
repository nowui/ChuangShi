package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationController extends Controller {

    @Path("/app/index")
    public void index() {
        List<User> userList = new User().list("select * from table_user", new ArrayList<Object>());

        renderJson(userList.size(), userList);
    }

    @Path("/app/detail")
    public void detail() {

        renderJson("ApplicationController detail");
    }

}

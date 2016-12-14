package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationController extends Controller {

    @Path("/app/index")
    public void index() {
        List<Object> parameterList = new ArrayList<Object>();
        List<Map<String, Object>> list = DBUtil.list("select * from table_user", parameterList);

        System.out.println(list.size());

        renderJson("ApplicationController index");
    }

    @Path("/app/detail")
    public void detail() {

        renderJson("ApplicationController detail");
    }

}

package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;

public class ApplicationController extends Controller {

    @Path("/app/index")
    public void index() {
        renderJson("ApplicationController index");
    }

    @Path("/app/detail")
    public void detail() {

        renderJson("ApplicationController detail");
    }

}

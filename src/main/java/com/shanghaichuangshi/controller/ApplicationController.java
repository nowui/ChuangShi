package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Action;

public class ApplicationController extends Controller {

    @Action("index")
    public void index() {
        System.out.println("ApplicationController index");
    }

}

package com.shanghaichuangshi.render;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Render {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public Render setContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        return this;
    }

    public abstract void render();

}

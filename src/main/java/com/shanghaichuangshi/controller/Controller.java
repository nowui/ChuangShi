package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.render.RenderFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private static final RenderFactory renderFactory = RenderFactory.getInstance();

    public Controller setContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        return this;
    }

    public void renderJson(Integer total, Object data) {
        renderFactory.getJsonRender(total, data).setContext(request, response).render();
    }

    public void renderJson(Object data) {
        renderFactory.getJsonRender(data).setContext(request, response).render();
    }

}

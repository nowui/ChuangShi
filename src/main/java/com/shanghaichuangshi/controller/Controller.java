package com.shanghaichuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.shanghaichuangshi.constant.Key;
import com.shanghaichuangshi.model.Model;
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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

    public <T> T getAttribute(String key) {
        return (T)request.getAttribute(key);
    }

    public void renderJson(Object data) {
        renderFactory.getJsonRender(data).setContext(request, response).render();
    }

    public void renderJson(Integer total, Object data) {
        renderFactory.getJsonRender(total, data).setContext(request, response).render();
    }

    public <T> T getModel(Class<T> modelClass) {
        try {
            Object object = modelClass.newInstance();

            if (object instanceof Model == false) {
                throw new RuntimeException("Model type is error");
            }

            ((Model<?>) object).set((JSONObject) getAttribute(Key.REQUEST_PARAMETER));

            ((Model<?>) object).set(Key.REQUEST_USER_ID, getAttribute(Key.REQUEST_USER_ID));

            return (T) object;
        } catch (InstantiationException e) {
            throw new RuntimeException("InstantiationException: ", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("IllegalAccessException: ", e);
        }
    }

}

package com.shanghaichuangshi.route;

import com.shanghaichuangshi.controller.Controller;

import java.lang.reflect.Method;

public class Route {

    private String path;
    private String controllerKey;
    private Class<? extends Controller> controllerClass;
    private Method method;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getControllerKey() {
        return controllerKey;
    }

    public void setControllerKey(String controllerKey) {
        this.controllerKey = controllerKey;
    }

    public Class<? extends Controller> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<? extends Controller> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}

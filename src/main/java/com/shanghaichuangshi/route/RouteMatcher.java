package com.shanghaichuangshi.route;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.controller.Controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RouteMatcher {

    private List<Route> routeList = new ArrayList<Route>();

    public RouteMatcher add(String controllerKey, Class<? extends Controller> controllerClass) {
        Boolean isNotExitControllerKey = checkControllerKey(controllerKey);

        if (isNotExitControllerKey) {
            Method[] methodArray = controllerClass.getMethods();

            for (Method method : methodArray) {
                Path path = method.getAnnotation(Path.class);
                if (path != null) {
                    Boolean isNotExitPath = checkPath(path.value());
                    if(isNotExitPath) {
                        Route route = new Route();
                        route.setPath(path.value());
                        route.setControllerKey(controllerKey);
                        route.setControllerClass(controllerClass);
                        route.setMethod(method);
                        routeList.add(route);
                    } else {
                        throw new RuntimeException("Path already exists: " + path.value());
                    }
                }
            }
        } else {
            throw new RuntimeException("ControllerKey already exists: " + controllerKey);
        }

        return this;
    }

    private Boolean checkControllerKey(String controllerKey) {
        for (Route route : routeList) {
            if (route.getControllerKey().equals(controllerKey)) {
                return false;
            }
        }

        return true;
    }

    private Boolean checkPath(String path) {
        for (Route route : routeList) {
            if (route.getPath().equals(path)) {
                return false;
            }
        }

        return true;
    }

    public Route find(String path) {
        for (Route route : routeList) {
            if (route.getPath().equals(path)) {
                return route;
            }
        }

        return null;
    }

}

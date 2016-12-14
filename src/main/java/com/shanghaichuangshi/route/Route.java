package com.shanghaichuangshi.route;


import com.shanghaichuangshi.controller.Controller;

import java.util.HashMap;
import java.util.Map;

public class Route {

    private Map<String, Class<? extends Controller>> map = new HashMap<String, Class<? extends Controller>>();

    public Route add(String controllerKey, Class<? extends Controller> controllerClass) {
        map.put(controllerKey, controllerClass);

        return this;
    }

}

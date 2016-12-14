package com.shanghaichuangshi.filter;

import com.shanghaichuangshi.annotation.Action;
import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.controller.ApplicationController;
import com.shanghaichuangshi.controller.Controller;
import com.shanghaichuangshi.route.Route;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Filter implements javax.servlet.Filter {

    private Config config;
    private static final Route route = new Route();

    public void init(FilterConfig filterConfig) throws ServletException {
        String configClass = filterConfig.getInitParameter("config");
        Object object = null;
        try {
            object = Class.forName(configClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Can not create instance of class: " + configClass, e);
        }

        if (object instanceof Config)
            config = (Config)object;
        else {
            throw new RuntimeException("Can not create instance of class: " + configClass + ". Please check the config in web.xml");
        }

        config.configRoute(route);

        try {
            executeMethod(ApplicationController.class, ApplicationController.class.getDeclaredMethod("index"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();

        System.out.println(path);
    }

    public void destroy() {
        System.out.println("destroy");
    }

    private void executeMethod(Class<? extends Controller> controller, Method method) {
        method.setAccessible(true);

        try {
            Action action = method.getAnnotation(Action.class);

            System.out.println(action.value());

            method.invoke(controller.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}

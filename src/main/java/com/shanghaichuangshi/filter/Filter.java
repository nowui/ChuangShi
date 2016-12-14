package com.shanghaichuangshi.filter;

import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.config.Global;
import com.shanghaichuangshi.controller.Controller;
import com.shanghaichuangshi.render.RenderFactory;
import com.shanghaichuangshi.route.Route;
import com.shanghaichuangshi.route.RouteMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Filter implements javax.servlet.Filter {

    private Config config;
    private static final RouteMatcher routeMatcher = new RouteMatcher();
    private static final RenderFactory renderFactory = RenderFactory.getInstance();

    public void init(FilterConfig filterConfig) throws ServletException {
        String configClass = filterConfig.getInitParameter("config");
        Object object;
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

        Global.init(config);

        config.configRouteMatcher(routeMatcher);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();

        Route route = routeMatcher.find(path);
        if(route != null) {
            execute(route.getControllerClass(), route.getMethod(), request, response);
        } else {
            renderFactory.getErrorRender().setContext(request, response).render();
        }
    }

    private void execute(Class<? extends Controller> controller, Method method, HttpServletRequest request, HttpServletResponse response) {
        method.setAccessible(true);

        try {
            method.invoke(controller.newInstance().setContext(request, response));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException("InvocationTargetException: ", e);
        } catch (InstantiationException e) {
            throw new RuntimeException("InstantiationException: ", e);
        }
    }

    public void destroy() {

    }

}

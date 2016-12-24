package com.shanghaichuangshi.filter;

import com.shanghaichuangshi.config.Certificate;
import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.controller.Controller;
import com.shanghaichuangshi.render.RenderFactory;
import com.shanghaichuangshi.route.Route;
import com.shanghaichuangshi.route.RouteMatcher;
import com.shanghaichuangshi.util.DatabaseUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filter implements javax.servlet.Filter {

    private Config config;
    private static final Certificate certificate = new Certificate();
    private static final RouteMatcher routeMatcher = new RouteMatcher();
    private static final List<String> uncheckTokenUrlList = new ArrayList<String>();
    private static final RenderFactory renderFactory = RenderFactory.getInstance();

    public void init(FilterConfig filterConfig) throws ServletException {
        String configClass = filterConfig.getInitParameter("WebConfig");
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

        DatabaseUtil.init(config);

        config.configCertificate(certificate);

        config.configRouteMatcher(routeMatcher);

        config.configUncheckTokenUrl(uncheckTokenUrlList);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Date start = new Date();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type, Last-Modified, Token, Platform, Version");
        response.setHeader("Access-Control-Max-Age", "7200");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        String path = request.getRequestURI();

        String platform = "";

        String version = "";

        String user_id = "";

        String authorization_id = "";

        String parameter = "{}";

        Connection connection = null;

        try {
            connection = DatabaseUtil.getDruidDataSource().getConnection();
            connection.setAutoCommit(false);


            Route route = routeMatcher.find(path);
            if(route != null) {
                execute(route.getControllerClass(), route.getMethod(), request, response);
            } else {
                renderFactory.getErrorRender().setContext(request, response).render();
            }

            connection.commit();
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();

            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            Date end = new Date();
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

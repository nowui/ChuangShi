package com.shanghaichuangshi.filter;

import com.shanghaichuangshi.config.Certificate;
import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.controller.Controller;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.render.RenderFactory;
import com.shanghaichuangshi.route.Route;
import com.shanghaichuangshi.route.RouteMatcher;
import com.shanghaichuangshi.util.DatabaseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.shanghaichuangshi.constant.Constant.DATABASE;

public class Filter implements javax.servlet.Filter {

    private Logger logger = LogManager.getLogger(Filter.class.getName());
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

        if (path.equals(Url.FAVICON_ICO)) {
            filterChain.doFilter(request, response);

            return;
        }

        String platform = "";

        String version = "";

        String user_id = "";

        String authorization_id = "";

        String parameter = "{}";

        try {
            DatabaseUtil.start();

            Route route = routeMatcher.find(path);
            if(route != null) {
                execute(route.getControllerClass(), route.getMethod(), request, response);
            } else {
                renderFactory.getNotFoundRender().setContext(request, response).render();
            }

            DatabaseUtil.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();

            DatabaseUtil.rollback();

            renderFactory.getInternalServerErrorRender(e.toString()).setContext(request, response).render();
        } finally {
            DatabaseUtil.close();

            Date end = new Date();

            ThreadContext.put(Log.LOG_URL, path);
            ThreadContext.put(Log.LOG_RUN_TIME, (end.getTime() - start.getTime()) + "");

            logger.log(DATABASE, "Add a new log to the database");
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

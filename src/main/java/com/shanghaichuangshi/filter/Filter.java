package com.shanghaichuangshi.filter;

import com.alibaba.fastjson.JSONObject;
import com.shanghaichuangshi.config.Certificate;
import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.constant.Key;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.controller.Controller;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.render.RenderFactory;
import com.shanghaichuangshi.route.Route;
import com.shanghaichuangshi.route.RouteMatcher;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.HttpUtil;
import com.shanghaichuangshi.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

        if (path.equals(Url.FAVICON_ICO) || request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);

            return;
        }

        String platform = "";

        String version = "";

        String user_id = "";

        String authorization_id = "";

        String parameter = "{}";

        try {
            parameter = HttpUtil.readData(request);

            if (Util.isNullOrEmpty(parameter)) {
                parameter = "{}";
            }

            Route route = routeMatcher.find(path);
            if(route != null) {
                try {
                    DatabaseUtil.start();

                    Controller controller = route.getControllerClass().newInstance().setContext(request, response);

                    controller.setAttribute(Key.REQUEST_PARAMETER, JSONObject.parse(parameter));

                    route.getMethod().setAccessible(true);

                    route.getMethod().invoke(controller);

                    DatabaseUtil.commit();
                } catch (InstantiationException e) {
                    throw new Exception("InstantiationException:", e);
                } catch (IllegalAccessException e) {
                    throw new Exception("IllegalAccessException:", e);
                } catch (InvocationTargetException e) {
                    Throwable t = e.getTargetException();
                    throw t instanceof RuntimeException ? (RuntimeException)t : new RuntimeException(e);
                }
            } else {
                renderFactory.getNotFoundRender().setContext(request, response).render();
            }
        } catch (Exception e) {
            e.printStackTrace();

            DatabaseUtil.rollback();

            String message = e.toString();
            String runtimeException = "java.lang.RuntimeException: ";

            if (message.contains(runtimeException)) {
                message = message.replace(runtimeException, "");

                renderFactory.getBadRequestRender(message).setContext(request, response).render();
            } else {
                renderFactory.getInternalServerErrorRender(message).setContext(request, response).render();
            }

        } finally {
            DatabaseUtil.close();

            Date end = new Date();

            ThreadContext.put(Log.LOG_URL, path);
            ThreadContext.put(Log.LOG_REQUEST, parameter);
            ThreadContext.put(Log.LOG_RESPONSE, request.getAttribute(Key.RESPONSE_PARAMETER).toString());
            ThreadContext.put(Log.LOG_RUN_TIME, (end.getTime() - start.getTime()) + "");

            logger.log(DATABASE, "Add a new log to the database");
        }
    }

    public void destroy() {

    }

}

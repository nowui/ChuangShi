package com.shanghaichuangshi.filter;

import com.alibaba.fastjson.JSONObject;
import com.shanghaichuangshi.config.Certificate;
import com.shanghaichuangshi.config.Config;
import com.shanghaichuangshi.constant.Global;
import com.shanghaichuangshi.constant.Key;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.controller.*;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.render.RenderFactory;
import com.shanghaichuangshi.route.Route;
import com.shanghaichuangshi.route.RouteMatcher;
import com.shanghaichuangshi.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.crypto.spec.SecretKeySpec;
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
    private static final List<String> uncheckParameterUrlList = new ArrayList<String>();
    private static final List<String> uncheckLogUrlList = new ArrayList<String>();
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
            config = (Config) object;
        else {
            throw new RuntimeException("Can not create instance of class: " + configClass + ". Please check the config in web.xml");
        }

        routeMatcher.add("/code", CodeController.class);
        routeMatcher.add("/role", RoleController.class);
        routeMatcher.add("/category", CategoryController.class);
        routeMatcher.add("/admin", AdminController.class);
        routeMatcher.add("/authorization", AuthorizationController.class);
        routeMatcher.add("/attribute", AttributeController.class);
        routeMatcher.add("/log", LogController.class);
        routeMatcher.add("/resource", ResourceController.class);
        routeMatcher.add("/upload", UploadController.class);
        routeMatcher.add("/file", FileController.class);

        uncheckTokenUrlList.add(Url.ADMIN_LOGIN);

        uncheckParameterUrlList.add(Url.UPLOAD_IMAGE);

        uncheckLogUrlList.add(Url.LOG_ADMIN_LIST);
        uncheckLogUrlList.add(Url.LOG_ADMIN_FIND);
        uncheckLogUrlList.add(Url.UPLOAD_IMAGE);

        config.configCertificate(certificate);
        config.configRouteMatcher(routeMatcher);
        config.configUncheckTokenUrl(uncheckTokenUrlList);

        FileUtil.getWebRootPath();
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

        if (path.equals(Url.FAVICON_ICO) || request.getMethod().equals("OPTIONS") || path.contains(Url.ASSETS)) {
            filterChain.doFilter(request, response);

            return;
        }

        int log_code = HttpStatus.SC_OK;

        String token = "";

        String platform = "";

        String version = "";

        String ip_address = "";

        String request_user_id = "";

        String authorization_id = "";

        String parameter = new JSONObject().toJSONString();

        try {
            DatabaseUtil.start();

            token = request.getHeader(Key.TOKEN);
            platform = request.getHeader(Key.PLATFORM);
            version = request.getHeader(Key.VERSION);
            ip_address = HttpUtil.getIpAddress(request);

            if (!uncheckTokenUrlList.contains(path)) {
                if (Util.isNullOrEmpty(token)) {
                    throw new RuntimeException(Key.TOKEN + "不能为空");
                }

                try {
                    java.security.Key key = new SecretKeySpec(Global.key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

                    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

                    request_user_id = claims.get(User.USER_ID).toString();

                    authorization_id = claims.get(Authorization.AUTHORIZATION_ID).toString();
                } catch (Exception e) {
                    throw new RuntimeException(Key.TOKEN + "不对");
                }
            }

            if (Util.isNullOrEmpty(platform)) {
                throw new RuntimeException(Key.PLATFORM + "不能为空");
            }

            if (Util.isNullOrEmpty(version)) {
                throw new RuntimeException(Key.VERSION + "不能为空");
            }

            if (!uncheckParameterUrlList.contains(path)) {
                parameter = HttpUtil.readData(request);
                if (Util.isNullOrEmpty(parameter)) {
                    parameter = new JSONObject().toJSONString();
                }
            }

            Route route = routeMatcher.find(path);
            if (route != null) {
                try {
                    Controller controller = route.getControllerClass().newInstance().setContext(request, response);

                    controller.setAttribute(Key.REQUEST_PARAMETER, JSONObject.parse(parameter));
                    controller.setAttribute(Key.REQUEST_USER_ID, request_user_id);
                    controller.setAttribute(Key.PLATFORM, platform);
                    controller.setAttribute(Key.VERSION, version);
                    controller.setAttribute(Key.IP_ADDRESS, ip_address);

                    route.getMethod().setAccessible(true);

                    route.getMethod().invoke(controller);

                    DatabaseUtil.commit();
                } catch (InstantiationException e) {
                    throw new Exception("InstantiationException:", e);
                } catch (IllegalAccessException e) {
                    throw new Exception("IllegalAccessException:", e);
                } catch (InvocationTargetException e) {
                    Throwable t = e.getTargetException();
                    throw t instanceof RuntimeException ? (RuntimeException) t : new RuntimeException(e);
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

                log_code = HttpStatus.SC_BAD_REQUEST;

                renderFactory.getBadRequestRender(message).setContext(request, response).render();
            } else {
                log_code = HttpStatus.SC_INTERNAL_SERVER_ERROR;

                renderFactory.getInternalServerErrorRender(message).setContext(request, response).render();
            }

        } finally {
            DatabaseUtil.close();

            if (uncheckLogUrlList.contains(path)) {

            } else {
                Date end = new Date();

                ThreadContext.put(Log.LOG_ID, Util.getRandomUUID());
                ThreadContext.put(Log.LOG_URL, path);
                ThreadContext.put(Log.LOG_REQUEST, parameter);
                ThreadContext.put(Log.LOG_RESPONSE, request.getAttribute(Key.RESPONSE_PARAMETER).toString());
                ThreadContext.put(Log.AUTHORIZATION_ID, authorization_id);
                ThreadContext.put(Log.USER_ID, request_user_id);
                ThreadContext.put(Log.LOG_CODE, String.valueOf(log_code));
                ThreadContext.put(Log.LOG_PLATFORM, platform);
                ThreadContext.put(Log.LOG_VERSION, version);
                ThreadContext.put(Log.LOG_IP_ADDRESS, ip_address);
                ThreadContext.put(Log.LOG_CREATE_TIME, DateUtil.getDateTimeString(start));
                ThreadContext.put(Log.LOG_RUN_TIME, (end.getTime() - start.getTime()) + "");
                ThreadContext.put(Log.SYSTEM_CREATE_USER_ID, request_user_id);
                ThreadContext.put(Log.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(start));
                ThreadContext.put(Log.SYSTEM_UPDATE_USER_ID, request_user_id);
                ThreadContext.put(Log.SYSTEM_UPDATE_TIME, DateUtil.getDateTimeString(start));
                ThreadContext.put(Log.SYSTEM_STATUS, "1");

                logger.log(DATABASE, "Add a new log to the database");
            }
        }
    }

    public void destroy() {

    }

}

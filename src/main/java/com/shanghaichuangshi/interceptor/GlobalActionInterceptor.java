package com.shanghaichuangshi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.DbKit;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Authorization;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.util.DateUtil;
import com.shanghaichuangshi.util.HttpUtil;
import com.shanghaichuangshi.util.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static com.shanghaichuangshi.constant.Constant.DATABASE;

public class GlobalActionInterceptor implements Interceptor {

    private Logger logger = LogManager.getLogger(GlobalActionInterceptor.class.getName());
    private final List<String> uncheckUrlList = new ArrayList<String>();
    private final List<String> uncheckTokenUrlList = new ArrayList<String>();
    private final List<String> uncheckRequestUserIdUrlList = new ArrayList<String>();
    private final List<String> uncheckParameterUrlList = new ArrayList<String>();
    private final List<String> uncheckLogUrlList = new ArrayList<String>();
    private final List<String> uncheckHeaderUrlList = new ArrayList<String>();

    public GlobalActionInterceptor(List<String> uncheckUrlList, List<String> uncheckTokenUrlList, List<String> uncheckRequestUserIdUrlList, List<String> uncheckParameterUrlList, List<String> uncheckHeaderUrlList) {
        this.uncheckUrlList.addAll(uncheckUrlList);

        this.uncheckTokenUrlList.add(Url.ADMIN_LOGIN);
        this.uncheckTokenUrlList.addAll(uncheckTokenUrlList);

        this.uncheckRequestUserIdUrlList.addAll(uncheckRequestUserIdUrlList);

        this.uncheckParameterUrlList.add(Url.UPLOAD_IMAGE);
        this.uncheckParameterUrlList.addAll(uncheckParameterUrlList);

        this.uncheckLogUrlList.add(Url.LOG_ADMIN_LIST);
        this.uncheckLogUrlList.add(Url.LOG_ADMIN_FIND);

        this.uncheckHeaderUrlList.addAll(uncheckHeaderUrlList);
    }

    public void intercept(Invocation invocation) {
        String url = invocation.getController().getRequest().getRequestURI();

        Date start = new Date();
        Connection connection = null;
        Controller controller = invocation.getController();
        int log_code = HttpStatus.SC_OK;
        String token = "";
        String platform = "";
        String version = "";
        String ip_address = HttpUtil.getIpAddress(controller.getRequest());
        String request_user_id = "";
        String authorization_id = "";
        JSONObject parameter = new JSONObject();

        try {
            connection = DbKit.getConfig().getDataSource().getConnection();
            DbKit.getConfig().setThreadLocalConnection(connection);
            connection.setAutoCommit(false);

            if (uncheckUrlList.contains(url) || uncheckTokenUrlList.contains(url) || uncheckHeaderUrlList.contains(url)) {

            } else {
                token = controller.getRequest().getHeader(Constant.TOKEN);

                try {
                    Key key = new SecretKeySpec(Constant.PRIVATE_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

                    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

                    request_user_id = claims.get(User.USER_ID).toString();

                    authorization_id = claims.get(Authorization.AUTHORIZATION_ID).toString();
                } catch (Exception e) {
                    if (!uncheckRequestUserIdUrlList.contains(url)) {
                        throw new RuntimeException(Constant.TOKEN + " timeout");
                    }
                }
            }

            platform = controller.getRequest().getHeader(Constant.PLATFORM);
            version = controller.getRequest().getHeader(Constant.VERSION);

            if (!Util.isNullOrEmpty(platform) || uncheckUrlList.contains(url) || uncheckHeaderUrlList.contains(url)) {

            } else {
                throw new RuntimeException(Constant.PLATFORM + " is null");
            }

            if (!Util.isNullOrEmpty(version) || uncheckUrlList.contains(url) || uncheckHeaderUrlList.contains(url)) {

            } else {
                throw new RuntimeException(Constant.VERSION + " is null");
            }

            if (uncheckUrlList.contains(url) || uncheckParameterUrlList.contains(url) || uncheckHeaderUrlList.contains(url)) {

            } else {
                parameter = JSONObject.parseObject(HttpKit.readData(controller.getRequest()));

                if (Util.isNullOrEmpty(parameter)) {
                    parameter = new JSONObject();
                }
            }

            if (uncheckUrlList.contains(url)) {

            } else {
                ((com.shanghaichuangshi.controller.Controller)controller).setPlatform(platform);
                ((com.shanghaichuangshi.controller.Controller)controller).setVersion(version);
                ((com.shanghaichuangshi.controller.Controller)controller).setIp_address(ip_address);
                ((com.shanghaichuangshi.controller.Controller)controller).setRequest_user_id(request_user_id);
                ((com.shanghaichuangshi.controller.Controller)controller).setPage_index(parameter.getIntValue(Constant.PAGE_INDEX));
                ((com.shanghaichuangshi.controller.Controller)controller).setPage_size(parameter.getIntValue(Constant.PAGE_SIZE));
                ((com.shanghaichuangshi.controller.Controller)controller).setParameter(parameter);
            }

            invocation.invoke();

            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {

            }

            log_code = HttpStatus.SC_BAD_REQUEST;

            String message = e.toString();
            String value = "java.lang.RuntimeException: ";
            message = message.replace(value, "");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Constant.CODE, HttpStatus.SC_BAD_REQUEST);
            map.put(Constant.MESSAGE, message);

            controller.renderJson(map);

            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            } finally {
                DbKit.getConfig().removeThreadLocalConnection();
            }

            if (!uncheckLogUrlList.contains(url)) {
                Date end = new Date();

                ThreadContext.put(Log.LOG_ID, Util.getRandomUUID());
                ThreadContext.put("log_full_url", controller.getRequest().getRequestURL().toString());
                ThreadContext.put(Log.LOG_URL, url);
                ThreadContext.put(Log.LOG_REQUEST, parameter.toJSONString());
                ThreadContext.put(Log.LOG_RESPONSE, controller.getAttrForStr(Constant.RESPONSE_PARAMETER));
                ThreadContext.put(Log.AUTHORIZATION_ID, authorization_id);
                ThreadContext.put(Log.USER_ID, request_user_id);
                ThreadContext.put(Log.LOG_CODE, String.valueOf(log_code));
                ThreadContext.put(Log.LOG_PLATFORM, platform);
                ThreadContext.put(Log.LOG_VERSION, version);
                ThreadContext.put(Log.LOG_IP_ADDRESS, ip_address);
                ThreadContext.put(Log.LOG_RUN_TIME, (end.getTime() - start.getTime()) + "");
                ThreadContext.put(Log.SYSTEM_CREATE_USER_ID, request_user_id);
                ThreadContext.put(Log.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(start));
                ThreadContext.put(Log.SYSTEM_UPDATE_USER_ID, request_user_id);
                ThreadContext.put(Log.SYSTEM_UPDATE_TIME, DateUtil.getDateTimeString(start));
                ThreadContext.put(Log.SYSTEM_STATUS, "1");

                logger.log(DATABASE, "");
            }
        }

    }

}

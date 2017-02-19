package com.shanghaichuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Model;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.util.Util;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Controller extends com.jfinal.core.Controller {

    public <T> T getParameter(Class<T> modelClass) {
        try {
            Object temp = modelClass.newInstance();

            if(!(temp instanceof Model)) {
                throw new RuntimeException("getModel only support class of Model, using getBean for other class.");
            } else {
                JSONObject jsonObject = getAttr(Constant.REQUEST_PARAMETER);

                return jsonObject.toJavaObject(modelClass);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void validate(String... keys) {
        for (String key : keys) {
            if (Util.isNull(getAttr(key))) {
                throw new RuntimeException(key + " is null");
            }
        }
    }

    public void setParameter(JSONObject parameter) {
        setAttr(Constant.REQUEST_PARAMETER, parameter);
    }

    public String getPlatform() {
        return getAttrForStr(Constant.PLATFORM);
    }

    public void setPlatform(String platform) {
        setAttr(Constant.PLATFORM, platform);
    }

    public String getVersion() {
        return getAttrForStr(Constant.VERSION);
    }

    public void setVersion(String version) {
        setAttr(Constant.VERSION, version);
    }

    public String getIp_address() {
        return getAttrForStr(Constant.IP_ADDRESS);
    }

    public void setIp_address(String ip_address) {
        setAttr(Constant.IP_ADDRESS, ip_address);
    }

    public void setPage_index(int page_index) {
        setAttr(Constant.PAGE_INDEX, page_index);
    }

    public void setPage_size(int page_size) {
        setAttr(Constant.PAGE_SIZE, page_size);
    }

    public int getM() {
        int page_index = getAttrForInt(Constant.PAGE_INDEX);
        int page_size = getAttrForInt(Constant.PAGE_SIZE);

        if (page_index > 0) {
            return (page_index - 1) * page_size;
        } else {
            return 1;
        }
    }

    public int getN() {
        int page_size = getAttrForInt(Constant.PAGE_SIZE);

        return page_size > 0 ? page_size : 0;
    }

    public String getRequest_user_id() {
        return getAttrForStr(Constant.REQUEST_USER_ID);
    }

    public void setRequest_user_id(String request_user_id) {
        setAttr(Constant.REQUEST_USER_ID, request_user_id);
    }

    public void renderSuccessJson() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);

        super.renderJson(map);
    }

    public void renderSuccessJson(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, object);

        super.renderJson(map);
    }

    public void renderSuccessJson(int count, Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.COUNT, count);
        map.put(Constant.DATA, object);

        super.renderJson(map);
    }

    public void renderErrorJson(String jsonText) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_BAD_REQUEST);
        map.put(Constant.MESSAGE, jsonText);

        super.renderJson(map);
    }

}

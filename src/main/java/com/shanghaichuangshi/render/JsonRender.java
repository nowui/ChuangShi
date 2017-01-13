package com.shanghaichuangshi.render;

import com.alibaba.fastjson.JSON;
import com.shanghaichuangshi.config.Json;
import com.shanghaichuangshi.constant.Key;

import java.io.IOException;
import java.io.PrintWriter;

public class JsonRender extends Render {

    private String jsonText;

    public JsonRender(Json json) {
        if (json != null) {
            jsonText = JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss");
        } else {
            throw new RuntimeException("The parameter object can not be null.");
        }
    }

    public void render() {
        request.setAttribute(Key.RESPONSE_PARAMETER, jsonText);

        PrintWriter writer = null;
        try {
            response.setHeader("Pragma", "no-cache");    // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setContentType("application/json; charset=utf-8");
            writer = response.getWriter();
            writer.write(jsonText);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("IOException: ", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}

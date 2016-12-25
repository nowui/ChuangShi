package com.shanghaichuangshi.render;

import com.shanghaichuangshi.config.Json;
import org.apache.http.HttpStatus;

public class RenderFactory {

    private static final RenderFactory renderFactory = new RenderFactory();

    private RenderFactory() {

    }

    public static RenderFactory getInstance() {
        return renderFactory;
    }

    public Render getJsonRender(Object data) {
        Json json = new Json(HttpStatus.SC_OK, "", data);

        return new JsonRender(json);
    }

    public Render getJsonRender(Integer total, Object data) {
        Json json = new Json(HttpStatus.SC_OK, "", total, data);

        return new JsonRender(json);
    }

    public Render getNotFoundRender() {
        Json json = new Json(HttpStatus.SC_NOT_FOUND, "", "");

        return new JsonRender(json);
    }

    public Render getInternalServerErrorRender(String error) {
        Json json = new Json(HttpStatus.SC_INTERNAL_SERVER_ERROR, "", error);

        return new JsonRender(json);
    }

}

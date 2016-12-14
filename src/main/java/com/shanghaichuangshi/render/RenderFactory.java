package com.shanghaichuangshi.render;

import org.apache.http.HttpStatus;

public class RenderFactory {

    private static final RenderFactory renderFactory = new RenderFactory();

    private RenderFactory() {

    }

    public static RenderFactory getInstance() {
        return renderFactory;
    }

    public Render getJsonRender(Object object) {
        Json json = new Json(HttpStatus.SC_OK, "", object);

        return new JsonRender(json);
    }

    public Render getErrorRender() {
        Json json = new Json(HttpStatus.SC_NOT_FOUND, "", "");

        return new JsonRender(json);
    }

}

package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Key;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.service.UploadService;

public class UploadController extends Controller {

    private final UploadService uploadService = new UploadService();

    @Path(Url.UPLOAD_IMAGE)
    public void image() {
        String request_user_id = getAttribute(Key.REQUEST_USER_ID);

        uploadService.upload(getRequest(), request_user_id);

        renderJson("");
    }

}
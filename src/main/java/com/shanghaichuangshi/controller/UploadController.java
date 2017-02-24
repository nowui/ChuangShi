package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.service.UploadService;

import java.util.List;

public class UploadController extends Controller {

    private static final UploadService uploadService = new UploadService();

    @ActionKey(Url.UPLOAD_IMAGE)
    public void image() {
        String request_user_id = getRequest_user_id();

        List<UploadFile> uploadFileList = getFiles(request_user_id, 1024 * 1024 * 2);

        uploadService.image(uploadFileList, request_user_id);

        renderSuccessJson();
    }

}

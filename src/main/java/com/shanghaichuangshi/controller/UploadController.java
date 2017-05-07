package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.service.UploadService;

import java.util.List;
import java.util.Map;

public class UploadController extends Controller {

    private final UploadService uploadService = new UploadService();

    @ActionKey(Url.UPLOAD_IMAGE)
    public void image() {
        String request_user_id = getRequest_user_id();

        List<UploadFile> uploadFileList = getFiles(request_user_id, 1024 * 1024 * 2);

        List<Map<String, Object>> resultList = uploadService.image(uploadFileList, request_user_id);

        renderSuccessJson(resultList);
    }

}

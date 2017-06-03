package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.service.FileService;
import com.shanghaichuangshi.type.FileType;

import java.util.List;
import java.util.Map;

public class FileController extends Controller {

    private final FileService fileService = new FileService();

    @ActionKey(Url.FILE_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File model = getParameter(File.class);
        String request_user_id = getRequest_user_id();

        model.validate(File.FILE_NAME);

        List<File> fileList = fileService.list(model.getFile_name(), "", request_user_id, getM(), getN());

        renderSuccessJson(fileList);
    }

    @ActionKey(Url.FILE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File model = getParameter(File.class);
        String request_user_id = getRequest_user_id();

        model.validate(File.FILE_NAME);

        int count = fileService.count(model.getFile_name(), "", request_user_id);

        List<File> fileList = fileService.list(model.getFile_name(), "", request_user_id, getM(), getN());

        renderSuccessJson(count, fileList);
    }

    @ActionKey(Url.FILE_ADMIN_IMAGE_LIST)
    public void adminImageList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File model = getParameter(File.class);
        String request_user_id = getRequest_user_id();

        model.validate(File.FILE_NAME);

        String file_type = FileType.IMAGE.getKey();

        int count = fileService.count(model.getFile_name(), file_type, request_user_id);

        List<File> fileList = fileService.list(model.getFile_name(), file_type, request_user_id, getM(), getN());

        renderSuccessJson(count, fileList);
    }

    @ActionKey(Url.FILE_ADMIN_VIDEO_LIST)
    public void adminVideoList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File model = getParameter(File.class);
        String request_user_id = getRequest_user_id();

        model.validate(File.FILE_NAME);

        String file_type = FileType.VIDEO.getKey();

        int count = fileService.count(model.getFile_name(), file_type, request_user_id);

        List<File> fileList = fileService.list(model.getFile_name(), file_type, request_user_id, getM(), getN());

        renderSuccessJson(count, fileList);
    }

    @ActionKey(Url.FILE_FIND)
    public void find() {
        File model = getParameter(File.class);

        model.validate(File.FILE_ID);

        File file = fileService.find(model.getFile_id());

        renderSuccessJson(file.removeUnfindable());
    }

    @ActionKey(Url.FILE_ADMIN_FIND)
    public void adminFind() {
        File model = getParameter(File.class);

        model.validate(File.FILE_ID);

        File file = fileService.find(model.getFile_id());

        renderSuccessJson(file.removeSystemInfo());
    }

//    @ActionKey(Url.FILE_SAVE)
//    public void save() {
//        File model = getParameter(File.class);
//        String request_user_id = getRequest_user_id();
//
//        model.validate(File.FILE_NAME);
//
//        fileService.save(model, request_user_id);
//
//        renderSuccessJson();
//    }

//    @ActionKey(Url.FILE_VIDEO_SAVE)
//    public void videoSave() {
//        File model = getParameter(File.class);
//        String request_user_id = getRequest_user_id();
//
//        model.validate(File.FILE_NAME, File.FILE_PATH, File.FILE_IMAGE);
//
//        File file = fileService.videoSave(model.getFile_name(), model.getFile_path(), model.getFile_image(), request_user_id);
//
//        renderSuccessJson(file);
//    }

    @ActionKey(Url.FILE_UPDATE)
    public void update() {
        File model = getParameter(File.class);
        String request_user_id = getRequest_user_id();

        model.validate(File.FILE_NAME, File.FILE_PATH, File.FILE_IMAGE);

        fileService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.FILE_ADMIN_IMAGE_UPLOAD)
    public void adminImageUpload() {
        String request_user_id = getRequest_user_id();

        List<UploadFile> uploadFileList = getFiles(request_user_id, 1024 * 1024 * 2);

        List<Map<String, Object>> resultList = fileService.imageUpload(uploadFileList, request_user_id);

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.FILE_DELETE)
    public void delete() {
        File model = getParameter(File.class);
        String request_user_id = getRequest_user_id();

        model.validate(File.FILE_ID);

        fileService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.service.FileService;

import java.util.List;

public class FileController extends Controller {

    private static final FileService fileService = new FileService();

    @ActionKey(Url.FILE_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File fileModel = getModel(File.class);
        String request_user_id = getRequest_user_id();

        fileModel.validate(File.FILE_NAME);

        List<File> fileList = fileService.list(fileModel, request_user_id, getM(), getN());

        renderSuccessJson(fileList);
    }

    @ActionKey(Url.FILE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File fileModel = getModel(File.class);
        String request_user_id = getRequest_user_id();

        fileModel.validate(File.FILE_NAME);

        int count = fileService.count(fileModel, request_user_id);

        List<File> fileList = fileService.list(fileModel, request_user_id, getM(), getN());

        renderSuccessJson(count, fileList);
    }

    @ActionKey(Url.FILE_FIND)
    public void find() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_ID);

        File file = fileService.find(fileModel);

        file.removeUnfindable();

        renderSuccessJson(file);
    }

    @ActionKey(Url.FILE_ADMIN_FIND)
    public void adminFind() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_ID);

        File file = fileService.find(fileModel);

        renderSuccessJson(file);
    }

    @ActionKey(Url.FILE_SAVE)
    public void save() {
        File fileModel = getModel(File.class);
        String request_user_id = getRequest_user_id();

        fileModel.validate(File.FILE_NAME);

        fileService.save(fileModel, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.FILEL_UPDATE)
    public void update() {
        File fileModel = getModel(File.class);
        String request_user_id = getRequest_user_id();

        fileModel.validate(File.FILE_ID, File.FILE_NAME);

        fileService.update(fileModel, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.FILE_DELETE)
    public void delete() {
        File fileModel = getModel(File.class);
        String request_user_id = getRequest_user_id();

        fileModel.validate(File.FILE_ID);

        fileService.delete(fileModel, request_user_id);

        renderSuccessJson();
    }

}
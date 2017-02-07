package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.File;
import com.shanghaichuangshi.service.FileService;

import java.util.List;

public class FileController extends Controller {

    private final FileService fileService = new FileService();

    @Path(Url.FILE_LIST)
    public void list() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_NAME, File.PAGE_INDEX, File.PAGE_SIZE);

        List<File> fileList = fileService.list(fileModel);

        renderJson(fileList);
    }

    @Path(Url.FILE_ADMIN_LIST)
    public void adminList() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.PAGE_INDEX, File.PAGE_SIZE);

        int count = fileService.count(fileModel);

        List<File> fileList = fileService.list(fileModel);

        renderJson(count, fileList);
    }

    @Path(Url.FILE_FIND)
    public void find() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_ID);

        File file = fileService.find(fileModel);

        file.removeUnfindable();

        renderJson(file);
    }

    @Path(Url.FILE_ADMIN_FIND)
    public void adminFind() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_ID);

        File file = fileService.find(fileModel);

        renderJson(file);
    }

    @Path(Url.FILE_SAVE)
    public void save() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_NAME);

        fileService.save(fileModel);

        renderJson("");
    }

    @Path(Url.FILEL_UPDATE)
    public void update() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_ID, File.FILE_NAME);

        fileService.update(fileModel);

        renderJson("");
    }

    @Path(Url.FILE_DELETE)
    public void delete() {
        File fileModel = getModel(File.class);

        fileModel.validate(File.FILE_ID);

        fileService.delete(fileModel);

        renderJson("");
    }

}
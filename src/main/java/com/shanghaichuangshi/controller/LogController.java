package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.service.LogService;

import java.util.List;

public class LogController extends Controller {

    private final LogService logService = new LogService();

    @Path(Url.LOG_LIST)
    public void list() {
        Log logModel = getModel(Log.class);

        logModel.validate(Log.PAGE_INDEX, Log.PAGE_SIZE);

        List<Log> logList = logService.list(logModel);

        renderJson(logList);
    }

    @Path(Url.LOG_ADMIN_LIST)
    public void adminList() {
        Log logModel = getModel(Log.class);

        logModel.validate(Log.LOG_URL, Log.PAGE_INDEX, Log.PAGE_SIZE);

        int count = logService.count(logModel);

        List<Log> logList = logService.list(logModel);

        renderJson(count, logList);
    }

    @Path(Url.LOG_FIND)
    public void find() {
        Log logModel = getModel(Log.class);

        logModel.validate(Log.LOG_ID);

        Log log = logService.find(logModel);

        log.removeUnfindable();

        renderJson(log);
    }

    @Path(Url.LOG_ADMIN_FIND)
    public void adminFind() {
        Log logModel = getModel(Log.class);

        logModel.validate(Log.LOG_ID);

        Log log = logService.find(logModel);

        renderJson(log);
    }

    @Path(Url.LOG_SAVE)
    public void save() {
        Log logModel = getModel(Log.class);

        logModel.validate(Log.LOG_URL);

        logService.save(logModel);

        renderJson("");
    }

    @Path(Url.LOGL_UPDATE)
    public void update() {
        Log logModel = getModel(Log.class);

        logModel.validate(Log.LOG_ID, Log.LOG_URL);

        logService.update(logModel);

        renderJson("");
    }

    @Path(Url.LOG_DELETE)
    public void delete() {
        Log logModel = getModel(Log.class);

        logModel.validate(Log.LOG_ID);

        logService.delete(logModel);

        renderJson("");
    }

}
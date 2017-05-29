package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.service.LogService;

import java.util.List;

public class LogController extends Controller {

    private final LogService logService = new LogService();

    @ActionKey(Url.LOG_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Log model = getParameter(Log.class);

        model.validate(Log.LOG_URL);

        List<Log> logList = logService.list(model.getLog_url(), model.getLog_code(), model.getLog_platform(), getM(), getN());

        renderSuccessJson(logList);
    }

    @ActionKey(Url.LOG_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Log model = getParameter(Log.class);

        model.validate(Log.LOG_URL, Log.LOG_CODE, Log.LOG_PLATFORM);

        int count = logService.count(model.getLog_url(), model.getLog_code(), model.getLog_platform());

        List<Log> logList = logService.list(model.getLog_url(), model.getLog_code(), model.getLog_platform(), getM(), getN());

        renderSuccessJson(count, logList);
    }

    @ActionKey(Url.LOG_FIND)
    public void find() {
        Log model = getParameter(Log.class);

        model.validate(Log.LOG_ID);

        Log log = logService.find(model.getLog_id());

        renderSuccessJson(log.removeUnfindable());
    }

    @ActionKey(Url.LOG_ADMIN_FIND)
    public void adminFind() {
        Log model = getParameter(Log.class);

        model.validate(Log.LOG_ID);

        Log log = logService.find(model.getLog_id());

        renderSuccessJson(log.removeSystemInfo());
    }

}
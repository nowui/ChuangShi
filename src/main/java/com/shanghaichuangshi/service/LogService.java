package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.LogDao;
import com.shanghaichuangshi.model.Log;

import java.util.List;

public class LogService extends Service {

    private final LogDao logDao = new LogDao();

    public int count(Log log) {
        return logDao.count(log.getLog_url());
    }

    public List<Log> list(Log log, int m, int n) {
        return logDao.list(log.getLog_url(), m, n);
    }

    public Log find(String log_id) {
        return logDao.find(log_id);
    }

}
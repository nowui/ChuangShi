package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.LogDao;
import com.shanghaichuangshi.model.Log;

import java.util.List;

public class LogService extends Service {

    private final LogDao logDao = new LogDao();

    public int count(String log_url, String log_code, String log_platform) {
        return logDao.count(log_url, log_code, log_platform);
    }

    public List<Log> list(String log_url, String log_code, String log_platform, int m, int n) {
        return logDao.list(log_url, log_code, log_platform, m, n);
    }

    public Log find(String log_id) {
        return logDao.find(log_id);
    }

}
package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.LogDao;
import com.shanghaichuangshi.model.Log;

import java.util.List;

public class LogService extends Service {

    private final LogDao logDao = new LogDao();

    public int count(Log log) {
        return logDao.count();
    }

    public List<Log> list(Log log) {
        return logDao.list(log.getLog_url(), log.getM(), log.getN());
    }

    public Log find(Log log) {
        return logDao.find(log.getLog_id());
    }

    public void save(Log log) {
        logDao.save(log);
    }

    public void update(Log log) {
        logDao.update(log);
    }

    public void delete(Log log) {
        logDao.delete(log);
    }

}
package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.LogDao;
import com.shanghaichuangshi.model.Log;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class LogCache extends Cache {

    public static final String LOG_BY_LOG_ID_CACHE = "log_by_log_id_cache";

    private LogDao logDao = new LogDao();

    public int count(String log_url, String log_code, String log_platform) {
        return logDao.count(log_url, log_code, log_platform);
    }

    public List<Log> list(String log_url, String log_code, String log_platform, int m, int n) {
        return logDao.list(log_url, log_code, log_platform, m, n);
    }

    public Log find(String log_id) {
        Log log = CacheUtil.get(LOG_BY_LOG_ID_CACHE, log_id);

        if (log == null) {
            log = logDao.find(log_id);

            CacheUtil.put(LOG_BY_LOG_ID_CACHE, log_id, log);
        }

        return log;
    }

}
package com.shanghaichuangshi.service;

import com.shanghaichuangshi.cache.LogCache;
import com.shanghaichuangshi.model.Log;

import java.util.List;

public class LogService extends Service {

    private final LogCache logCache = new LogCache();

    public int count(String log_url, String log_code, String log_platform) {
        return logCache.count(log_url, log_code, log_platform);
    }

    public List<Log> list(String log_url, String log_code, String log_platform, int m, int n) {
        return logCache.list(log_url, log_code, log_platform, m, n);
    }

    public Log find(String log_id) {
        return logCache.find(log_id);
    }

}
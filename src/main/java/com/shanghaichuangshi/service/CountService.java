package com.shanghaichuangshi.service;

import com.shanghaichuangshi.cache.CountCache;
import com.shanghaichuangshi.model.Count;

public class CountService extends Service {

    private final CountCache countCache = new CountCache();

    public Count find(String count_type, String object_id, String count_key) {
        return countCache.find(count_type, object_id, count_key);
    }

    public Count saveOrUpdate(String count_type, String object_id, String count_key, String count_value) {
        return countCache.saveOrUpdate(count_type, object_id, count_key, count_value);
    }

    public boolean delete(Count count, String request_user_id) {
        return countCache.delete(count.getCount_id(), request_user_id);
    }

}
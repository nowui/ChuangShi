package com.shanghaichuangshi.util;

import com.jfinal.plugin.ehcache.CacheKit;

import java.util.List;

public class CacheUtil {

    public static final String CACHE_NAME_CACHE = "cache_name_cache";

    public static <T> T get(String cacheName, Object key) {
        return CacheKit.get(cacheName, key);
    }

    public static List getKeys(String cacheName) {
        return CacheKit.getKeys(cacheName);
    }

    public static void put(String cacheName, Object key, Object value) {
        if (value != null) {
            CacheKit.put(cacheName, key, value);

            CacheKit.put(CACHE_NAME_CACHE, cacheName, cacheName);
        }
    }

    public static void remove(String cacheName, Object key) {
        CacheKit.remove(cacheName, key);
    }

    public static void removeAll(String cacheName) {
        CacheKit.removeAll(cacheName);
    }

}

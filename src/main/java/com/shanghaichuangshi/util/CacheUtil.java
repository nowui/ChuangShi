package com.shanghaichuangshi.util;

import com.jfinal.plugin.ehcache.CacheKit;

public class CacheUtil {

    public static <T> T get(String cacheName, Object key) {
        return CacheKit.get(cacheName, key);
    }

    public static void put(String cacheName, Object key, Object value) {
        if (value != null) {
            CacheKit.put(cacheName, key, value);
        }
    }

    public static void remove(String cacheName, Object key) {
        CacheKit.remove(cacheName, key);
    }

    public static void removeAll(String cacheName) {
        CacheKit.removeAll(cacheName);
    }

}

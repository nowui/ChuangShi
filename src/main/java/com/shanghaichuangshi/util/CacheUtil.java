package com.shanghaichuangshi.util;

import com.jfinal.plugin.ehcache.CacheKit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CacheUtil {

    private static final String KEY_CACHE = "key_cache";

    public static <T> T get(String cacheName, Object key) {
        return CacheKit.get(cacheName, key);
    }

    public static List<String> getKeys(String cacheName) {
        List<String> keyList = CacheKit.get(KEY_CACHE, cacheName);
        if (keyList == null) {
            keyList = new ArrayList<String>();
        }

        return keyList;
    }

    public static void put(String cacheName, Object key, Object value) {
        List<String> keyList = CacheKit.get(KEY_CACHE, cacheName);
        if (keyList == null) {
            keyList = new ArrayList<String>();
        }

        keyList.add(key.toString());

        CacheKit.put(KEY_CACHE, cacheName, keyList);

        CacheKit.put(cacheName, key, value);
    }

    public static void remove(String cacheName, Object key) {
        List<String> keyList = CacheKit.get(KEY_CACHE, cacheName);
        if (keyList == null) {
            keyList = new ArrayList<String>();
        }

        Iterator<String> iterator = keyList.iterator();
        while(iterator.hasNext()) {
            String k = iterator.next();

            if (k.equals(key)) {
                iterator.remove();
            }
        }

        CacheKit.put(KEY_CACHE, cacheName, keyList);

        CacheKit.remove(cacheName, key);
    }

    public static void removeAll(String cacheName) {
        CacheKit.removeAll(KEY_CACHE);

        CacheKit.removeAll(cacheName);
    }

}

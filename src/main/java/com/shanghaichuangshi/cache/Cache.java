package com.shanghaichuangshi.cache;

import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.UserManagedCacheBuilder;

import java.util.*;

public abstract class Cache {

    private final String KET_OBJECT_CACHE = "key_object_cache";

    private static final UserManagedCache<String, Object> userManagedCache = UserManagedCacheBuilder.newUserManagedCacheBuilder(String.class, Object.class).build(true);

    public Object getObjectBykeyAndId(String key, String id) {

        Object object = userManagedCache.get(key + "_" + id);

        return object;
    }

    public void setObjectBykeyAndId(Object object, String key, String id) {
        userManagedCache.put(key + "_" + id, object);

        setMapByKeyAndId(key, id);
    }

    public void removeObjectBykeyAndId(String key, String id) {
        userManagedCache.remove(key + "_" + id);

        removeMapByKeyAndId(key, id);
    }

    public void removeObjectByKey(String key) {
        userManagedCache.removeAll(getMapByKey(key));

        removeMapByKey(key);
    }

    protected Set<String> getMapByKey(String key) {
        Map<String, Set<String>> keyMap = (Map<String, Set<String>>) userManagedCache.get(KET_OBJECT_CACHE);

        if (keyMap == null) {
            keyMap = new HashMap<String, Set<String>>();
        }

        Set<String> set;

		if (keyMap.containsKey(key)) {
            set = keyMap.get(key);
		} else {
            set = new HashSet<String>();
		}

		return set;
	}

    protected void setMapByKeyAndId(String key, String id) {
        Map<String, Set<String>> keyMap = (Map<String, Set<String>>) userManagedCache.get(KET_OBJECT_CACHE);

        if (keyMap == null) {
            keyMap = new HashMap<String, Set<String>>();
        }

        Set<String> set;

		if (keyMap.containsKey(key)) {
            set = keyMap.get(key);
        } else {
            set = new HashSet<String>();
		}

        set.add(key + "_" + id);

        keyMap.put(key, set);

        userManagedCache.put(KET_OBJECT_CACHE, keyMap);
	}

    protected void removeMapByKeyAndId(String key, String id) {
        Map<String, Set<String>> keyMap = (Map<String, Set<String>>) userManagedCache.get(KET_OBJECT_CACHE);

        if (keyMap == null) {
            keyMap = new HashMap<String, Set<String>>();
        }

        Set<String> set;

		if (keyMap.containsKey(key)) {
            set = keyMap.get(key);

            set.remove(key + "_" + id);
		} else {
            set = new HashSet<String>();
		}

        keyMap.put(key, set);

        userManagedCache.put(KET_OBJECT_CACHE, keyMap);
	}

    protected void removeMapByKey(String key) {
        Map<String, Set<String>> keyMap = (Map<String, Set<String>>) userManagedCache.get(KET_OBJECT_CACHE);

        if (keyMap == null) {
            keyMap = new HashMap<String, Set<String>>();
        }

        Set<String> set = new HashSet<String>();

        keyMap.put(key, set);

        userManagedCache.put(KET_OBJECT_CACHE, keyMap);
	}

}

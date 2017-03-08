package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.model.User;

public class UserCache extends Cache {

    private final String USER_CACHE_MODEL = "user_cache_model";

    public User getUserByUser_id(String user_id) {
        return (User) ehcacheObject.get(USER_CACHE_MODEL + "_" + user_id);
    }

    public void setUserByUser_id(User user, String user_id) {
        ehcacheObject.put(USER_CACHE_MODEL + "_" + user_id, user);

        setMapByKeyAndId(USER_CACHE_MODEL, user_id);
    }

    public void removeUserByUser_id(String user_id) {
        ehcacheObject.remove(USER_CACHE_MODEL + "_" + user_id);

        removeMapByKeyAndId(USER_CACHE_MODEL, user_id);
    }

    public void removeUser() {
        ehcacheObject.removeAll(getMapByKey(USER_CACHE_MODEL));

        removeMapByKey(USER_CACHE_MODEL);
    }

}

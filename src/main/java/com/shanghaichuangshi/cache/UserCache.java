package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.model.User;

public class UserCache extends Cache {

    private final String USER_OBJECT_CACHE = "user_object_cache";

    public User getUserByUser_id(String user_id) {
        return (User) getObjectBykeyAndId(USER_OBJECT_CACHE, user_id);
    }

    public void setUserByUser_id(User user, String user_id) {
        setObjectBykeyAndId(user, USER_OBJECT_CACHE, user_id);
    }

    public void removeUserByUser_id(String user_id) {
        removeObjectBykeyAndId(USER_OBJECT_CACHE, user_id);
    }

    public void removeUser() {
        removeObjectByKey(USER_OBJECT_CACHE);
    }

}

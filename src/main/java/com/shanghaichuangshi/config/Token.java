package com.shanghaichuangshi.config;

public final class Token {

    private String user_id;
    private String token_expire_time;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToken_expire_time() {
        return token_expire_time;
    }

    public void setToken_expire_time(String token_expire_time) {
        this.token_expire_time = token_expire_time;
    }
}

package com.shanghaichuangshi.type;

public enum UserType {

    ADMIN("ADMIN", "管理员"),
    MEMBER("MEMBER", "会员"),
    ENTERPRISE("ENTERPRISE", "商家");

    private String key;
    private String value;

    private UserType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}

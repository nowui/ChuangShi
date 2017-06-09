package com.shanghaichuangshi.type;

public enum CountTypeEnum {

    MEMBER("MEMBER", "会员");

    private String key;
    private String value;

    private CountTypeEnum(String key, String value) {
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

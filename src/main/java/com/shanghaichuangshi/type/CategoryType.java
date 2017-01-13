package com.shanghaichuangshi.type;

public enum CategoryType {

    MENU("menu", "菜单"),
    CHINA("MEMBER", "中国省市区");

    private String key;
    private String value;

    private CategoryType(String key, String value) {
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

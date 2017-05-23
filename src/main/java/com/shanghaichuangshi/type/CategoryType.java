package com.shanghaichuangshi.type;

public enum CategoryType {

    MENU("MENU", "菜单"),
    CHINA("CHINA", "中国省市区"),
    ARTICLE("ARTICLE", "文章"),
    PRODUCT("PRODUCT", "商品");

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

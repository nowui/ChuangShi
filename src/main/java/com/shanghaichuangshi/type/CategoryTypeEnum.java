package com.shanghaichuangshi.type;

public enum CategoryTypeEnum {

    RESOURCE("RESOURCE", "资源"),
    CHINA("CHINA", "中国省市区"),
    ARTICLE("ARTICLE", "文章"),
    ROLE("ROLE", "角色"),
    PRODUCT("PRODUCT", "商品");

    private String key;
    private String value;

    private CategoryTypeEnum(String key, String value) {
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

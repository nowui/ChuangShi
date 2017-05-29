package com.shanghaichuangshi.type;

public enum ResourceType {

    URL("URL", "链接"),
    BUTTON("BUTTON", "按钮");

    private String key;
    private String value;

    private ResourceType(String key, String value) {
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

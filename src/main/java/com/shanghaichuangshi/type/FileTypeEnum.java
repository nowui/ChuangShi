package com.shanghaichuangshi.type;

public enum FileTypeEnum {

    IMAGE("IMAGE", "图片"),
    OTHER("OTHER", "其他");

    private String key;
    private String value;

    private FileTypeEnum(String key, String value) {
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

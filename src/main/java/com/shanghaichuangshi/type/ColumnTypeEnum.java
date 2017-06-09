package com.shanghaichuangshi.type;

public enum ColumnTypeEnum {
    INT("INT"),
    TINYINT("TINYINT"),
    VARCHAR("VARCHAR"),
    DECIMAL("DECIMAL"),
    BOOLEAN("BOOLEAN"),
    LONGTEXT("LONGTEXT"),
    DATETIME("DATETIME");

    private String key;

    private ColumnTypeEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

package com.shanghaichuangshi.type;

public enum ColumnType {
    INT("INT"),
    VARCHAR("VARCHAR"),
    DECIMAL("DECIMAL"),
    BOOLEAN("BOOLEAN"),
    LONGTEXT("LONGTEXT"),
    DATETIME("DATETIME");

    private String key;

    private ColumnType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

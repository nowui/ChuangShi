package com.shanghaichuangshi.type;

public enum ColumnType {
    CHAR("CHAR"),
    BOOLEAN("BOOLEAN"),
    VARCHAR("VARCHAR"),
    TEXT("TEXT"),
    BINARY("BINARY"),
    TIMESTAMP("TIMESTAMP"),
    DATETIME("DATETIME"),
    DATE("DATE"),
    TIME("TIME"),
    INT("INT"),
    FLOAT("FLOAT"),
    AUTO("AUTO");

    private String key;

    private ColumnType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

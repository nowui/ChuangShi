package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class Resource extends Model<Resource> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "资源编号")
    public static final String RESOURCE_ID = "resource_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单编号")
    public static final String MENU_ID = "menu_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "资源类型")
    public static final String RESOURCE_TYPE = "resource_type";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "资源名称")
    public static final String RESOURCE_NAME = "resource_name";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "资源键值")
    public static final String RESOURCE_KEY = "resource_key";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "资源数值")
    public static final String RESOURCE_VALUE = "resource_value";

    @Column(type = ColumnType.INT, length = 3, comment = "资源排序")
    public static final String RESOURCE_SORT = "resource_sort";

    
    public String getResource_id() {
        return getStr(RESOURCE_ID);
    }

    public void setResource_id(String resource_id) {
        set(RESOURCE_ID, resource_id);
    }

    public String getMenu_id() {
        return getStr(MENU_ID);
    }

    public void setMenu_id(String menu_id) {
        set(MENU_ID, menu_id);
    }

    public String getResource_type() {
        return getStr(RESOURCE_TYPE);
    }

    public void setResource_type(String resource_type) {
        set(RESOURCE_TYPE, resource_type);
    }

    public String getResource_name() {
        return getStr(RESOURCE_NAME);
    }

    public void setResource_name(String resource_name) {
        set(RESOURCE_NAME, resource_name);
    }

    public String getResource_key() {
        return getStr(RESOURCE_KEY);
    }

    public void setResource_key(String resource_key) {
        set(RESOURCE_KEY, resource_key);
    }

    public String getResource_value() {
        return getStr(RESOURCE_VALUE);
    }

    public void setResource_value(String resource_value) {
        set(RESOURCE_VALUE, resource_value);
    }

    public String getResource_sort() {
        return getStr(RESOURCE_SORT);
    }

    public void setResource_sort(String resource_sort) {
        set(RESOURCE_SORT, resource_sort);
    }
}
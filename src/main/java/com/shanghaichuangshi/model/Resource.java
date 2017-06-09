package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnTypeEnum;

public class Resource extends Model<Resource> {

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "资源编号")
    public static final String RESOURCE_ID = "resource_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "分类编号")
    public static final String CATEGORY_ID = "category_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 10, comment = "资源类型")
    public static final String RESOURCE_TYPE = "resource_type";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 20, comment = "资源名称")
    public static final String RESOURCE_NAME = "resource_name";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 200, comment = "资源键值")
    public static final String RESOURCE_KEY = "resource_key";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 200, comment = "资源数值")
    public static final String RESOURCE_VALUE = "resource_value";

    @Column(type = ColumnTypeEnum.INT, length = 3, comment = "资源排序")
    public static final String RESOURCE_SORT = "resource_sort";

    
    public String getResource_id() {
        return getStr(RESOURCE_ID);
    }

    public void setResource_id(String resource_id) {
        set(RESOURCE_ID, resource_id);
    }

    public String getCategory_id() {
        return getStr(CATEGORY_ID);
    }

    public void setCategory_id(String category_id) {
        set(CATEGORY_ID, category_id);
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

    public Integer getResource_sort() {
        return getInt(RESOURCE_SORT);
    }

    public void setResource_sort(Integer resource_sort) {
        set(RESOURCE_SORT, resource_sort);
    }
}
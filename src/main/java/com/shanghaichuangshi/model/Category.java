package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnTypeEnum;

public class Category extends Model<Category> {

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "分类编号")
    public static final String CATEGORY_ID = "category_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 32, comment = "分类父编号", findable = false, updatable = false)
    public static final String PARENT_ID = "parent_id";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 20, comment = "分类名称")
    public static final String CATEGORY_NAME = "category_name";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 100, comment = "分类键值")
    public static final String CATEGORY_KEY = "category_key";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 100, comment = "分类数值")
    public static final String CATEGORY_VALUE = "category_value";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 5000, comment = "分类路径", findable = false, updatable = false)
    public static final String CATEGORY_PATH = "category_path";

    @Column(type = ColumnTypeEnum.VARCHAR, length = 100, comment = "分类描述")
    public static final String CATEGORY_REMARK = "category_remark";

    @Column(type = ColumnTypeEnum.INT, length = 3, comment = "分类排序")
    public static final String CATEGORY_SORT = "category_sort";

    public String getCategory_id() {
        return getStr(CATEGORY_ID);
    }

    public void setCategory_id(String category_id) {
        set(CATEGORY_ID, category_id);
    }

    public String getParent_id() {
        return getStr(PARENT_ID);
    }

    public void setParent_id(String parent_id) {
        set(PARENT_ID, parent_id);
    }

    public String getCategory_name() {
        return getStr(CATEGORY_NAME);
    }

    public void setCategory_name(String category_name) {
        set(CATEGORY_NAME, category_name);
    }

    public String getCategory_key() {
        return getStr(CATEGORY_KEY);
    }

    public void setCategory_key(String category_key) {
        set(CATEGORY_KEY, category_key);
    }

    public String getCategory_value() {
        return getStr(CATEGORY_VALUE);
    }

    public void setCategory_value(String category_value) {
        set(CATEGORY_VALUE, category_value);
    }

    public String getCategory_path() {
        return getStr(CATEGORY_PATH);
    }

    public void setCategory_path(String category_path) {
        set(CATEGORY_PATH, category_path);
    }

    public String getCategory_remark() {
        return getStr(CATEGORY_REMARK);
    }

    public void setCategory_remark(String category_remark) {
        set(CATEGORY_REMARK, category_remark);
    }

    public Integer getCategory_sort() {
        return getInt(CATEGORY_SORT);
    }

    public void setCategory_sort(Integer category_sort) {
        set(CATEGORY_SORT, category_sort);
    }

}
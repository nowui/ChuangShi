package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class Role extends Model<Role> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "角色编号")
    public static final String ROLE_ID = "role_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类编号")
    public static final String CATEGORY_ID = "category_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "角色名称")
    public static final String ROLE_NAME = "role_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "角色键值")
    public static final String ROLE_KEY = "role_key";

    @Column(type = ColumnType.INT, length = 3, comment = "角色排序")
    public static final String ROLE_SORT = "role_sort";

    
    public String getRole_id() {
        return getStr(ROLE_ID);
    }

    public void setRole_id(String role_id) {
        set(ROLE_ID, role_id);
    }

    public String getCategory_id() {
        return getStr(CATEGORY_ID);
    }

    public void setCategory_id(String category_id) {
        set(CATEGORY_ID, category_id);
    }

    public String getRole_name() {
        return getStr(ROLE_NAME);
    }

    public void setRole_name(String role_name) {
        set(ROLE_NAME, role_name);
    }

    public String getRole_key() {
        return getStr(ROLE_KEY);
    }

    public void setRole_key(String role_key) {
        set(ROLE_KEY, role_key);
    }

    public Integer getRole_sort() {
        return getInt(ROLE_SORT);
    }

    public void setRole_sort(Integer role_sort) {
        set(ROLE_SORT, role_sort);
    }
}
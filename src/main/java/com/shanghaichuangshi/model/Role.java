package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class Role extends Model<Role> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "角色编号")
    public static final String ROLE_ID = "role_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "组织架构编号")
    public static final String ORGANIZATION_ID = "organization_id";

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

    public String getOrganization_id() {
        return getStr(ORGANIZATION_ID);
    }

    public void setOrganization_id(String organization_id) {
        set(ORGANIZATION_ID, organization_id);
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

    public String getRole_sort() {
        return getStr(ROLE_SORT);
    }

    public void setRole_sort(String role_sort) {
        set(ROLE_SORT, role_sort);
    }
}
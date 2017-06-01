package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.type.ColumnType;

public class RoleResource extends Model<RoleResource> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "角色资源编号")
    public static final String ROLE_RESOURCE_ID = "role_resource_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "角色编号")
    public static final String ROLE_ID = "role_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "资源编号")
    public static final String RESOURCE_ID = "resource_id";

    public static final String ROLE_KEY = "role_key";
    public static final String RESOURCE_VALUE = "resource_value";

    public String getRole_resource_id() {
        return getStr(ROLE_RESOURCE_ID);
    }

    public void setRole_resource_id(String role_resource_id) {
        set(ROLE_RESOURCE_ID, role_resource_id);
    }
    public String getRole_id() {
        return getStr(ROLE_ID);
    }

    public void setRole_id(String role_id) {
        set(ROLE_ID, role_id);
    }
    public String getResource_id() {
        return getStr(RESOURCE_ID);
    }

    public void setResource_id(String resource_id) {
        set(RESOURCE_ID, resource_id);
    }

    public String getResource_value() {
        return getStr(RESOURCE_VALUE);
    }

    public void setResource_value(String resource_value) {
        set(RESOURCE_VALUE, resource_value);
    }

}
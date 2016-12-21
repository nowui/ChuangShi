package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColumnType;

public class User extends Model<User> {

    @Table()
    public static final String TABLE_USER = "table_user";

    @Id
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "用户电话")
    public static final String USER_PHONE = "user_phone";


    public String getUser_id() {
        return "";
    }

    public void setUser_id(String user_id) {

    }

}

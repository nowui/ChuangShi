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

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户帐号")
    public static final String USER_ACCOUNT = "user_account";


    public String getUser_id() {
        return getString(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getUser_account() {
        return getString(USER_ACCOUNT);
    }

    public void setUser_account(String user_account) {
        set(USER_ACCOUNT, user_account);
    }

}

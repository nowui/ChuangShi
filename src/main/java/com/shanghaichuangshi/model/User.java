package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColumnType;

@Table("table_user")
public class User extends Model<User> {

    @Id
    @Column(type = ColumnType.VARCHAR, width = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, width = 100, comment = "用户电话")
    public static final String USER_PHONE = "user_phone";

}

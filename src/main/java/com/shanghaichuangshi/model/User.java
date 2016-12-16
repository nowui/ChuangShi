package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Key;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColType;

@Table("table_user")
public class User extends Model<User> {

    @Key
    @Column(type = ColType.VARCHAR, width = 32, comment = "用户编号")
    public static final String COLUMN_USER_ID = "user_id";

    @Column(type = ColType.VARCHAR, width = 100, comment = "用户名称")
    public static final String COLUMN_USER_NAME = "user_name";

}

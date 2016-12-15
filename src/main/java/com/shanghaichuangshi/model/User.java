package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.ColDefine;
import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Comment;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColType;

@Table("table_user")
public class User extends Model<User> {

    @Column
    @Comment("用户编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    public static final String COLUMN_USER_ID = "user_id";

    @Column
    @Comment("用户名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    public static final String COLUMN_USER_NAME = "user_name";

}

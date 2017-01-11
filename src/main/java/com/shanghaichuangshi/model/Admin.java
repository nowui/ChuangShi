package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColumnType;

public class Admin extends Model<Admin> {

    @Table()
    public static final String TABLE_ADMIN = "table_admin";

    @Id
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "管理员编号")
    public static final String ADMIN_ID = "admin_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "管理员名称")
    public static final String ADMIN_NAME = "admin_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

          public String getAdmin_id() {
              return getString(ADMIN_ID);
          }

          public void setAdmin_id(String admin_id) {
              set(ADMIN_ID, admin_id);
          }

          public String getAdmin_name() {
              return getString(ADMIN_NAME);
          }

          public void setAdmin_name(String admin_name) {
              set(ADMIN_NAME, admin_name);
          }

          public String getUser_id() {
              return getString(USER_ID);
          }

          public void setUser_id(String user_id) {
              set(USER_ID, user_id);
          }

}
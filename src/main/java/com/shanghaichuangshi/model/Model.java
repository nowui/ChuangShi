package com.shanghaichuangshi.model;

import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.type.ColumnType;
import com.shanghaichuangshi.util.Util;

import java.util.Date;

public class Model<M extends Model> extends com.jfinal.plugin.activerecord.Model<M> {

    private static final long serialVersionUID = 1L;

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String SYSTEM_CREATE_USER_ID = "system_create_user_id";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.DATETIME, length = 0, comment = "")
    public static final String SYSTEM_CREATE_TIME = "system_create_time";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String SYSTEM_UPDATE_USER_ID = "system_update_user_id";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.DATETIME, length = 0, comment = "")
    public static final String SYSTEM_UPDATE_TIME = "system_update_time";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.BOOLEAN, length = 0, comment = "")
    public static final String SYSTEM_STATUS = "system_status";

    public static final String M = "m";

    public static final String N = "n";

//    private String request_user_id;

    public void setSystem_create_user_id(String system_create_user_id) {
        set(SYSTEM_CREATE_USER_ID, system_create_user_id);
    }

    public void setSystem_create_time(Date system_create_time) {
        set(SYSTEM_CREATE_TIME, system_create_time);
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        set(SYSTEM_UPDATE_USER_ID, system_update_user_id);
    }

    public void setSystem_update_time(Date system_update_time) {
        set(SYSTEM_UPDATE_TIME, system_update_time);
    }

    public void setSystem_status(Boolean system_status) {
        set(SYSTEM_STATUS, system_status);
    }

//    public String getRequest_user_id() {
//        return request_user_id;
//    }
//
//    public void setRequest_user_id(String request_user_id) {
//        this.request_user_id = request_user_id;
//    }

//    public int getM() {
//        int page_index = getInt(Constant.PAGE_INDEX);
//        int page_size = getInt(Constant.PAGE_SIZE);
//
//        if (page_index > 0) {
//            return (page_index - 1) * page_size;
//        } else {
//            return 1;
//        }
//    }
//
//    public int getN() {
//        int page_size = getInt(Constant.PAGE_SIZE);
//
//        return page_size > 0 ? page_size : 0;
//    }

    public void validate(String... keys) {
        for (String key : keys) {
            if (this.getAttrs().containsKey(key)) {
                if (Util.isNull(this.get(key))) {
                    throw new RuntimeException(key + " is null");
                }


            } else {
                throw new RuntimeException(key + " is null");
            }
        }
    }

    public void removeUnfindable() {

    }

}
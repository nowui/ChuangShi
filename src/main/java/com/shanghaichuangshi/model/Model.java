package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.type.ColumnType;
import com.shanghaichuangshi.util.Util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public class Model<M extends Model> extends com.jfinal.plugin.activerecord.Model<M> implements Serializable {

    private static final long serialVersionUID = 1L;

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.VARCHAR, length = 32, comment = "", findable = false)
    public static final String SYSTEM_CREATE_USER_ID = "system_create_user_id";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.DATETIME, length = 0, comment = "", findable = false)
    public static final String SYSTEM_CREATE_TIME = "system_create_time";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.VARCHAR, length = 32, comment = "", findable = false)
    public static final String SYSTEM_UPDATE_USER_ID = "system_update_user_id";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.DATETIME, length = 0, comment = "", findable = false)
    public static final String SYSTEM_UPDATE_TIME = "system_update_time";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.BOOLEAN, length = 0, comment = "", findable = false)
    public static final String SYSTEM_STATUS = "system_status";

    public static final String M = "m";

    public static final String N = "n";

    private List<Map<String, Object>> columnList;

    public String getSystem_create_user_id() {
        return getStr(SYSTEM_CREATE_USER_ID);
    }

    public void setSystem_create_user_id(String system_create_user_id) {
        set(SYSTEM_CREATE_USER_ID, system_create_user_id);
    }

    public Date getSystem_create_time() {
        return getDate(SYSTEM_CREATE_TIME);
    }

    public void setSystem_create_time(Date system_create_time) {
        set(SYSTEM_CREATE_TIME, system_create_time);
    }

    public String getSystem_update_user_id() {
        return getStr(SYSTEM_UPDATE_USER_ID);
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        set(SYSTEM_UPDATE_USER_ID, system_update_user_id);
    }

    public Date getSystem_update_time() {
        return getDate(SYSTEM_UPDATE_TIME);
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

//    public Map<String, Object> formatToMap() {
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        Iterator<Map.Entry<String, Object>> iterator = this.getAttrs().entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Object> entry = iterator.next();
//
//            boolean isFind = true;
//
//            for (Map<String, Object> column : getColumnList()) {
//                if (column.get(Constant.NAME).toString().equals(entry.getKey())) {
//                    if (!(boolean)column.get(Constant.FINDABLE)) {
//                        isFind = false;
//
//                        break;
//                    }
//                }
//            }
//
//            if (isFind) {
//                result.put(entry.getKey(), entry.getValue());
//            }
//        }
//
//        return result;
//    }
//
//    public Map<String, Object> keepToMap(String... keys) {
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        Iterator<Map.Entry<String, Object>> iterator = this.getAttrs().entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Object> entry = iterator.next();
//
//            boolean isFind = false;
//
//
//            for (String key : keys) {
//                if (key.equals(entry.getKey())) {
//                    isFind = true;
//
//                    break;
//                }
//            }
//
//            if (isFind) {
//                result.put(entry.getKey(), entry.getValue());
//            }
//        }
//
//        return result;
//    }

    public M removeUnfindable() {
        for (Map<String, Object> map : getColumnList()) {
            if (!(boolean)map.get(Constant.FINDABLE)) {
                this.remove(map.get(Constant.NAME).toString());
            }
        }

        return (M) this;
    }

    public M removeSystemInfo() {
        this.remove(SYSTEM_CREATE_USER_ID);
        this.remove(SYSTEM_CREATE_TIME);
        this.remove(SYSTEM_UPDATE_USER_ID);
        this.remove(SYSTEM_UPDATE_TIME);
        this.remove(SYSTEM_STATUS);

        return (M) this;
    }

    private List<Map<String, Object>> getColumnList() {
        if (columnList == null) {
            columnList = new ArrayList<Map<String, Object>>();

            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);

                if (column != null) {
                    Map<String, Object> map = new HashMap<String, Object>();

                    try {
                        map.put(Constant.NAME, field.get(Model.class).toString());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("IllegalAccessException: " + e);
                    }

                    map.put(Constant.FINDABLE, column.findable());

                    columnList.add(map);
                }
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_CREATE_USER_ID);
            map.put(Constant.FINDABLE, false);
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_CREATE_TIME);
            map.put(Constant.FINDABLE, false);
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_UPDATE_USER_ID);
            map.put(Constant.FINDABLE, false);
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_UPDATE_TIME);
            map.put(Constant.FINDABLE, false);
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_STATUS);
            map.put(Constant.FINDABLE, false);
            columnList.add(map);
        }

        return columnList;
    }

}
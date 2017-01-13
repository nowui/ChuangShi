package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.config.Column;
import com.shanghaichuangshi.type.ColumnType;
import com.shanghaichuangshi.util.DatabaseUtil;
import com.shanghaichuangshi.util.IntUtil;
import com.shanghaichuangshi.util.StringUtil;
import com.shanghaichuangshi.util.Util;

import java.lang.reflect.Field;
import java.util.*;

public abstract class Model<M extends Model> extends HashMap<String, Object> {

    private String table_name;
    private String key_id;
    private List<Column> columnList;

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

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.INT, length = 0, comment = "")
    public static final String PAGE_INDEX = "page_index";

    @com.shanghaichuangshi.annotation.Column(type = ColumnType.INT, length = 0, comment = "")
    public static final String PAGE_SIZE = "page_size";

    public String getString(String key) {
        return (String) this.get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) this.get(key);
    }

    public int getInt(String key) {
        return (int) this.get(key);
    }

    public int getM() {
        int page_index = getInt(PAGE_INDEX);
        int page_size = getInt(PAGE_SIZE);

        if (page_index > 0) {
            return (page_index - 1) * page_size;
        } else {
            return 1;
        }
    }

    public int getN() {
        int page_size = getInt(PAGE_SIZE);

        return page_size > 0 ? page_size : 0;
    }

    public String getRequest_user_id() {
        return "";
    }

    public void setRequest_user_id(String request_user_id) {

    }

    private String getTable_name() {
        if (table_name == null) {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Table table = field.getAnnotation(Table.class);
                if (table != null) {
                    try {
                        table_name = field.get(User.class).toString();

                        break;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("IllegalAccessException: " + e);
                    }
                }
            }
        }

        if (table_name == null) {
            throw new RuntimeException("Can not find the table name");
        }

        return table_name;
    }

    private String getKey_id() {
        if (key_id == null) {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Id id = field.getAnnotation(Id.class);
                if (id != null) {
                    try {
                        key_id = field.get(User.class).toString();

                        break;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("IllegalAccessException: " + e);
                    }
                }
            }
        }

        if (key_id == null) {
            throw new RuntimeException("Can not find the key column");
        }

        return key_id;
    }

    private List<Column> getColumnList() {
        if (columnList == null) {
            columnList = new ArrayList<Column>();

            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                com.shanghaichuangshi.annotation.Column columnAnnotation = field.getAnnotation(com.shanghaichuangshi.annotation.Column.class);
                if (columnAnnotation != null) {
                    try {
                        Column column = new Column();
                        column.setType(columnAnnotation.type());
                        column.setWidth(columnAnnotation.length());
                        column.setDefaultValue(columnAnnotation.defaultValue());
                        column.setComment(columnAnnotation.comment());
                        column.setFindable(columnAnnotation.findable());
                        column.setUpdatable(columnAnnotation.updatable());
                        column.setName(field.get(User.class).toString());
                        columnList.add(column);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("IllegalAccessException: " + e);
                    }
                }
            }
        }

        if (columnList.size() == 0) {
            throw new RuntimeException("Can not find the column");
        }

        return columnList;
    }

    public Model set(String key, Object value) {
        this.put(key, value);

        return this;
    }

    public Model set(Map<String, Object> map) {
        this.putAll(map);

        return this;
    }

    public Model keep(String... keys) {
        Iterator<String> iterator = this.keySet().iterator();
        while (iterator.hasNext()) {
            Boolean isNotExit = true;

            String entry = iterator.next();
            for (String key : keys) {
                if (key.equals(entry)) {
                    isNotExit = false;
                }
            }

            if (isNotExit) {
                iterator.remove();
            }
        }

        return this;
    }

    public Model remove(String... keys) {
        for (String key : keys) {
            this.remove(key);
        }

        return this;
    }

    public M removeUnfindable() {
        for (Column column : getColumnList()) {
            if (!column.getFindable()) {
                this.remove(column.getName());
            }
        }

        return (M)this;
    }

    public void validate(String... keys) {
        for (String key : keys) {
            if (this.containsKey(key)) {
                if (Util.isNull(this.get(key))) {
                    throw new RuntimeException(key + " is null");
                }

                for (Column column : getColumnList()) {
                    if (column.getName().equals(key)) {
                        switch (column.getType()) {
                            case VARCHAR:
                                if (!StringUtil.checkLength(this.get(key).toString(), column.getWidth())) {
                                    throw new RuntimeException("The length of the " + key + " is more than " + column.getWidth());
                                }
                                break;
                            case INT:
                                if (!IntUtil.checkLength((Integer) this.get(key), column.getWidth())) {
                                    throw new RuntimeException("The length of the " + key + " is more than " + column.getWidth());
                                }
                            default:
                                break;
                        }
                    }
                }
            } else {
                throw new RuntimeException(key + " is null");
            }
        }


    }

//    public int count(String sql, List<Object> parameterList) {
//        return DatabaseUtil.count(sql, parameterList);
//    }
//
//    public List<M> list(String sql, List<Object> parameterList) {
//        List<Map<String, Object>> resultList = DatabaseUtil.list(sql, parameterList);
//
//        List<M> list = new ArrayList<M>();
//        for (Map<String, Object> map : resultList) {
//            try {
//                list.add((M) getClass().newInstance().set(map));
//            } catch (InstantiationException e) {
//                throw new RuntimeException("InstantiationException:", e);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException("IllegalAccessException:", e);
//            }
//        }
//
//        return list;
//    }
//
//    public M find(String sql, List<Object> parameterList) {
//        Map<String, Object> resultMap = DatabaseUtil.find(sql, parameterList);
//
//        this.clear();
//        set(resultMap);
//
//        return (M) this;
//    }
//
//    private String packageSelectSQL(String... columns) {
//        StringBuffer sql = new StringBuffer();
//
//        if (columns.length > 0) {
//            for (int i = 0; i < columns.length; i++) {
//                if (i > 0) {
//                    sql.append(", ");
//                }
//
//                if (columns[i].contains(".")) {
//                    sql.append(columns[i]);
//                } else {
//                    sql.append(getTable_name()).append(".").append(columns[i]);
//                }
//            }
//            sql.append(" ");
//        } else {
//            sql.append(getTable_name()).append(".* ");
//        }
//
//        return sql.toString();
//    }
//
//    public M findById(String id, String... columns) {
//        StringBuilder sql = new StringBuilder();
//        List<Object> parameterList = new ArrayList<Object>();
//
//        sql.append("SELECT ");
//        sql.append(getTable_name()).append(".* ");
//        sql.append("FROM ").append(getTable_name()).append(" WHERE ").append(getKey_id()).append(" = ? ");
//        parameterList.add(id);
//
//        Map<String, Object> resultMap = DatabaseUtil.find(sql.toString(), parameterList);
//
//        this.clear();
//        set(resultMap);
//
//        return (M) this;
//    }

    public boolean save() {
        StringBuilder sql = new StringBuilder();
        StringBuilder temp = new StringBuilder(") VALUES (");
        List<Object> parameterList = new ArrayList<Object>();

        sql.append("INSERT INTO ").append(getTable_name()).append(" (");

        for (Entry<String, Object> entry : this.entrySet()) {
            for (Column column : getColumnList()) {
                if (entry.getKey().equals(column.getName())) {
                    sql.append(entry.getKey()).append(", ");
                    temp.append("?, ");
                    parameterList.add(entry.getValue());
                }
            }
        }

        sql.append(SYSTEM_CREATE_USER_ID).append(", ");
        temp.append("?, ");
        parameterList.add(getRequest_user_id());

        sql.append(SYSTEM_CREATE_TIME).append(", ");
        temp.append("?, ");
        parameterList.add(new Date());

        sql.append(SYSTEM_UPDATE_USER_ID).append(", ");
        temp.append("?, ");
        parameterList.add(getRequest_user_id());

        sql.append(SYSTEM_UPDATE_TIME).append(", ");
        temp.append("?, ");
        parameterList.add(new Date());

        sql.append(SYSTEM_STATUS);
        temp.append("?");
        parameterList.add(true);


        sql.append(temp.toString());
        sql.append(")");

       return DatabaseUtil.update(sql.toString(), parameterList);
    }

    public boolean update() {
        StringBuilder sql = new StringBuilder();
        List<Object> parameterList = new ArrayList<Object>();

        sql.append("UPDATE ").append(getTable_name()).append(" SET ");

        Iterator<String> iterator = this.keySet().iterator();
        while (iterator.hasNext()) {
            String entry = iterator.next();

            if (entry.equals(SYSTEM_CREATE_USER_ID) || entry.equals(SYSTEM_CREATE_TIME) || entry.equals(SYSTEM_UPDATE_USER_ID) || entry.equals(SYSTEM_UPDATE_TIME)) {
                iterator.remove();
            }
        }

        for (Entry<String, Object> entry : this.entrySet()) {
            for (Column column : getColumnList()) {
                if (entry.getKey().equals(column.getName()) && !column.getName().equals(getKey_id()) && column.getUpdatable()) {
                    sql.append(entry.getKey()).append(" = ?, ");
                    parameterList.add(entry.getValue());
                }
            }
        }

        sql.append(SYSTEM_UPDATE_USER_ID).append(" = ?, ");
        parameterList.add(getRequest_user_id());

        sql.append(SYSTEM_UPDATE_TIME).append(" = ? ");
        parameterList.add(new Date());

        sql.append("WHERE ");

        String value = get(getKey_id()).toString();
        if (value == null) {
            throw new RuntimeException("Can not find the id");
        }

        sql.append(getKey_id()).append(" = ? ");
        parameterList.add(value);

        return DatabaseUtil.update(sql.toString(), parameterList);
    }

    public boolean delete() {
        StringBuilder sql = new StringBuilder();
        List<Object> parameterList = new ArrayList<Object>();

        sql.append("UPDATE ").append(getTable_name()).append(" SET ");

        sql.append(SYSTEM_UPDATE_USER_ID).append(" = ?, ");
        parameterList.add(getRequest_user_id());

        sql.append(SYSTEM_UPDATE_TIME).append(" = ?, ");
        parameterList.add(new Date());

        sql.append(SYSTEM_STATUS).append(" = ? ");
        parameterList.add(false);

        sql.append("WHERE ");

        String value = get(getKey_id()).toString();
        if (value == null) {
            throw new RuntimeException("Can not find the id");
        }

        sql.append(getKey_id()).append(" = ? ");
        parameterList.add(value);

        return DatabaseUtil.update(sql.toString(), parameterList);
    }

}

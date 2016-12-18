package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.config.Column;
import com.shanghaichuangshi.util.DatabaseUtil;

import java.lang.reflect.Field;
import java.util.*;

public abstract class Model<M extends Model> extends HashMap<String, Object> {

    private String table_name;
    private String key_id;
    private List<Column> columnList;

    private String getTable_name() {
        if (table_name == null) {
            Table table = this.getClass().getAnnotation(Table.class);
            table_name = table.value();

            columnList = new ArrayList<Column>();

            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                com.shanghaichuangshi.annotation.Column columnAnnotation = field.getAnnotation(com.shanghaichuangshi.annotation.Column.class);
                if (columnAnnotation != null) {
                    Id key = field.getAnnotation(Id.class);

                    Column column = new Column();
                    column.setType(columnAnnotation.type().getKey());
                    column.setWidth(columnAnnotation.width());
                    column.setDefaultValue(columnAnnotation.defaultValue());
                    column.setComment(columnAnnotation.comment());

                    try {
                        column.setName(field.get(User.class).toString());
                        columnList.add(column);
                        if (key != null) {
                            key_id = column.getName();
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("IllegalAccessException: " + e);
                    }
                }
            }

            if (table_name == null) {
                throw new RuntimeException("Can not find the table");
            }

            if (key_id == null) {
                throw new RuntimeException("Can not find the key column");
            }

            if (columnList.size() == 0) {
                throw new RuntimeException("Can not find the column");
            }
        }

        return table_name;
    }

    private List<Column> getColumnList() {
        if (columnList == null) {
            getTable_name();
        }

        return columnList;
    }

    private String getKey_id() {
        if (key_id == null) {
            getTable_name();
        }

        return key_id;
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

    public void validate(String... keys) {
        for (String key : keys) {
            if (this.containsKey(key)) {

            } else {
                throw new RuntimeException(key + " is empty");
            }
        }


    }

    public List<M> list(String sql, List<Object> parameterList) {
        List<Map<String, Object>> resultList = DatabaseUtil.list(sql, parameterList);

        List<M> list = new ArrayList<M>();
        for (Map<String, Object> map : resultList) {
            try {
                list.add((M) getClass().newInstance().set(map));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public M find(String sql, List<Object> parameterList) {
        Map<String, Object> resultMap = DatabaseUtil.find(sql, parameterList);
        set(resultMap);

        return (M) this;
    }

    public M findById(String id) {
        StringBuilder sql = new StringBuilder();
        List<Object> parameterList = new ArrayList<Object>();

        sql.append("SELECT * FROM ").append(getTable_name()).append(" WHERE ").append(getKey_id()).append(" = ? ");
        parameterList.add(id);

        Map<String, Object> resultMap = DatabaseUtil.find(sql.toString(), parameterList);
        set(resultMap);

        return (M) this;
    }

    public boolean save() {
        StringBuilder sql = new StringBuilder();
        StringBuilder temp = new StringBuilder(") VALUES (");
        List<Object> parameterList = new ArrayList<Object>();

        sql.append("INSERT INTO ").append(getTable_name()).append(" (");

        for (Entry<String, Object> entry : this.entrySet()) {
            for (Column column : getColumnList()) {
                if (entry.getKey().equals(column.getName())) {
                    if (parameterList.size() > 0) {
                        sql.append(", ");
                        temp.append(", ");
                    }
                    sql.append(entry.getKey());
                    temp.append("?");
                    parameterList.add(entry.getValue());
                }
            }
        }



        sql.append(temp.toString());
        sql.append(")");

        System.out.println(sql.toString());

//        return DatabaseUtil.update(sql.toString(), parameterList);
        return true;
    }

    public boolean update() {
        StringBuilder sql = new StringBuilder();
        List<Object> parameterList = new ArrayList<Object>();

        sql.append("UPDATE ").append(getTable_name()).append(" SET ");

        for (Entry<String, Object> entry : this.entrySet()) {
            for (Column column : getColumnList()) {
                if (entry.getKey().equals(column.getName()) && ! column.getName().equals(getKey_id())) {
                    if (parameterList.size() > 0) {
                        sql.append(", ");
                    }
                    sql.append(entry.getKey()).append(" = ?");
                    parameterList.add(entry.getValue());
                }
            }
        }

        sql.append(" WHERE ");

        String value = get(getKey_id()).toString();
        if (value == null) {
            throw new RuntimeException("Can not find the id value");
        }

        sql.append(getKey_id()).append(" = ? ");
        parameterList.add(value);

        System.out.println(sql.toString());

//        return DatabaseUtil.update(sql.toString(), parameterList);
        return true;
    }

}

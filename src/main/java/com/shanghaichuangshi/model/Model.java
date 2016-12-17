package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.util.DatabaseUtil;

import java.lang.reflect.Field;
import java.util.*;

public abstract class Model<M extends Model> extends HashMap<String, Object> {

    private List<String> columnList;
    private String id;

    private List<String> getColumnList() {
        if (columnList == null) {
            columnList = new ArrayList<String>();

            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    Id key = field.getAnnotation(Id.class);

                    try {
                        String columnValue = field.get(User.class).toString();
                        columnList.add(columnValue);
                        if (key != null) {
                            id = columnValue;
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("IllegalAccessException: " + e);
                    }
                }
            }

            if (columnList.size() == 0) {
                throw new RuntimeException("Can not find the column");
            }

            if (id == null) {
                throw new RuntimeException("Can not find the key column");
            }
        }

        return columnList;
    }

    private String getId() {
        if (id == null) {
            getColumnList();
        }

        return id;
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

    public boolean save() {
        StringBuilder sql = new StringBuilder();
        StringBuilder temp = new StringBuilder(") VALUES (");
        List<Object> parameterList = new ArrayList<Object>();

        Table table = this.getClass().getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("Can not find the table");
        }

        sql.append("INSERT INTO ").append(table.value()).append(" (");

        for (Entry<String, Object> entry : this.entrySet()) {
            for (String column : getColumnList()) {
                if (entry.getKey().equals(column)) {
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

        Table table = this.getClass().getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("Can not find the table");
        }

        sql.append("UPDATE ").append(table.value()).append(" SET ");

        for (Entry<String, Object> entry : this.entrySet()) {
            for (String column : getColumnList()) {
                if (entry.getKey().equals(column) && ! column.equals(getId())) {
                    if (parameterList.size() > 0) {
                        sql.append(", ");
                    }
                    sql.append(entry.getKey()).append(" = ?");
                    parameterList.add(entry.getValue());
                }
            }
        }

        sql.append(" WHERE ");

        String value = get(getId()).toString();
        if (value == null) {
            throw new RuntimeException("Can not find the id value");
        }

        sql.append(getId()).append(" = ? ");
        parameterList.add(value);

        System.out.println(sql.toString());

//        return DatabaseUtil.update(sql.toString(), parameterList);
        return true;
    }

}

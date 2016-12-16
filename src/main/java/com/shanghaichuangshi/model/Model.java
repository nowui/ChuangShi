package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.util.DatabaseUtil;

import java.lang.reflect.Field;
import java.util.*;

public abstract class Model<M extends Model> extends HashMap<String, Object> {

    public Model get() {
        return this;
    }

    public Model set(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }

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
        for(Map<String, Object> map : resultList) {
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

        return (M)this;
    }

    public boolean save() {
        StringBuilder sql = new StringBuilder();
        List<Object> parameterList = new ArrayList<Object>();

        Table table = this.getClass().getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("Can not find table");
        }

        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields) {
            Column column = field.getAnnotation(Column.class);

            if (column != null) {
                try {
                    System.out.println(field.getName() + " " + field.get(User.class));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        sql.append("INSERT INTO ").append(table.value()).append(" (");

        sql.append(") VALUES (");

        sql.append(")");

        System.out.println(sql.toString());

        return true;
    }

    public boolean update() {


        return true;
    }

}

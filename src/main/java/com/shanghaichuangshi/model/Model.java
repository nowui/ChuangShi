package com.shanghaichuangshi.model;

import com.shanghaichuangshi.util.DatabaseUtil;

import java.util.*;

public abstract class Model<M extends HashMap<String, Object>> extends HashMap<String, Object> {

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

        List<M> modelList = new ArrayList<M>();
        for(Map<String, Object> map : resultList) {
            modelList.add((M)map);
        }

        return modelList;
    }

    public Model find(String sql, List<Object> parameterList) {
        Map<String, Object> resultMap = DatabaseUtil.find(sql, parameterList);

        set(resultMap);

        return this;
    }

}

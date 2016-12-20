package com.shanghaichuangshi.config;

import java.util.ArrayList;
import java.util.List;

public class DynamicSQL {

    private StringBuilder sql = new StringBuilder();
    private List<Object> parameterList = new ArrayList<Object>();

    public String getSql() {
        return sql.toString();
    }

    public List<Object> getParameterList() {
        return parameterList;
    }

    public DynamicSQL append(String string) {
        this.sql.append(string);
        return this;
    }

    public DynamicSQL append(String string, Object... objects) {
        this.sql.append(string);

        for (Object object : objects) {
            this.parameterList.add(object);
        }
        return this;
    }

    public DynamicSQL appendPagination(Integer m, Integer n) {
        if (n > 0) {
            this.sql.append("sLIMIT ?, ? ");
            this.parameterList.add(m);
            this.parameterList.add(n);
        }
        return this;
    }
}
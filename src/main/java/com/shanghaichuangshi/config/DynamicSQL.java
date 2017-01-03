package com.shanghaichuangshi.config;

import com.shanghaichuangshi.util.Util;

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

    public DynamicSQL append(String string, Object... objects) {
        this.sql.append(string);

        for (Object object : objects) {
            this.parameterList.add(object);
        }

        return this;
    }


//    public DynamicSQL appendIsNullOrEmpty(String string, Object object) {
//        if (!Util.isNullOrEmpty(object)) {
//            this.sql.append(string);
//
//            this.parameterList.add(object);
//        }
//
//        return this;
//    }
//
//    public DynamicSQL appendLike(String string, Object object) {
//        this.sql.append(string);
//
//        this.parameterList.add("%" + object + "%");
//
//        return this;
//    }
//
//    public DynamicSQL appendLikeIsNullOrEmpty(String string, Object object) {
//        if (!Util.isNullOrEmpty(object)) {
//            this.sql.append(string);
//
//            this.parameterList.add("%" + object + "%");
//        }
//
//        return this;
//    }
//
//    public DynamicSQL appendPagination(Integer m, Integer n) {
//        this.sql.append("LIMIT ?, ? ");
//        this.parameterList.add(m);
//        this.parameterList.add(n);
//
//        return this;
//    }
}

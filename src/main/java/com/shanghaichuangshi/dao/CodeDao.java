package com.shanghaichuangshi.dao;

import com.shanghaichuangshi.config.DynamicSQL;
import com.shanghaichuangshi.model.Code;

import java.util.List;

public class CodeDao extends Dao {

    public List<Code> listTable(String table_name) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append("table_name ");
        dynamicSQL.append("FROM information_schema.tables ");
        dynamicSQL.append("WHERE table_schema='HongLuoMeng' ");
        dynamicSQL.appendLikeIsNullOrEmpty("AND table_name LIKE ? ", table_name);

        return new Code().list(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

    public List<Code> listColumn(String table_name) {
        DynamicSQL dynamicSQL = new DynamicSQL();

        dynamicSQL.append("SELECT ");
        dynamicSQL.append("column_name, data_type, character_maximum_length, column_comment ");
        dynamicSQL.append("FROM information_schema.columns ");
        dynamicSQL.append("WHERE table_schema='HongLuoMeng' ");
        dynamicSQL.appendIsNullOrEmpty("AND table_name = ? ", table_name);

        return new Code().list(dynamicSQL.getSql(), dynamicSQL.getParameterList());
    }

}

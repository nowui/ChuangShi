package com.shanghaichuangshi.dao;

import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.shanghaichuangshi.constant.Jdbc;

import java.util.List;

public class CodeDao extends Dao {

    public List<Record> listTable() {
        JMap map = JMap.create();
        map.put("table_schema", Jdbc.table_schema);
        SqlPara sqlPara = Db.getSqlPara("code.listTable", map);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Record> listColumn(String table_name) {
        JMap map = JMap.create();
        map.put("table_name", table_name);
        map.put("table_schema", Jdbc.table_schema);
        SqlPara sqlPara = Db.getSqlPara("code.listColumn", map);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

}

package com.shanghaichuangshi.service;

import com.jfinal.plugin.activerecord.Record;
import com.shanghaichuangshi.dao.CodeDao;

import java.util.List;

public class CodeService extends Service {

    private final CodeDao codeDao = new CodeDao();

    public List<Record> listTable(String table_schema) {
        return codeDao.listTable(table_schema);
    }

    public List<Record> listColumn(String table_name, String table_schema) {
        return codeDao.listColumn(table_name, table_schema);
    }
}

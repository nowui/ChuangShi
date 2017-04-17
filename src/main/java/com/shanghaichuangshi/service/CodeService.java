package com.shanghaichuangshi.service;

import com.jfinal.plugin.activerecord.Record;
import com.shanghaichuangshi.dao.CodeDao;

import java.util.List;

public class CodeService extends Service {

    private final CodeDao codeDao = new CodeDao();

    public List<Record> listTable() {
        return codeDao.listTable();
    }

    public List<Record> listColumn(String table_name) {
        return codeDao.listColumn(table_name);
    }
}

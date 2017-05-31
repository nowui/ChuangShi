package com.shanghaichuangshi.cache;

import com.jfinal.plugin.activerecord.Record;
import com.shanghaichuangshi.dao.CodeDao;

import java.util.List;

public class CodeCache extends Cache {

    private CodeDao codeDao = new CodeDao();

    public List<Record> listTable() {
        return codeDao.listTable();
    }

    public List<Record> listColumn(String table_name) {
        return codeDao.listColumn(table_name);
    }

}

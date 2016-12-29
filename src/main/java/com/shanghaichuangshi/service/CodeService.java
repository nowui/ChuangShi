package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.CodeDao;
import com.shanghaichuangshi.model.Code;

import java.util.List;

public class CodeService extends Service {

    private final CodeDao codeDao = new CodeDao();

    public List<Code> listTable(String table_name) {
        return codeDao.listTable(table_name);
    }

    public List<Code> listColumn(String table_name) {
        return codeDao.listColumn(table_name);
    }
}

package com.shanghaichuangshi.service;

import com.jfinal.plugin.activerecord.Record;
import com.shanghaichuangshi.cache.CodeCache;

import java.util.List;

public class CodeService extends Service {

    private final CodeCache codeCache = new CodeCache();

    public List<Record> listTable() {
        return codeCache.listTable();
    }

    public List<Record> listColumn(String table_name) {
        return codeCache.listColumn(table_name);
    }
}

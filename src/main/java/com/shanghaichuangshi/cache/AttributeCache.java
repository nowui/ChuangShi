package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.AttributeDao;
import com.shanghaichuangshi.model.Attribute;
import com.shanghaichuangshi.util.CacheUtil;

import java.util.List;

public class AttributeCache extends Cache {

    public static final String ATTRIBUTE_BY_ATTRIBUTE_ID_CACHE = "attribute_by_attribute_id_cache";

    private AttributeDao attributeDao = new AttributeDao();

    public int count(String attribute_name) {
        return attributeDao.count(attribute_name);
    }

    public List<Attribute> list(String attribute_name, Integer m, Integer n) {
        return attributeDao.list(attribute_name, m, n);
    }

    public Attribute find(String attribute_id) {
        Attribute attribute = CacheUtil.get(ATTRIBUTE_BY_ATTRIBUTE_ID_CACHE, attribute_id);

        if (attribute == null) {
            attribute = attributeDao.find(attribute_id);

            CacheUtil.put(ATTRIBUTE_BY_ATTRIBUTE_ID_CACHE, attribute_id, attribute);
        }

        return attribute;
    }

    public Attribute save(Attribute attribute, String request_user_id) {
        return attributeDao.save(attribute, request_user_id);
    }

    public boolean update(Attribute attribute, String request_user_id) {
        CacheUtil.remove(ATTRIBUTE_BY_ATTRIBUTE_ID_CACHE, attribute.getAttribute_id());

        return attributeDao.update(attribute, request_user_id);
    }

    public boolean delete(String attribute_id, String request_user_id) {
        CacheUtil.remove(ATTRIBUTE_BY_ATTRIBUTE_ID_CACHE, attribute_id);

        return attributeDao.delete(attribute_id, request_user_id);
    }

}
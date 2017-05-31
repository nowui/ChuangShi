package com.shanghaichuangshi.service;

import com.shanghaichuangshi.cache.AttributeCache;
import com.shanghaichuangshi.model.Attribute;

import java.util.List;

public class AttributeService extends Service {

    private final AttributeCache attributeCache = new AttributeCache();

    public int count(String attribute_name) {
        return attributeCache.count(attribute_name);
    }

    public List<Attribute> list(String attribute_name, int m, int n) {
        return attributeCache.list(attribute_name, m, n);
    }

    public Attribute find(String attribute_id) {
        return attributeCache.find(attribute_id);
    }

    public Attribute save(Attribute attribute, String request_user_id) {
        return attributeCache.save(attribute, request_user_id);
    }

    public boolean update(Attribute attribute, String request_user_id) {
        return attributeCache.update(attribute, request_user_id);
    }

    public boolean delete(Attribute attribute, String request_user_id) {
        return attributeCache.delete(attribute.getAttribute_id(), request_user_id);
    }

}
package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.AttributeDao;
import com.shanghaichuangshi.model.Attribute;

import java.util.List;

public class AttributeService extends Service {

    private final AttributeDao attributeDao = new AttributeDao();

    public int count(Attribute attribute) {
        return attributeDao.count(attribute.getAttribute_name());
    }

    public List<Attribute> list(Attribute attribute, int m, int n) {
        return attributeDao.list(attribute.getAttribute_name(), m, n);
    }

    public Attribute find(String attribute_id) {
        return attributeDao.find(attribute_id);
    }

    public Attribute save(Attribute attribute, String request_user_id) {
        return attributeDao.save(attribute, request_user_id);
    }

    public boolean update(Attribute attribute, String request_user_id) {
        return attributeDao.update(attribute, request_user_id);
    }

    public boolean delete(Attribute attribute, String request_user_id) {
        return attributeDao.delete(attribute.getAttribute_id(), request_user_id);
    }

}
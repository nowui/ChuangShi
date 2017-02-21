package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.AttributeDao;
import com.shanghaichuangshi.model.Attribute;

import java.util.List;

public class AttributeService extends Service {

    private static final AttributeDao attributeDao = new AttributeDao();

    public int count(Attribute attribute) {
        return attributeDao.count(attribute.getAttribute_name());
    }

    public List<Attribute> list(Attribute attribute, int m, int n) {
        return attributeDao.list(attribute.getAttribute_name(), m, n);
    }

    public Attribute find(Attribute attribute) {
        return attributeDao.find(attribute.getAttribute_id());
    }

    public void save(Attribute attribute, String request_user_id) {
        attributeDao.save(attribute, request_user_id);
    }

    public void update(Attribute attribute, String request_user_id) {
        attributeDao.update(attribute, request_user_id);
    }

    public void delete(Attribute attribute, String request_user_id) {
        attributeDao.delete(attribute.getAttribute_id(), request_user_id);
    }

}
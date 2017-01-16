package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.AttributeDao;
import com.shanghaichuangshi.model.Attribute;

import java.util.List;

public class AttributeService extends Service {

    private final AttributeDao attributeDao = new AttributeDao();

    public int count(Attribute attribute) {
        return attributeDao.count();
    }

    public List<Attribute> list(Attribute attribute) {
        return attributeDao.list(attribute.getAttribute_name(), attribute.getM(), attribute.getN());
    }

    public Attribute find(Attribute attribute) {
        return attributeDao.find(attribute.getAttribute_id());
    }

    public void save(Attribute attribute) {
        attributeDao.save(attribute);
    }

    public void update(Attribute attribute) {
        attributeDao.update(attribute);
    }

    public void delete(Attribute attribute) {
        attributeDao.delete(attribute);
    }

}
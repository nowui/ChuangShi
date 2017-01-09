package com.shanghaichuangshi.service;

import com.shanghaichuangshi.dao.CategoryDao;
import com.shanghaichuangshi.model.Category;

import java.util.List;

public class CategoryService extends Service {

    private final CategoryDao categoryDao = new CategoryDao();

    public int count(Category category) {
        return categoryDao.count();
    }

    public List<Category> list(Category category) {
        return categoryDao.list(category.getCategory_name(), category.getM(), category.getN());
    }

    public Category find(Category category) {
        return categoryDao.find(category.getCategory_id());
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public void delete(Category category) {
        categoryDao.delete(category);
    }

}
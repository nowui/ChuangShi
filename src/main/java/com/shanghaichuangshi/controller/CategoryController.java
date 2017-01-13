package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Key;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.service.CategoryService;

import java.util.List;

public class CategoryController extends Controller {

    private final CategoryService categoryService = new CategoryService();

    @Path(Url.CATEGORY_ADMIN_LIST)
    public void adminList() {
        Category categoryModel = getModel(Category.class);

        categoryModel.validate(Category.PAGE_INDEX, Category.PAGE_SIZE);

        int count = categoryService.count(categoryModel);

        List<Category> categoryList = categoryService.list(categoryModel);

        renderJson(count, categoryList);
    }

    @Path(Url.CATEGORY_ADMIN_TREE_LIST)
    public void adminTreeList() {
        Category categoryModel = getModel(Category.class);

        categoryModel.validate(Category.CATEGORY_ID);

        Category category = categoryService.treeList(categoryModel);

        category.keep(Category.CATEGORY_ID, Category.CATEGORY_NAME, Key.CHILDREN);

        renderJson(category);
    }

    @Path(Url.CATEGORY_FIND)
    public void find() {
        Category categoryModel = getModel(Category.class);

        categoryModel.validate(Category.CATEGORY_ID);

        Category category = categoryService.find(categoryModel);

        category.removeUnfindable();

        renderJson(category);
    }

    @Path(Url.CATEGORY_ADMIN_FIND)
    public void adminFind() {
        Category categoryModel = getModel(Category.class);

        categoryModel.validate(Category.CATEGORY_ID);

        Category category = categoryService.find(categoryModel);

        renderJson("");
    }

    @Path(Url.CATEGORY_SAVE)
    public void save() {
        Category categoryModel = getModel(Category.class);

        categoryModel.validate(Category.CATEGORY_NAME);

        categoryService.save(categoryModel);

        renderJson("");
    }

    @Path(Url.CATEGORYL_UPDATE)
    public void update() {
        Category categoryModel = getModel(Category.class);

        categoryModel.validate(Category.CATEGORY_ID, Category.CATEGORY_NAME);

        categoryService.update(categoryModel);

        renderJson("");
    }

    @Path(Url.CATEGORY_DELETE)
    public void delete() {
        Category categoryModel = getModel(Category.class);

        categoryModel.validate(Category.CATEGORY_ID);

        categoryService.delete(categoryModel);

        renderJson("");
    }

}
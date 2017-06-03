package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.service.CategoryService;

import java.util.List;
import java.util.Map;

public class CategoryController extends Controller {

    private final CategoryService categoryService = new CategoryService();

    @ActionKey(Url.CATEGORY_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Category model = getParameter(Category.class);

        model.validate(Category.CATEGORY_NAME);

        int count = categoryService.count(model.getCategory_name());

        List<Category> categoryList = categoryService.list(model.getCategory_name(), getM(), getN());

        renderSuccessJson(count, categoryList);
    }

    @ActionKey(Url.CATEGORY_ADMIN_TREE_LIST)
    public void adminTreeList() {
        Category model = getParameter(Category.class);

        model.validate(Category.CATEGORY_ID);

        List<Map<String, Object>> resultList = categoryService.treeListByCategory_key(model.getCategory_id(), Category.CATEGORY_VALUE, Category.CATEGORY_SORT);

        renderSuccessJson(resultList);
    }

//    @ActionKey(Url.CATEGORY_CHINA_LIST)
//    public void chinaList() {
//        List<Map<String, Object>> resultList = categoryService.treeChinaList();
//
//        renderSuccessJson(resultList);
//    }

    @ActionKey(Url.CATEGORY_ADMIN_FIND)
    public void adminFind() {
        Category model = getParameter(Category.class);

        model.validate(Category.CATEGORY_ID);

        Category category = categoryService.find(model.getCategory_id());

        renderSuccessJson(category.removeSystemInfo());
    }

    @ActionKey(Url.CATEGORY_ADMIN_SAVE)
    public void adminSave() {
        Category model = getParameter(Category.class);
        String request_user_id = getRequest_user_id();

        model.validate(Category.CATEGORY_NAME, Category.CATEGORY_SORT);

        categoryService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.CATEGORYL_ADMIN_UPDATE)
    public void adminUpdate() {
        Category model = getParameter(Category.class);
        String request_user_id = getRequest_user_id();

        model.validate(Category.CATEGORY_ID, Category.CATEGORY_NAME, Category.CATEGORY_SORT);

        categoryService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.CATEGORY_ADMIN_DELETE)
    public void adminDelete() {
        Category model = getParameter(Category.class);
        String request_user_id = getRequest_user_id();

        model.validate(Category.CATEGORY_ID);

        categoryService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.service.ResourceService;

import java.util.List;
import java.util.Map;

public class ResourceController extends Controller {

    private final ResourceService resourceService = new ResourceService();

    @ActionKey(Url.RESOURCE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_NAME);

        int count = resourceService.count(model.getCategory_id(), model.getResource_name());

        List<Resource> resourceList = resourceService.list(model.getCategory_id(), model.getResource_name(), getM(), getN());

        renderSuccessJson(count, resourceList);
    }

    @ActionKey(Url.RESOURCE_ADMIN_CATEGORY_LIST)
    public void adminCategoryList() {
        List<Map<String, Object>> resultList = resourceService.categoryList();

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.RESOURCE_ADMIN_FIND)
    public void adminFind() {
        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_ID);

        Resource resource = resourceService.find(model.getResource_id());

        renderSuccessJson(resource.removeSystemInfo());
    }

    @ActionKey(Url.RESOURCE_ADMIN_SAVE)
    public void adminSave() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_NAME);

        resourceService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.RESOURCEL_ADMIN_UPDATE)
    public void adminUpdate() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_ID, Resource.RESOURCE_NAME);

        resourceService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.RESOURCE_ADMIN_DELETE)
    public void adminDelete() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_ID);

        resourceService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
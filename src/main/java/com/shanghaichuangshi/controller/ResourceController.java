package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Category;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.service.ResourceService;

import java.util.List;

public class ResourceController extends Controller {

    private final ResourceService resourceService = new ResourceService();

    @ActionKey(Url.RESOURCE_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_NAME);

        List<Resource> resourceList = resourceService.list(model.getCategory_id(), model.getResource_name(), getM(), getN());

        renderSuccessJson(resourceList);
    }

    @ActionKey(Url.RESOURCE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_NAME);

        int count = resourceService.count(model.getCategory_id(), model.getResource_name());

        List<Resource> resourceList = resourceService.list(model.getCategory_id(), model.getResource_name(), getM(), getN());

        renderSuccessJson(count, resourceList);
    }

    @ActionKey(Url.RESOURCE_CATEGORY_LIST)
    public void categoryList() {
        Category category = resourceService.categoryList();

        renderSuccessJson(category);
    }

    @ActionKey(Url.RESOURCE_FIND)
    public void find() {
        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_ID);

        Resource resource = resourceService.find(model.getResource_id());

        renderSuccessJson(resource.removeUnfindable());
    }

    @ActionKey(Url.RESOURCE_ADMIN_FIND)
    public void adminFind() {
        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_ID);

        Resource resource = resourceService.find(model.getResource_id());

        renderSuccessJson(resource.removeSystemInfo());
    }

    @ActionKey(Url.RESOURCE_SAVE)
    public void save() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_NAME);

        resourceService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.RESOURCEL_UPDATE)
    public void update() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_ID, Resource.RESOURCE_NAME);

        resourceService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.RESOURCE_DELETE)
    public void delete() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_ID);

        resourceService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
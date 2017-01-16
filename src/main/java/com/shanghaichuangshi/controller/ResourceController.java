package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.service.ResourceService;

import java.util.List;

public class ResourceController extends Controller {

    private final ResourceService resourceService = new ResourceService();

    @Path(Url.RESOURCE_LIST)
    public void list() {
        Resource resourceModel = getModel(Resource.class);

        resourceModel.validate(Resource.RESOURCE_NAME, Resource.PAGE_INDEX, Resource.PAGE_SIZE);

        List<Resource> resourceList = resourceService.list(resourceModel);

        renderJson(resourceList);
    }

    @Path(Url.RESOURCE_ADMIN_LIST)
    public void adminList() {
        Resource resourceModel = getModel(Resource.class);

        resourceModel.validate(Resource.PAGE_INDEX, Resource.PAGE_SIZE);

        int count = resourceService.count(resourceModel);

        List<Resource> resourceList = resourceService.list(resourceModel);

        renderJson(count, resourceList);
    }

    @Path(Url.RESOURCE_FIND)
    public void find() {
        Resource resourceModel = getModel(Resource.class);

        resourceModel.validate(Resource.RESOURCE_ID);

        Resource resource = resourceService.find(resourceModel);

        resource.removeUnfindable();

        renderJson(resource);
    }

    @Path(Url.RESOURCE_ADMIN_FIND)
    public void adminFind() {
        Resource resourceModel = getModel(Resource.class);

        resourceModel.validate(Resource.RESOURCE_ID);

        Resource resource = resourceService.find(resourceModel);

        renderJson(resource);
    }

    @Path(Url.RESOURCE_SAVE)
    public void save() {
        Resource resourceModel = getModel(Resource.class);

        resourceModel.validate(Resource.RESOURCE_NAME);

        resourceService.save(resourceModel);

        renderJson("");
    }

    @Path(Url.RESOURCEL_UPDATE)
    public void update() {
        Resource resourceModel = getModel(Resource.class);

        resourceModel.validate(Resource.RESOURCE_ID, Resource.RESOURCE_NAME);

        resourceService.update(resourceModel);

        renderJson("");
    }

    @Path(Url.RESOURCE_DELETE)
    public void delete() {
        Resource resourceModel = getModel(Resource.class);

        resourceModel.validate(Resource.RESOURCE_ID);

        resourceService.delete(resourceModel);

        renderJson("");
    }

}
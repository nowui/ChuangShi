package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Resource;
import com.shanghaichuangshi.service.ResourceService;

import java.util.List;

public class ResourceController extends Controller {

    private static final ResourceService RESOURCEService = new ResourceService();

    @ActionKey(Url.RESOURCE_LIST)
    public void list() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_NAME);

        List<Resource> RESOURCEList = RESOURCEService.list(model, getM(), getN());

        renderSuccessJson(RESOURCEList);
    }

    @ActionKey(Url.RESOURCE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_NAME);

        int count = RESOURCEService.count(model);

        List<Resource> RESOURCEList = RESOURCEService.list(model, getM(), getN());

        renderSuccessJson(count, RESOURCEList);
    }

    @ActionKey(Url.RESOURCE_FIND)
    public void find() {
        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_ID);

        Resource RESOURCE = RESOURCEService.find(model);

        RESOURCE.removeUnfindable();

        renderSuccessJson(RESOURCE);
    }

    @ActionKey(Url.RESOURCE_ADMIN_FIND)
    public void adminFind() {
        Resource model = getParameter(Resource.class);

        model.validate(Resource.RESOURCE_ID);

        Resource RESOURCE = RESOURCEService.find(model);

        renderSuccessJson(RESOURCE);
    }

    @ActionKey(Url.RESOURCE_SAVE)
    public void save() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_NAME);

        RESOURCEService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.RESOURCEL_UPDATE)
    public void update() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_ID, Resource.RESOURCE_NAME);

        RESOURCEService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.RESOURCE_DELETE)
    public void delete() {
        Resource model = getParameter(Resource.class);
        String request_user_id = getRequest_user_id();

        model.validate(Resource.RESOURCE_ID);

        RESOURCEService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Constant;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Attribute;
import com.shanghaichuangshi.service.AttributeService;

import java.util.List;

public class AttributeController extends Controller {

    private final AttributeService attributeService = new AttributeService();

    @ActionKey(Url.ATTRIBUTE_ADMIN_LIST)
    public void adminList() {
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Attribute model = getParameter(Attribute.class);

        model.validate(Attribute.ATTRIBUTE_NAME);

        int count = attributeService.count(model.getAttribute_name());

        List<Attribute> attributeList = attributeService.list(model.getAttribute_name(), getM(), getN());

        renderSuccessJson(count, attributeList);
    }

    @ActionKey(Url.ATTRIBUTE_ADMIN_FIND)
    public void adminFind() {
        Attribute model = getParameter(Attribute.class);

        model.validate(Attribute.ATTRIBUTE_ID);

        Attribute attribute = attributeService.find(model.getAttribute_id());

        renderSuccessJson(attribute.removeSystemInfo());
    }

    @ActionKey(Url.ATTRIBUTE_ADMIN_SAVE)
    public void adminSave() {
        Attribute model = getParameter(Attribute.class);
        String request_user_id = getRequest_user_id();

        model.validate(Attribute.ATTRIBUTE_NAME);

        attributeService.save(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ATTRIBUTEL_ADMIN_UPDATE)
    public void adminUpdate() {
        Attribute model = getParameter(Attribute.class);
        String request_user_id = getRequest_user_id();

        model.validate(Attribute.ATTRIBUTE_ID, Attribute.ATTRIBUTE_NAME);

        attributeService.update(model, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.ATTRIBUTE_ADMIN_DELETE)
    public void adminDelete() {
        Attribute model = getParameter(Attribute.class);
        String request_user_id = getRequest_user_id();

        model.validate(Attribute.ATTRIBUTE_ID);

        attributeService.delete(model, request_user_id);

        renderSuccessJson();
    }

}
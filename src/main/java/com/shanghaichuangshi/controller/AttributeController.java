package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Attribute;
import com.shanghaichuangshi.service.AttributeService;

import java.util.List;

public class AttributeController extends Controller {

    private final AttributeService attributeService = new AttributeService();

    @Path(Url.ATTRIBUTE_LIST)
    public void list() {
        Attribute attributeModel = getModel(Attribute.class);

        attributeModel.validate(Attribute.PAGE_INDEX, Attribute.PAGE_SIZE);

        List<Attribute> attributeList = attributeService.list(attributeModel);

        renderJson(attributeList);
    }

    @Path(Url.ATTRIBUTE_ADMIN_LIST)
    public void adminList() {
        Attribute attributeModel = getModel(Attribute.class);

        attributeModel.validate(Attribute.ATTRIBUTE_NAME, Attribute.PAGE_INDEX, Attribute.PAGE_SIZE);

        int count = attributeService.count(attributeModel);

        List<Attribute> attributeList = attributeService.list(attributeModel);

        renderJson(count, attributeList);
    }

    @Path(Url.ATTRIBUTE_FIND)
    public void find() {
        Attribute attributeModel = getModel(Attribute.class);

        attributeModel.validate(Attribute.ATTRIBUTE_ID);

        Attribute attribute = attributeService.find(attributeModel);

        attribute.removeUnfindable();

        renderJson(attribute);
    }

    @Path(Url.ATTRIBUTE_ADMIN_FIND)
    public void adminFind() {
        Attribute attributeModel = getModel(Attribute.class);

        attributeModel.validate(Attribute.ATTRIBUTE_ID);

        Attribute attribute = attributeService.find(attributeModel);

        renderJson(attribute);
    }

    @Path(Url.ATTRIBUTE_SAVE)
    public void save() {
        Attribute attributeModel = getModel(Attribute.class);

        attributeModel.validate(Attribute.ATTRIBUTE_NAME);

        attributeService.save(attributeModel);

        renderJson("");
    }

    @Path(Url.ATTRIBUTEL_UPDATE)
    public void update() {
        Attribute attributeModel = getModel(Attribute.class);

        attributeModel.validate(Attribute.ATTRIBUTE_ID, Attribute.ATTRIBUTE_NAME);

        attributeService.update(attributeModel);

        renderJson("");
    }

    @Path(Url.ATTRIBUTE_DELETE)
    public void delete() {
        Attribute attributeModel = getModel(Attribute.class);

        attributeModel.validate(Attribute.ATTRIBUTE_ID);

        attributeService.delete(attributeModel);

        renderJson("");
    }

}
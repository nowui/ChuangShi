package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.model.Code;
import com.shanghaichuangshi.service.CodeService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CodeController extends Controller {

    private final CodeService codeService = new CodeService();

    @Path(Url.CODE_LIST)
    public void list() {
        Code codeModel = getModel(Code.class);

        List<Code> tableList = codeService.listTable(codeModel.getTable_name());

        renderJson(tableList.size(), tableList);
    }

    @Path(Url.CODE_SAVE)
    public void save() throws IOException {
        Code codeModel = getModel(Code.class);

        codeModel.validate(Code.TABLE_NAME);

        String table_name = codeModel.getTable_name();

        List<Code> codeList = codeService.listColumn(codeModel.getTable_name());

        List<Code> columnList = new ArrayList<Code>();

        for (Code code : codeList) {
            if (! code.getColumn_name().startsWith("system_")) {
                columnList.add(code);
                System.out.println(code);
            }
        }

        String lowerModelName = table_name.replace("table_", "").toLowerCase();
        String upperModelName = lowerModelName.toUpperCase();
        String firstModelName = lowerModelName.substring(0, 1).toUpperCase() + lowerModelName.substring(1);

        write(lowerModelName, upperModelName, firstModelName, columnList, "/model.template", firstModelName + ".java");
        write(lowerModelName, upperModelName, firstModelName, columnList, "/dao.template", firstModelName + "Dao.java");

        renderJson("");
    }

    private void write(String lowerModelName, String upperModelName, String firstModelName, List<Code> columnList, String templateName, String fileName) throws IOException {
        String root = System.getProperty("user.dir") + File.separator + "/src/main/resources/template";
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate(templateName);
        template.binding("lowerModelName", lowerModelName);
        template.binding("upperModelName", upperModelName);
        template.binding("firstModelName", firstModelName);
        template.binding("columnList", columnList);

        template.renderTo(new OutputStreamWriter(new FileOutputStream(new File("/Users/yongqiangzhong/Documents/Publish/" + fileName)), "UTF-8"));
    }

}

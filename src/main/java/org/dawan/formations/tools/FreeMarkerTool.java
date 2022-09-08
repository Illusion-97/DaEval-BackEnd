package org.dawan.formations.tools;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Component
public class FreeMarkerTool {

    private static Configuration freemarkerConfig = null;


    @Autowired
    public FreeMarkerTool(Configuration freemarkerConfig) {
        FreeMarkerTool.freemarkerConfig = freemarkerConfig;
    }

    public static String getResult(Class<?> clazz, String template, Object object) throws IOException, TemplateException {
        String basePackagePath = "/templates";
        freemarkerConfig.setClassForTemplateLoading(clazz, basePackagePath);
        return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate(template), object);
    }
}

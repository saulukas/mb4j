package org.mb4j.brick.template;

import org.mb4j.brick.MustacheBrick;

public class TemplateUtils {

    public static String typeStringOf(Class<? extends MustacheBrick> brickClass) {
        TemplateType annotation = brickClass.getAnnotation(TemplateType.class);
        return annotation == null ? TemplateType.DEFAULT : annotation.value();
    }

    public static String encodingStringOf(Class<? extends MustacheBrick> brickClass) {
        TemplateEncoding annotation = brickClass.getAnnotation(TemplateEncoding.class);
        return annotation == null ? TemplateEncoding.DEFAULT : annotation.value();
    }

    public static String outputEncodingStringOf(Class<? extends MustacheBrick> brickClass) {
        TemplateOutputEncoding annotation = brickClass.getAnnotation(TemplateOutputEncoding.class);
        return annotation == null ? TemplateOutputEncoding.DEFAULT : annotation.value();
    }
}

package org.mb4j.template;

import org.mb4j.Brick;

public class TemplateUtils {
  public static String typeStringOf(Class<? extends Brick> brickClass) {
    TemplateType annotation = brickClass.getAnnotation(TemplateType.class);
    return annotation == null ? TemplateType.DEFAULT : annotation.value();
  }

  public static String encodingStringOf(Class<? extends Brick> brickClass) {
    TemplateEncoding annotation = brickClass.getAnnotation(TemplateEncoding.class);
    return annotation == null ? TemplateEncoding.DEFAULT : annotation.value();
  }

  public static String outputEncodingStringOf(Class<? extends Brick> brickClass) {
    TemplateOutputEncoding annotation = brickClass.getAnnotation(TemplateOutputEncoding.class);
    return annotation == null ? TemplateOutputEncoding.DEFAULT : annotation.value();
  }
}

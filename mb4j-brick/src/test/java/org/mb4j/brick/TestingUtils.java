package org.mb4j.brick;

import java.io.InputStream;
import org.mb4j.brick.template.TemplateIoUtils;

public class TestingUtils {
  public static String fileContentFor(Class subclass, String extension) {
    String resourcePath = "/" + subclass.getName().replace(".", "/") + extension;
    InputStream inputStream = subclass.getResourceAsStream(resourcePath);
    if (inputStream == null) {
      throw new RuntimeException("Classpath resource not found: " + resourcePath);
    }
    return TemplateIoUtils.readAllCharsFrom(inputStream, "utf-8");
  }
}

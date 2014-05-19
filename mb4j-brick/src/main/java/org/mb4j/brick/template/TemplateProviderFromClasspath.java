package org.mb4j.brick.template;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import java.io.InputStream;
import org.mb4j.brick.MustacheBrick;

public class TemplateProviderFromClasspath implements TemplateProvider {
  @Override
  public BrickTemplate templateFor(Class<? extends MustacheBrick> brickClass) {
    TemplateTextSource source = templateTextSourceFor(brickClass);
    String templateText = templateTextFrom(source);
    Template template = Mustache.compiler().compile(templateText);
    return new BrickTemplate(source.name, template);
  }

  private static class TemplateTextSource {
    final String name;
    final String encoding;
    final InputStream inputStream;

    public TemplateTextSource(String name, String encoding, InputStream inputStream) {
      this.name = name;
      this.encoding = encoding;
      this.inputStream = inputStream;
    }
  }

  private TemplateTextSource templateTextSourceFor(Class<? extends MustacheBrick> brickClass) {
    String templateType = TemplateUtils.typeStringOf(brickClass);
    Class<?> templateClass = brickClass;
    InputStream inputStream = TemplateIoUtils.inputStreamOrNull(templateClass, templateType);
    while (inputStream == null && templateClass != null) {
      templateClass = templateClass.getSuperclass();
      inputStream = TemplateIoUtils.inputStreamOrNull(templateClass, templateType);
    }
    if (inputStream == null) {
      throw new RuntimeException("Template not found for class " + brickClass
          + " and templateType '" + templateType + "'");
    }
    String encoding = TemplateUtils.encodingStringOf(brickClass);
    return new TemplateTextSource(templateClass.getName() + templateType, encoding, inputStream);
  }

  private String templateTextFrom(TemplateTextSource source) {
    try {
      return TemplateIoUtils.readAllCharsFrom(source.inputStream, source.encoding);
    } catch (RuntimeException ex) {
      throw new RuntimeException("Failed to read template " + source.name + ": " + ex, ex);
    }
  }
}

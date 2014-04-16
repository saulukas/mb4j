package org.mb4j.brick.template;

import org.mb4j.brick.template.TemplateProviderFromClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.brick.samples.TemplateBrick;
import org.mb4j.brick.samples.TemplateExtensionBrick;
import org.mb4j.brick.samples.TemplateExtensionExtensionBrick;

public class TemplateProviderFromClasspathTest {
  private final TemplateProviderFromClasspath provider = new TemplateProviderFromClasspath();

  @Test
  public void finds_templates_next_to_class_file() {
    assertThat(provider.templateFor(TemplateBrick.class).name,
        is(TemplateBrick.class.getName() + ".mustache"));
  }

  @Test
  public void if_class_has_no_template_the_one_from_super_class_is_used() {
    assertThat(provider.templateFor(TemplateExtensionBrick.class).name,
        is(TemplateBrick.class.getName() + ".mustache"));
    assertThat(provider.templateFor(TemplateExtensionExtensionBrick.class).name,
        is(TemplateBrick.class.getName() + ".mustache"));
  }
}

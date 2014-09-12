package org.mb4j.brick.template;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.brick.samples.layout.LayoutBrick;
import org.mb4j.brick.samples.layout.LayoutExtensionBrick;
import org.mb4j.brick.samples.layout.LayoutExtensionExtensionBrick;

public class TemplateProviderFromClasspathTest {

    private final TemplateProviderFromClasspath provider = new TemplateProviderFromClasspath();

    @Test
    public void finds_templates_next_to_class_file() {
        assertThat(provider.templateFor(LayoutBrick.class).name,
                is(LayoutBrick.class.getName() + ".mustache"));
    }

    @Test
    public void if_class_has_no_template_the_one_from_super_class_is_used() {
        assertThat(provider.templateFor(LayoutExtensionBrick.class).name,
                is(LayoutBrick.class.getName() + ".mustache"));
        assertThat(provider.templateFor(LayoutExtensionExtensionBrick.class).name,
                is(LayoutBrick.class.getName() + ".mustache"));
    }
}

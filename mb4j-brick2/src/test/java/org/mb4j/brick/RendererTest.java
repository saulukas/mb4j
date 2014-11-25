package org.mb4j.brick;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;

public class RendererTest {

    @Test
    public void substitutes_variables() {
        String output = Renderer.render("aaa {{var1}} bbb", new DemoBrick());
        System.out.println("output = " + output);
//        assertThat(output, is("aaa 123 bbb"));
    }

    @Ignore
    @Test
    public void substitutes_variables2() {
        String output = Renderer.render("aaa {{oho}} bbb", new Object() {
            String oho = "123";
        });
        assertThat(output, is("aaa 123 bbb"));
    }
}

package org.mb4j.brick;

import java.io.File;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class RendererTest {

    @Test
    public void dummy() {
        new File("asdf");
//   Template<SimpleBrick> template = Compiler.compile(SimpleBrick.class);
//   template
    }

    public static void main(String[] args) {
        String output = Renderer.render("aaa {{oho}} bbb", new Object() {
            String oho = "123";
        });
        assertThat(output, is("aaa 123 bbb"));
    }
}

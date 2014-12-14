package org.mb4j.brick;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.brick.MustacheBricks.render;

public class MustacheBricksTest {

    @Test
    public void substitutes_variables() {
        String output = render("aaa {{var1}} bbb", new DemoBrick());
        System.out.println("output = " + output);
//        assertThat(output, is("aaa 123 bbb"));
    }

//    @Ignore
    @Test
    public void substitutes_variables2() {
        String output = render("aaa {{oho}} bbb", new Object() {
            String oho = "123";
        });
        assertThat(output, is("aaa 123 bbb"));
    }
}

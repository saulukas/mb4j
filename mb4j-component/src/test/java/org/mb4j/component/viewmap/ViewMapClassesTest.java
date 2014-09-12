package org.mb4j.component.viewmap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.component.TypicalControllers.HOME;
import static org.mb4j.component.TypicalControllers.TUTORIAL;
import static org.mb4j.component.url.UrlPathString.pathStringOf;
import static org.mb4j.component.url.UrlPathString.urlPathOf;

public class ViewMapClassesTest {

    @Test
    public void maps_view_classes_at_view_paths() {
        ViewMapClasses classes = new ViewMapClasses();
        classes.mount(urlPathOf("/"), HOME.getClass());
        classes.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
        assertThat(pathStringOf(classes.urlPathFor(HOME.getClass())), is(""));
        assertThat(pathStringOf(classes.urlPathFor(TUTORIAL.getClass())), is("tutorial"));
    }

    @Test
    public void does_not_allow_to_map_same_view_class_twice() {
        ViewMapClasses classes = new ViewMapClasses();
        classes.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
        try {
            classes.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
            fail();
        } catch (RuntimeException ex) {
            System.out.println("Nice error messge:\n" + ex.getMessage());
        }
        try {
            classes.mount(urlPathOf("some/other/path"), TUTORIAL.getClass());
            fail();
        } catch (RuntimeException ex) {
            System.out.println("Nice error messge:\n" + ex.getMessage());
        }
    }

    @Test
    public void throws_exception_when_resolving_not_mapped_view() {
        ViewMapClasses classes = new ViewMapClasses();
        try {
            classes.urlPathFor(TUTORIAL.getClass());
            fail();
        } catch (RuntimeException ex) {
            System.out.println("Nice error messge:\n" + ex.getMessage());
        }
    }
}

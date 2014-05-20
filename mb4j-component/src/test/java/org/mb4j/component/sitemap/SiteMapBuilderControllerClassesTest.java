package org.mb4j.component.sitemap;

import org.mb4j.component.sitemap.SiteMapBuilderControllerClasses;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.component.TypicalViews.HOME;
import static org.mb4j.component.TypicalViews.TUTORIAL;
import static org.mb4j.component.url.UrlPathString.pathStringOf;
import static org.mb4j.component.url.UrlPathString.urlPathOf;

public class SiteMapBuilderControllerClassesTest {
  @Test
  public void mounts_controller_classes_at_controller_paths() {
    SiteMapBuilderControllerClasses classes = new SiteMapBuilderControllerClasses();
    classes.mount(urlPathOf("/"), HOME.getClass());
    classes.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
    assertThat(pathStringOf(classes.urlPathFor(HOME.getClass())), is(""));
    assertThat(pathStringOf(classes.urlPathFor(TUTORIAL.getClass())), is("tutorial"));
  }

  @Test
  public void does_not_allow_to_mount_same_controller_class_twice() {
    SiteMapBuilderControllerClasses classes = new SiteMapBuilderControllerClasses();
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
  public void throws_exception_when_resolving_unmounted_controller() {
    SiteMapBuilderControllerClasses classes = new SiteMapBuilderControllerClasses();
    try {
      classes.urlPathFor(TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
  }
}

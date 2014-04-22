package org.mb4j.controller.mapping;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.controller.TypicalViews.HOME;
import static org.mb4j.controller.TypicalViews.TUTORIAL;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;

public class ViewPathMounterTest {
  @Test
  public void mounts_view_classes_at_view_paths() {
    ControllerPathMounter mounter = new ControllerPathMounter();
    mounter.mount(urlPathOf("/"), HOME.getClass());
    mounter.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
    assertThat(pathStringOf(mounter.urlPathFor(HOME.getClass())), is(""));
    assertThat(pathStringOf(mounter.urlPathFor(TUTORIAL.getClass())), is("tutorial"));
  }

  @Test
  public void does_not_allow_to_mount_same_view_class_twice() {
    ControllerPathMounter mounter = new ControllerPathMounter();
    mounter.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
    try {
      mounter.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
    try {
      mounter.mount(urlPathOf("some/other/path"), TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
  }

  @Test
  public void throws_exception_when_resolving_unmounted_view() {
    ControllerPathMounter mounter = new ControllerPathMounter();
    try {
      mounter.urlPathFor(TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
  }
}

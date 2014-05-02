package org.mb4j.controller.mapping;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.controller.TypicalControllers.HOME;
import static org.mb4j.controller.TypicalControllers.TUTORIAL;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;

public class ControllerPathMounterTest {
  @Test
  public void mounts_controller_classes_at_controller_paths() {
    ControllerPathMounter mounter = new ControllerPathMounter();
    mounter.mount(urlPathOf("/"), HOME.getClass());
    mounter.mount(urlPathOf("tutorial"), TUTORIAL.getClass());
    assertThat(pathStringOf(mounter.urlPathFor(HOME.getClass())), is(""));
    assertThat(pathStringOf(mounter.urlPathFor(TUTORIAL.getClass())), is("tutorial"));
  }

  @Test
  public void does_not_allow_to_mount_same_controller_class_twice() {
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
  public void throws_exception_when_resolving_unmounted_controller() {
    ControllerPathMounter mounter = new ControllerPathMounter();
    try {
      mounter.urlPathFor(TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
  }
}

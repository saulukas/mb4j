package org.mb4j.view.mount;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.view.TypicalViews.HOME;
import static org.mb4j.view.TypicalViews.TUTORIAL;
import static org.mb4j.view.path.ViewPathString.pathStringOf;
import static org.mb4j.view.path.ViewPathString.viewPath;

public class ViewPathMounterTest {
  @Test
  public void mounts_view_classes_at_view_paths() {
    ViewPathMounter mounter = new ViewPathMounter();
    mounter.mount(viewPath("/"), HOME.getClass());
    mounter.mount(viewPath("tutorial"), TUTORIAL.getClass());
    assertThat(pathStringOf(mounter.viewPathFor(HOME.getClass())), is(""));
    assertThat(pathStringOf(mounter.viewPathFor(TUTORIAL.getClass())), is("tutorial"));
  }

  @Test
  public void does_not_allow_to_mount_same_view_class_twice() {
    ViewPathMounter mounter = new ViewPathMounter();
    mounter.mount(viewPath("tutorial"), TUTORIAL.getClass());
    try {
      mounter.mount(viewPath("tutorial"), TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
    try {
      mounter.mount(viewPath("some/other/path"), TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
  }

  @Test
  public void throws_exception_when_resolving_unmounted_view() {
    ViewPathMounter mounter = new ViewPathMounter();
    try {
      mounter.viewPathFor(TUTORIAL.getClass());
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error messge:\n" + ex.getMessage());
    }
  }
}

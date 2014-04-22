package org.mb4j.controller.mount;

import org.mb4j.controller.mount.ViewMounterNode;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.controller.TypicalViews.HOME;
import static org.mb4j.controller.TypicalViews.TUTORIAL;
import static org.mb4j.controller.TypicalViews.TUTORIAL_ON_EVENTS;
import static org.mb4j.controller.TypicalViews.TUTORIAL_ON_SOCKETS;
import static org.mb4j.controller.TypicalViews.TUTORIAL_OTHER_STUFF;
import static org.mb4j.controller.TypicalViews.TUTORIAL_TOPIC;
import static org.mb4j.controller.path.UrlPathString.urlPath;

public class ViewMounterNodeTest {
  @Test
  public void prints_view_tree_as_string() {
    ViewMounterNode root = ViewMounterNode.createRoot();
    root.mount(urlPath(""), HOME);
    root.mount(urlPath("tutorial/*"), TUTORIAL);
    root.mount(urlPath("tutorial/events"), TUTORIAL_ON_EVENTS);
    root.mount(urlPath("tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    root.mount(urlPath("tutorial/topic/*"), TUTORIAL_TOPIC);
    root.mount(urlPath("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    System.out.println();
    System.out.println("    " + root.toString("    "));
    System.out.println();
  }

  @Test
  public void mounts_views_at_ViewPaths() {
    ViewMounterNode root = ViewMounterNode.createRoot();
    root.mount(urlPath(""), HOME);
    root.mount(urlPath("tutorial/*"), TUTORIAL);
    root.mount(urlPath("tutorial/events"), TUTORIAL_ON_EVENTS);
    root.mount(urlPath("tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    root.mount(urlPath("tutorial/topic/*"), TUTORIAL_TOPIC);
    root.mount(urlPath("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    assertThat(root.resolve(urlPath("")).view, sameInstance(HOME));
    assertThat(root.resolve(urlPath("tutorial")).view, sameInstance(TUTORIAL));
    assertThat(root.resolve(urlPath("tutorial/")).view, sameInstance(TUTORIAL));
    assertThat(root.resolve(urlPath("tutorial/1")).view, sameInstance(TUTORIAL));
    assertThat(root.resolve(urlPath("tutorial/2/3")).view, sameInstance(TUTORIAL));
    assertThat(root.resolve(urlPath("tutorial/events")).view, sameInstance(TUTORIAL_ON_EVENTS));
    assertThat(root.resolve(urlPath("tutorial/sockets")).view, sameInstance(TUTORIAL_ON_SOCKETS));
    assertThat(root.resolve(urlPath("tutorial/topic")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.resolve(urlPath("tutorial/topic/")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.resolve(urlPath("tutorial/topic/7")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.resolve(urlPath("tutorial/topic/8/9")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.resolve(urlPath("tutorial/other/stuff")).view, sameInstance(TUTORIAL_OTHER_STUFF));
  }

  @Test
  public void does_not_allow_several_views_on_same_viewPath() {
    ViewMounterNode root = ViewMounterNode.createRoot();
    root.mount(urlPath("tutorial/topic/*"), TUTORIAL_TOPIC);
    try {
      root.mount(urlPath("tutorial/topic"), TUTORIAL_OTHER_STUFF);
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error message:\n" + ex.getMessage());
    }
  }

  @Test
  public void allows_asterist_only_at_path_end() {
    ViewMounterNode root = ViewMounterNode.createRoot();
    root.mount(urlPath("*"), TUTORIAL);
    root.mount(urlPath("other/*"), TUTORIAL_OTHER_STUFF);
    try {
      root.mount(urlPath("*/at/the/start"), TUTORIAL_ON_EVENTS);
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error message:\n" + ex.getMessage());
    }
    try {
      root.mount(urlPath("in/the/*/middle"), TUTORIAL_ON_SOCKETS);
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error message:\n" + ex.getMessage());
    }
  }
}

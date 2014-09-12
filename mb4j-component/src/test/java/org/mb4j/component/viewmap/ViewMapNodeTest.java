package org.mb4j.component.viewmap;

import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.component.TypicalControllers.HOME;
import static org.mb4j.component.TypicalControllers.TUTORIAL;
import static org.mb4j.component.TypicalControllers.TUTORIAL_ON_EVENTS;
import static org.mb4j.component.TypicalControllers.TUTORIAL_ON_SOCKETS;
import static org.mb4j.component.TypicalControllers.TUTORIAL_OTHER_STUFF;
import static org.mb4j.component.TypicalControllers.TUTORIAL_TOPIC;
import static org.mb4j.component.url.UrlPathString.urlPathOf;

public class ViewMapNodeTest {
  @Test
  public void prints_view_tree_as_string() {
    ViewMapNode root = ViewMapNode.createRoot();
    root.mount(urlPathOf(""), HOME);
    root.mount(urlPathOf("tutorial/*"), TUTORIAL);
    root.mount(urlPathOf("tutorial/events"), TUTORIAL_ON_EVENTS);
    root.mount(urlPathOf("tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    root.mount(urlPathOf("tutorial/topic/*"), TUTORIAL_TOPIC);
    root.mount(urlPathOf("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    System.out.println();
    System.out.println("    " + root.toString("    "));
    System.out.println();
  }

  @Test
  public void mounts_views_at_urlPaths() {
    ViewMapNode root = ViewMapNode.createRoot();
    root.mount(urlPathOf(""), HOME);
    root.mount(urlPathOf("tutorial/*"), TUTORIAL);
    root.mount(urlPathOf("tutorial/events"), TUTORIAL_ON_EVENTS);
    root.mount(urlPathOf("tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    root.mount(urlPathOf("tutorial/topic/*"), TUTORIAL_TOPIC);
    root.mount(urlPathOf("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    assertThat(root.viewAt(urlPathOf("")).view, sameInstance(HOME));
    assertThat(root.viewAt(urlPathOf("tutorial")).view, sameInstance(TUTORIAL));
    assertThat(root.viewAt(urlPathOf("tutorial/")).view, sameInstance(TUTORIAL));
    assertThat(root.viewAt(urlPathOf("tutorial/1")).view, sameInstance(TUTORIAL));
    assertThat(root.viewAt(urlPathOf("tutorial/2/3")).view, sameInstance(TUTORIAL));
    assertThat(root.viewAt(urlPathOf("tutorial/events")).view, sameInstance(TUTORIAL_ON_EVENTS));
    assertThat(root.viewAt(urlPathOf("tutorial/sockets")).view, sameInstance(TUTORIAL_ON_SOCKETS));
    assertThat(root.viewAt(urlPathOf("tutorial/topic")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.viewAt(urlPathOf("tutorial/topic/")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.viewAt(urlPathOf("tutorial/topic/7")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.viewAt(urlPathOf("tutorial/topic/8/9")).view, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.viewAt(urlPathOf("tutorial/other/stuff")).view, sameInstance(TUTORIAL_OTHER_STUFF));
  }

  @Test
  public void does_not_allow_several_views_on_same_urlPath() {
    ViewMapNode root = ViewMapNode.createRoot();
    root.mount(urlPathOf("tutorial/topic/*"), TUTORIAL_TOPIC);
    try {
      root.mount(urlPathOf("tutorial/topic"), TUTORIAL_OTHER_STUFF);
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error message:\n" + ex.getMessage());
    }
  }

  @Test
  public void allows_asterist_only_at_path_end() {
    ViewMapNode root = ViewMapNode.createRoot();
    root.mount(urlPathOf("*"), TUTORIAL);
    root.mount(urlPathOf("other/*"), TUTORIAL_OTHER_STUFF);
    try {
      root.mount(urlPathOf("*/at/the/start"), TUTORIAL_ON_EVENTS);
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error message:\n" + ex.getMessage());
    }
    try {
      root.mount(urlPathOf("in/the/*/middle"), TUTORIAL_ON_SOCKETS);
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error message:\n" + ex.getMessage());
    }
  }
}

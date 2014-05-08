package org.mb4j.controller.mapping;

import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.controller.TypicalControllers.HOME;
import static org.mb4j.controller.TypicalControllers.TUTORIAL;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_ON_EVENTS;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_ON_SOCKETS;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_OTHER_STUFF;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_TOPIC;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;

public class ControllerMounterNodeTest {
  @Test
  public void prints_controller_tree_as_string() {
    ControllerMounterNode root = ControllerMounterNode.createRoot();
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
  public void mounts_controllers_at_urlPaths() {
    ControllerMounterNode root = ControllerMounterNode.createRoot();
    root.mount(urlPathOf(""), HOME);
    root.mount(urlPathOf("tutorial/*"), TUTORIAL);
    root.mount(urlPathOf("tutorial/events"), TUTORIAL_ON_EVENTS);
    root.mount(urlPathOf("tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    root.mount(urlPathOf("tutorial/topic/*"), TUTORIAL_TOPIC);
    root.mount(urlPathOf("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    assertThat(root.controllerFor(urlPathOf("")).controller, sameInstance(HOME));
    assertThat(root.controllerFor(urlPathOf("tutorial")).controller, sameInstance(TUTORIAL));
    assertThat(root.controllerFor(urlPathOf("tutorial/")).controller, sameInstance(TUTORIAL));
    assertThat(root.controllerFor(urlPathOf("tutorial/1")).controller, sameInstance(TUTORIAL));
    assertThat(root.controllerFor(urlPathOf("tutorial/2/3")).controller, sameInstance(TUTORIAL));
    assertThat(root.controllerFor(urlPathOf("tutorial/events")).controller, sameInstance(TUTORIAL_ON_EVENTS));
    assertThat(root.controllerFor(urlPathOf("tutorial/sockets")).controller, sameInstance(TUTORIAL_ON_SOCKETS));
    assertThat(root.controllerFor(urlPathOf("tutorial/topic")).controller, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.controllerFor(urlPathOf("tutorial/topic/")).controller, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.controllerFor(urlPathOf("tutorial/topic/7")).controller, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.controllerFor(urlPathOf("tutorial/topic/8/9")).controller, sameInstance(TUTORIAL_TOPIC));
    assertThat(root.controllerFor(urlPathOf("tutorial/other/stuff")).controller, sameInstance(TUTORIAL_OTHER_STUFF));
  }

  @Test
  public void does_not_allow_several_controllers_on_same_urlPath() {
    ControllerMounterNode root = ControllerMounterNode.createRoot();
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
    ControllerMounterNode root = ControllerMounterNode.createRoot();
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

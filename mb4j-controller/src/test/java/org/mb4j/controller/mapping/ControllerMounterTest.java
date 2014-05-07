package org.mb4j.controller.mapping;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.controller.Controller;
import static org.mb4j.controller.TypicalControllers.HOME;
import static org.mb4j.controller.TypicalControllers.TUTORIAL;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_ON_EVENTS;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_ON_SOCKETS;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_OTHER_STUFF;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_TOPIC;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;

public class ControllerMounterTest {
  @Test
  public void mounts_controllers_at_given_paths() {
    ControllerMounter mounter = ControllerMounter.withHomeController(HOME);
    mounter.mount(urlPathOf("/tutorial/*"), TUTORIAL);
    mounter.mount(urlPathOf("/tutorial/events"), TUTORIAL_ON_EVENTS);
    mounter.mount(urlPathOf("/tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    mounter.mount(urlPathOf("/tutorial/topic/*"), TUTORIAL_TOPIC);
    mounter.mount(urlPathOf("/tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    String margin = "   ";
    System.out.println("");
    System.out.println(margin + mounter.toString(margin));
    System.out.println("");
    MapControllerClass2UrlPath pathResolver = mounter.controllerClass2UrlPathResolver();
    assertThat(resolvedPathString(pathResolver, HOME), is(""));
    assertThat(resolvedPathString(pathResolver, TUTORIAL), is("tutorial"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_ON_EVENTS), is("tutorial/events"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_ON_SOCKETS), is("tutorial/sockets"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_TOPIC), is("tutorial/topic"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_OTHER_STUFF), is("tutorial/other/stuff"));
    MapUrlPath2Controller resolver = mounter.urlPath2ControllerResolver();
    assertThat(resolvedControllerBy(resolver, ""), sameInstance(HOME));
    assertThat(resolvedControllerBy(resolver, "tutorial"), sameInstance(TUTORIAL));
    assertThat(resolvedControllerBy(resolver, "tutorial/"), sameInstance(TUTORIAL));
    assertThat(resolvedControllerBy(resolver, "tutorial/1"), sameInstance(TUTORIAL));
    assertThat(resolvedControllerBy(resolver, "tutorial/2/3"), sameInstance(TUTORIAL));
    assertThat(resolvedControllerBy(resolver, "tutorial/events"), sameInstance(TUTORIAL_ON_EVENTS));
    assertThat(resolvedControllerBy(resolver, "tutorial/sockets"), sameInstance(TUTORIAL_ON_SOCKETS));
    assertThat(resolvedControllerBy(resolver, "tutorial/topic"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedControllerBy(resolver, "tutorial/topic/"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedControllerBy(resolver, "tutorial/topic/7"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedControllerBy(resolver, "tutorial/topic/8/9"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedControllerBy(resolver, "tutorial/other/stuff"), sameInstance(TUTORIAL_OTHER_STUFF));
  }

  private String resolvedPathString(MapControllerClass2UrlPath resolver, Controller controller) {
    return pathStringOf(resolver.urlPathFor(controller.getClass()));
  }

  private Controller resolvedControllerBy(MapUrlPath2Controller resolver, String pathString) {
    return resolver.resolve(urlPathOf(pathString)).controller;
  }
}

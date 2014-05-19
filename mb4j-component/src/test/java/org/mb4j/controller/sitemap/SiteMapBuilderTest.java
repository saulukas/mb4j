package org.mb4j.controller.sitemap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.controller.Controller;
import static org.mb4j.controller.TypicalPages.HOME;
import static org.mb4j.controller.TypicalPages.TUTORIAL;
import static org.mb4j.controller.TypicalPages.TUTORIAL_ON_EVENTS;
import static org.mb4j.controller.TypicalPages.TUTORIAL_ON_SOCKETS;
import static org.mb4j.controller.TypicalPages.TUTORIAL_OTHER_STUFF;
import static org.mb4j.controller.TypicalPages.TUTORIAL_TOPIC;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;

public class SiteMapBuilderTest {
  @Test
  public void mounts_controllers_at_given_paths() {
    SiteMapBuilder builder = SiteMapBuilder.withHomeController(HOME);
    builder.mount(urlPathOf("/tutorial/*"), TUTORIAL);
    builder.mount(urlPathOf("/tutorial/events"), TUTORIAL_ON_EVENTS);
    builder.mount(urlPathOf("/tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    builder.mount(urlPathOf("/tutorial/topic/*"), TUTORIAL_TOPIC);
    builder.mount(urlPathOf("/tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    String margin = "   ";
    System.out.println("");
    System.out.println(margin + builder.toString(margin));
    System.out.println("");
    MapControllerClass2UrlPath pathResolver = builder.controllerClass2UrlPath();
    assertThat(resolvedPathString(pathResolver, HOME), is(""));
    assertThat(resolvedPathString(pathResolver, TUTORIAL), is("tutorial"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_ON_EVENTS), is("tutorial/events"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_ON_SOCKETS), is("tutorial/sockets"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_TOPIC), is("tutorial/topic"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_OTHER_STUFF), is("tutorial/other/stuff"));
    MapUrlPath2Controller map = builder.urlPath2Controller();
    assertThat(controllerFor(map, ""), sameInstance(HOME));
    assertThat(controllerFor(map, "tutorial"), sameInstance(TUTORIAL));
    assertThat(controllerFor(map, "tutorial/"), sameInstance(TUTORIAL));
    assertThat(controllerFor(map, "tutorial/1"), sameInstance(TUTORIAL));
    assertThat(controllerFor(map, "tutorial/2/3"), sameInstance(TUTORIAL));
    assertThat(controllerFor(map, "tutorial/events"), sameInstance(TUTORIAL_ON_EVENTS));
    assertThat(controllerFor(map, "tutorial/sockets"), sameInstance(TUTORIAL_ON_SOCKETS));
    assertThat(controllerFor(map, "tutorial/topic"), sameInstance(TUTORIAL_TOPIC));
    assertThat(controllerFor(map, "tutorial/topic/"), sameInstance(TUTORIAL_TOPIC));
    assertThat(controllerFor(map, "tutorial/topic/7"), sameInstance(TUTORIAL_TOPIC));
    assertThat(controllerFor(map, "tutorial/topic/8/9"), sameInstance(TUTORIAL_TOPIC));
    assertThat(controllerFor(map, "tutorial/other/stuff"), sameInstance(TUTORIAL_OTHER_STUFF));
  }

  private String resolvedPathString(MapControllerClass2UrlPath map, Controller controller) {
    return pathStringOf(map.urlPathFor(controller.getClass()));
  }

  private Controller controllerFor(MapUrlPath2Controller map, String pathString) {
    return map.controllerFor(urlPathOf(pathString)).controller;
  }
}

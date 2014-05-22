package org.mb4j.component.viewmap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.component.TypicalViews.HOME;
import static org.mb4j.component.TypicalViews.TUTORIAL;
import static org.mb4j.component.TypicalViews.TUTORIAL_ON_EVENTS;
import static org.mb4j.component.TypicalViews.TUTORIAL_ON_SOCKETS;
import static org.mb4j.component.TypicalViews.TUTORIAL_OTHER_STUFF;
import static org.mb4j.component.TypicalViews.TUTORIAL_TOPIC;
import static org.mb4j.component.url.UrlPathString.pathStringOf;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.view.View;

public class ViewMapBuilderTest {
  @Test
  public void mounts_views_at_given_paths() {
    ViewMapBuilder builder = ViewMapBuilder.withHomeView(HOME);
    builder.mount(urlPathOf("/tutorial/*"), TUTORIAL);
    builder.mount(urlPathOf("/tutorial/events"), TUTORIAL_ON_EVENTS);
    builder.mount(urlPathOf("/tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    builder.mount(urlPathOf("/tutorial/topic/*"), TUTORIAL_TOPIC);
    builder.mount(urlPathOf("/tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    String margin = "   ";
    System.out.println("");
    System.out.println(margin + builder.toString(margin));
    System.out.println("");
    MapViewClass2UrlPath pathResolver = builder.viewClass2UrlPath();
    assertThat(pathString(pathResolver, HOME), is(""));
    assertThat(pathString(pathResolver, TUTORIAL), is("tutorial"));
    assertThat(pathString(pathResolver, TUTORIAL_ON_EVENTS), is("tutorial/events"));
    assertThat(pathString(pathResolver, TUTORIAL_ON_SOCKETS), is("tutorial/sockets"));
    assertThat(pathString(pathResolver, TUTORIAL_TOPIC), is("tutorial/topic"));
    assertThat(pathString(pathResolver, TUTORIAL_OTHER_STUFF), is("tutorial/other/stuff"));
    MapUrlPath2View map = builder.urlPath2View();
    assertThat(viewAt(map, ""), sameInstance(HOME));
    assertThat(viewAt(map, "tutorial"), sameInstance(TUTORIAL));
    assertThat(viewAt(map, "tutorial/"), sameInstance(TUTORIAL));
    assertThat(viewAt(map, "tutorial/1"), sameInstance(TUTORIAL));
    assertThat(viewAt(map, "tutorial/2/3"), sameInstance(TUTORIAL));
    assertThat(viewAt(map, "tutorial/events"), sameInstance(TUTORIAL_ON_EVENTS));
    assertThat(viewAt(map, "tutorial/sockets"), sameInstance(TUTORIAL_ON_SOCKETS));
    assertThat(viewAt(map, "tutorial/topic"), sameInstance(TUTORIAL_TOPIC));
    assertThat(viewAt(map, "tutorial/topic/"), sameInstance(TUTORIAL_TOPIC));
    assertThat(viewAt(map, "tutorial/topic/7"), sameInstance(TUTORIAL_TOPIC));
    assertThat(viewAt(map, "tutorial/topic/8/9"), sameInstance(TUTORIAL_TOPIC));
    assertThat(viewAt(map, "tutorial/other/stuff"), sameInstance(TUTORIAL_OTHER_STUFF));
  }

  private String pathString(MapViewClass2UrlPath map, View view) {
    return pathStringOf(map.urlPathFor(view.getClass()));
  }

  private View viewAt(MapUrlPath2View map, String pathString) {
    return map.viewAt(urlPathOf(pathString)).view;
  }
}

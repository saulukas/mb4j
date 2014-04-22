package org.mb4j.controller.mount;

import org.mb4j.controller.mount.ViewFromPathResolver;
import org.mb4j.controller.mount.PathFromViewClassResolver;
import org.mb4j.controller.mount.ViewMounter;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.controller.TypicalViews.HOME;
import static org.mb4j.controller.TypicalViews.TUTORIAL;
import static org.mb4j.controller.TypicalViews.TUTORIAL_ON_EVENTS;
import static org.mb4j.controller.TypicalViews.TUTORIAL_ON_SOCKETS;
import static org.mb4j.controller.TypicalViews.TUTORIAL_OTHER_STUFF;
import static org.mb4j.controller.TypicalViews.TUTORIAL_TOPIC;
import org.mb4j.controller.Controller;
import static org.mb4j.controller.path.UrlPathString.pathStringOf;
import static org.mb4j.controller.path.UrlPathString.urlPath;

public class ViewMounterTest {
  @Test
  public void mounts_views_at_given_paths() {
    ViewMounter mounter = ViewMounter.withHomeView(HOME);
    mounter.mount(urlPath("/tutorial/*"), TUTORIAL);
    mounter.mount(urlPath("/tutorial/events"), TUTORIAL_ON_EVENTS);
    mounter.mount(urlPath("/tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    mounter.mount(urlPath("/tutorial/topic/*"), TUTORIAL_TOPIC);
    mounter.mount(urlPath("/tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    String margin = "   ";
    System.out.println("");
    System.out.println(margin + mounter.toString(margin));
    System.out.println("");
    PathFromViewClassResolver pathResolver = mounter.pathFromViewClassResolver();
    assertThat(resolvedPathString(pathResolver, HOME), is(""));
    assertThat(resolvedPathString(pathResolver, TUTORIAL), is("tutorial"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_ON_EVENTS), is("tutorial/events"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_ON_SOCKETS), is("tutorial/sockets"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_TOPIC), is("tutorial/topic"));
    assertThat(resolvedPathString(pathResolver, TUTORIAL_OTHER_STUFF), is("tutorial/other/stuff"));
    ViewFromPathResolver viewFromPath = mounter.viewFromPathResolver();
    assertThat(resolvedView(viewFromPath, ""), sameInstance(HOME));
    assertThat(resolvedView(viewFromPath, "tutorial"), sameInstance(TUTORIAL));
    assertThat(resolvedView(viewFromPath, "tutorial/"), sameInstance(TUTORIAL));
    assertThat(resolvedView(viewFromPath, "tutorial/1"), sameInstance(TUTORIAL));
    assertThat(resolvedView(viewFromPath, "tutorial/2/3"), sameInstance(TUTORIAL));
    assertThat(resolvedView(viewFromPath, "tutorial/events"), sameInstance(TUTORIAL_ON_EVENTS));
    assertThat(resolvedView(viewFromPath, "tutorial/sockets"), sameInstance(TUTORIAL_ON_SOCKETS));
    assertThat(resolvedView(viewFromPath, "tutorial/topic"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedView(viewFromPath, "tutorial/topic/"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedView(viewFromPath, "tutorial/topic/7"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedView(viewFromPath, "tutorial/topic/8/9"), sameInstance(TUTORIAL_TOPIC));
    assertThat(resolvedView(viewFromPath, "tutorial/other/stuff"), sameInstance(TUTORIAL_OTHER_STUFF));
  }

  private String resolvedPathString(PathFromViewClassResolver resolver, Controller view) {
    return pathStringOf(resolver.viewPathFor(view.getClass()));
  }

  private Controller resolvedView(ViewFromPathResolver viewFromPath, String pathString) {
    return viewFromPath.resolve(urlPath(pathString)).view;
  }
}

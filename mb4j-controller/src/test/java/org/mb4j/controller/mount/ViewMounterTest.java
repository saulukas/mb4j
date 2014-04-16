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
import org.mb4j.controller.View;
import static org.mb4j.controller.path.ViewPathString.pathStringOf;
import static org.mb4j.controller.path.ViewPathString.viewPath;

public class ViewMounterTest {
  @Test
  public void mounts_views_at_given_paths() {
    ViewMounter mounter = ViewMounter.withHomeView(HOME);
    mounter.mount(viewPath("/tutorial/*"), TUTORIAL);
    mounter.mount(viewPath("/tutorial/events"), TUTORIAL_ON_EVENTS);
    mounter.mount(viewPath("/tutorial/sockets"), TUTORIAL_ON_SOCKETS);
    mounter.mount(viewPath("/tutorial/topic/*"), TUTORIAL_TOPIC);
    mounter.mount(viewPath("/tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
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

  private String resolvedPathString(PathFromViewClassResolver resolver, View view) {
    return pathStringOf(resolver.viewPathFor(view.getClass()));
  }

  private View resolvedView(ViewFromPathResolver viewFromPath, String pathString) {
    return viewFromPath.resolve(viewPath(pathString)).view;
  }
}

package org.mb4j.sample.servlet.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.sample.servlet.master.MasterLayoutPage;

public class EventListPage extends MasterLayoutPage {
  public static final int SHOW_ALL = EventListPanel.Params.SHOW_ALL;
  @Inject
  EventListPanel contentPanel;

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(EventListPage.class);
      bind(EventListPanel.class);
      bind(EventListItemPanel.class);
    }
  }

  public static ControllerUrl url() {
    return url(SHOW_ALL);
  }

  public static ControllerUrl url(int maxEventCount) {
    return ControllerUrl.of(EventListPage.class,
        new EventListPanel.Params(maxEventCount, false).toUrlParams());
  }

  @Override
  protected MustacheBrick bakeContentBrick(Request request) {
    return contentPanel.bakeBrick(request);
  }
}

package org.mb4j.servlet.sample.event.list;

import com.google.inject.Inject;
import org.mb4j.brick.Brick;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.controller.url.ViewUrl;
import org.mb4j.controller.ViewRequest;

public class EventListPage extends MasterLayoutPage {
  public static final int SHOW_ALL = EventListPanel.Params.SHOW_ALL;
  @Inject
  EventListPanel contentPanel;

  public static ViewUrl url() {
    return url(SHOW_ALL);
  }

  public static ViewUrl url(int maxEventCount) {
    return ViewUrl.of(EventListPage.class,
        new EventListPanel.Params(maxEventCount, false).toViewParams());
  }

  @Override
  protected Brick bakeContentBrick(ViewRequest request) {
    return contentPanel.bakeBrick(request);
  }
}

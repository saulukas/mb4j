package org.mb4j.servlet.sample.event.list;

import com.google.inject.Inject;
import org.mb4j.brick.Brick;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.ControllerRequest;

public class EventListPage extends MasterLayoutPage {
  public static final int SHOW_ALL = EventListPanel.Params.SHOW_ALL;
  @Inject
  EventListPanel contentPanel;

  public static ControllerUrl url() {
    return url(SHOW_ALL);
  }

  public static ControllerUrl url(int maxEventCount) {
    return ControllerUrl.of(EventListPage.class,
        new EventListPanel.Params(maxEventCount, false).toViewParams());
  }

  @Override
  protected Brick bakeContentBrick(ControllerRequest request) {
    return contentPanel.bakeBrick(request);
  }
}

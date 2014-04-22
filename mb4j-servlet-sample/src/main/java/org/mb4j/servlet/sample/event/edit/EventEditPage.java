package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import org.mb4j.brick.Brick;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.controller.url.UrlPathBuilder.urlPath;
import org.mb4j.servlet.sample.master.MasterLayoutPage;

public class EventEditPage extends MasterLayoutPage {
  @Inject
  EventEditPanel contentPanel;

  public static ControllerUrl url(int eventId) {
    return ControllerUrl.of(EventEditPage.class, urlPath().with(String.valueOf(eventId)).instance());
  }

  @Override
  protected Brick bakeContentBrick(ControllerRequest request) {
    return contentPanel.bakeBrick(request, readEventIdFrom(request));
  }

  private int readEventIdFrom(ControllerRequest request) {
    return Integer.parseInt(request.readPathSegment());
  }
}

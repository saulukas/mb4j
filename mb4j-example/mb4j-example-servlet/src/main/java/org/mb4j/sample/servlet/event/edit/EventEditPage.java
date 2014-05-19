package org.mb4j.sample.servlet.event.edit;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.mb4j.brick.Brick;
import org.mb4j.controller.Request;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.controller.url.UrlPathBuilder.urlPath;
import org.mb4j.sample.servlet.master.MasterLayoutPage;

public class EventEditPage extends MasterLayoutPage {
  @Inject
  EventEditPanel contentPanel;

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(EventEditPage.class);
      bind(EventEditPanel.class);
      bind(EventEditForm.class);
    }
  }

  public static ControllerUrl url(int eventId) {
    return ControllerUrl.of(EventEditPage.class, urlPath().with(String.valueOf(eventId)));
  }

  @Override
  protected Brick bakeContentBrick(Request request) {
    int eventId = Integer.parseInt(request.readPathSegment());
    return contentPanel.bakeBrick(request, eventId);
  }
}

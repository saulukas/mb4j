package org.mb4j.example.servlet.event.edit;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.component.url.UrlPathBuilder.urlPath;
import org.mb4j.component.Request;
import org.mb4j.component.ControllerUrl;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class EventEditPage extends MasterLayoutPage {
  @Inject
  EventEditPanel contentPanel;

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(EventEditPage.class);
      bind(EventEditPanel.class);
      bind(EventEditFormHandler.class);
    }
  }

  public static ControllerUrl url(int eventId) {
    return ControllerUrl.of(EventEditPage.class, urlPath().with(String.valueOf(eventId)));
  }

  @Override
  protected MustacheBrick bakeContentBrick(Request request) {
    int eventId = Integer.parseInt(request.readPathSegment());
    return contentPanel.bakeBrick(request, eventId);
  }
}

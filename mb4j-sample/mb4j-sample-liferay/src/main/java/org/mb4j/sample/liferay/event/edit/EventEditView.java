package org.mb4j.sample.liferay.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.Request;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.controller.url.UrlPathBuilder.urlPath;
import org.mb4j.liferay.PortletView;

@Singleton
public class EventEditView extends PortletView {
  @Inject
  EventEditForm form;

  public static ControllerUrl url(int eventId) {
    return ControllerUrl.of(EventEditView.class, urlPath().with(String.valueOf(eventId)));
  }

  @Override
  public EventEditViewBrick bakeBrickFrom(Request request) {
    int eventId = Integer.parseInt(request.readPathSegment());
    EventEditViewBrick brick = new EventEditViewBrick();
    brick.form = request.resolve(form.dataFor(request, eventId));
    return brick;
  }
}

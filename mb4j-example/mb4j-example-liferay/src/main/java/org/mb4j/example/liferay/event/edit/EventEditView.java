package org.mb4j.example.liferay.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import static org.mb4j.component.url.UrlPathBuilder.urlPath;
import org.mb4j.liferay.PortletView;

@Singleton
public class EventEditView extends PortletView {
  @Inject
  EventEditForm form;

  public static ViewUrl url(int eventId) {
    return ViewUrl.of(EventEditView.class, urlPath().with(String.valueOf(eventId)));
  }

  @Override
  public EventEditViewBrick bakeBrickFrom(ViewRequest request) {
    int eventId = Integer.parseInt(request.readPathSegment());
    EventEditViewBrick brick = new EventEditViewBrick();
    brick.form = request.resolve(form.dataFor(request, eventId));
    return brick;
  }
}

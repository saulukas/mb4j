package org.mb4j.liferay.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.page.BrickBakerPage;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.controller.url.UrlPathBuilder.urlPath;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.domain.EventQuery;

@Singleton
public class EventEditPage extends BrickBakerPage {
  @Inject
  EventQuery eventQuery;
  @Inject
  EventEditForm.Filler formFiller;

  public static ControllerUrl url(int eventId) {
    return ControllerUrl.of(EventEditPage.class, urlPath().with(String.valueOf(eventId)));
  }

  @Override
  public EventEditPageBrick bakeBrickFrom(ControllerRequest request) {
    int eventId = readEventIdFrom(request);
    Event event = eventQuery.eventOrNullFor(eventId);
    EventEditPageBrick brick = new EventEditPageBrick();
    brick.actionSaveUrl = request.resolve(ControllerUrl.of(EventEditForm.SaveAction.class));
    brick.form = formFiller.filledForm(request, new EventEditForm.Filler.Params(event));
    return brick;
  }

  private int readEventIdFrom(ControllerRequest request) {
    return Integer.parseInt(request.readPathSegment());
  }
}

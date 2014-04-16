package org.mb4j.liferay.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.BrickBakerPage;
import org.mb4j.controller.ViewRequest;
import static org.mb4j.controller.path.ViewPathBuilder.viewPath;
import org.mb4j.controller.url.ViewUrl;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.domain.EventQuery;

@Singleton
public class EventEditPage extends BrickBakerPage {
  @Inject
  EventQuery eventQuery;
  @Inject
  EventEditForm.Filler formFiller;

  public static ViewUrl url(int eventId) {
    return ViewUrl.of(EventEditPage.class, viewPath().with(String.valueOf(eventId)));
  }

  @Override
  public EventEditPageBrick bakeBrickFrom(ViewRequest request) {
    int eventId = readEventIdFrom(request);
    Event event = eventQuery.eventOrNullFor(eventId);
    EventEditPageBrick brick = new EventEditPageBrick();
    brick.actionSaveUrl = request.stringOf(ViewUrl.of(EventEditForm.SaveAction.class));
    brick.form = formFiller.filledForm(request, new EventEditForm.Filler.Params(event));
    return brick;
  }

  private int readEventIdFrom(ViewRequest request) {
    return Integer.parseInt(request.pathParamsReader.readSegment());
  }
}

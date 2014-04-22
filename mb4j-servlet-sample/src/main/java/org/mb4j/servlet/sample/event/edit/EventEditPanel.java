package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventQuery;

@Singleton
public class EventEditPanel {
  @Inject
  EventQuery eventQuery;
  @Inject
  EventEditForm.Filler formFiller;

  public EventEditPanelBrick bakeBrick(ControllerRequest request, int eventId) {
    Event event = eventQuery.eventOrNullFor(eventId);
    EventEditPanelBrick brick = new EventEditPanelBrick();
    brick.actionSaveUrl = request.resolve(ControllerUrl.of(EventEditForm.SaveAction.class));
    brick.form = formFiller.filledForm(request, new EventEditForm.Filler.Params(event));
    return brick;
  }
}

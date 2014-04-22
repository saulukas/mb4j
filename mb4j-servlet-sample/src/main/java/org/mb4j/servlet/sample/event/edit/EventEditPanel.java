package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventQuery;

@Singleton
public class EventEditPanel {
  @Inject
  EventQuery eventQuery;
  @Inject
  EventEditForm.Filler formFiller;

  public EventEditPanelBrick bakeBrick(ViewRequest request, int eventId) {
    Event event = eventQuery.eventOrNullFor(eventId);
    EventEditPanelBrick brick = new EventEditPanelBrick();
    brick.actionSaveUrl = request.stringOf(ControllerUrl.of(EventEditForm.SaveAction.class));
    brick.form = formFiller.filledForm(request, new EventEditForm.Filler.Params(event));
    return brick;
  }
}

package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.page.Panel;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventQuery;

@Singleton
public class EventEditPanel extends Panel {
  @Inject
  EventQuery eventQuery;
  @Inject
  EventEditForm form;

  public EventEditPanelBrick bakeBrick(ControllerRequest request, int eventId) {
    Event event = eventQuery.eventOrNullFor(eventId);
    EventEditPanelBrick brick = new EventEditPanelBrick();
    brick.form = request.resolve(form.dataFrom(event));
    return brick;
  }
}

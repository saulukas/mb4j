package org.mb4j.sample.servlet.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.page.Panel;

@Singleton
public class EventEditPanel extends Panel {
  @Inject
  EventEditForm form;

  public EventEditPanelBrick bakeBrick(ControllerRequest request, final int eventId) {
    EventEditPanelBrick brick = new EventEditPanelBrick();
    brick.form = request.resolve(form.data(request, eventId));
    return brick;
  }
}

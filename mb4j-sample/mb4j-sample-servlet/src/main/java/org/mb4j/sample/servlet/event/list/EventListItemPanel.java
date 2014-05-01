package org.mb4j.sample.servlet.event.list;

import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.sample.domain.data.Event;
import org.mb4j.sample.servlet.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel {
  public EventListItemPanelBrick bakeBrick(ControllerRequest request, Event event) {
    EventListItemPanelBrick brick = new EventListItemPanelBrick();
    brick.event = event;
    brick.eventImageUrl = request.resolveUrl(event.imageUrl);
    brick.eventEditUrl = request.resolve(EventEditPage.url(event.id));
    return brick;
  }
}

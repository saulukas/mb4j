package org.mb4j.servlet.sample.event.list;

import com.google.inject.Singleton;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.event.edit.EventEditPage;
import org.mb4j.controller.ControllerRequest;

@Singleton
public class EventListItemPanel {
  public EventListItemPanelBrick bakeBrick(ControllerRequest request, Event event) {
    EventListItemPanelBrick brick = new EventListItemPanelBrick();
    brick.event = event;
    brick.eventImageUrl = request.staticUrl(event.imageUrl);
    brick.eventEditUrl = request.stringOf(EventEditPage.url(event.id));
    return brick;
  }
}

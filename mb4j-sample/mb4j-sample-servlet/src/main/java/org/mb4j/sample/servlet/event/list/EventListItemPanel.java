package org.mb4j.sample.servlet.event.list;

import com.google.inject.Singleton;
import org.mb4j.controller.Request;
import org.mb4j.controller.page.Panel;
import org.mb4j.sample.domain.data.Event;
import org.mb4j.sample.servlet.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel extends Panel {
  public EventListItemPanelBrick bakeBrick(Request request, Event event) {
    EventListItemPanelBrick brick = new EventListItemPanelBrick();
    brick.event = event;
    brick.eventImageUrl = request.resolveUrl(event.imageUrl);
    brick.eventEditUrl = request.resolve(EventEditPage.url(event.id));
    return brick;
  }
}

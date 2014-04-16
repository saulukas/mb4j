package org.mb4j.liferay.sample.event.list;

import com.google.inject.Singleton;
import org.mb4j.controller.ViewRequest;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel {
  public EventListItemPanelBrick bakeBrick(ViewRequest request, Event event) {
    EventListItemPanelBrick brick = new EventListItemPanelBrick();
    brick.event = event;
    brick.eventImageUrl = request.staticUrl(event.imageUrl);
    brick.eventEditUrl = request.stringOf(EventEditPage.url(event.id));
    return brick;
  }
}

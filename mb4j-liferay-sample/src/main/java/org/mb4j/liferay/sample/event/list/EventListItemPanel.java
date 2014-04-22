package org.mb4j.liferay.sample.event.list;

import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel {
  public EventListItemPanelBrick bakeBrick(ControllerRequest request, Event event) {
    EventListItemPanelBrick brick = new EventListItemPanelBrick();
    brick.event = event;
    brick.eventImageUrl = request.resolveStaticUrl(event.imageUrl);
    brick.eventEditUrl = request.resolve(EventEditPage.url(event.id));
    return brick;
  }
}

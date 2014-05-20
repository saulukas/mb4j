package org.mb4j.example.liferay.event.list;

import com.google.inject.Singleton;
import org.mb4j.component.ViewRequest;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.liferay.event.edit.EventEditView;

@Singleton
public class EventListItemPanel {
  public EventListItemPanelBrick bakeBrick(ViewRequest request, Event event) {
    EventListItemPanelBrick brick = new EventListItemPanelBrick();
    brick.event = event;
    brick.eventImageUrl = request.resolveUrl(event.imageUrl);
    brick.eventEditUrl = request.resolve(EventEditView.url(event.id));
    return brick;
  }
}

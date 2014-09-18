package org.mb4j.example.servlet.event.list;

import com.google.inject.Singleton;
import org.mb4j.component.ReflectiveComponent;
import org.mb4j.component.Request;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.servlet.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel extends ReflectiveComponent {

    public EventListItemBrick bakeBrick(Request request, Event event) {
        EventListItemBrick brick = new EventListItemBrick();
        brick.event = event;
        brick.eventImageUrl = request.assetUrl(event.imageUrl);
        brick.eventEditUrl = request.resolve(EventEditPage.url(event.id));
        return brick;
    }
}

package org.mb4j.example.servlet.event.list;

import com.google.inject.Singleton;
import org.mb4j.component.ReflectiveComponent;
import org.mb4j.component.Request;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.servlet.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel extends ReflectiveComponent {


    public EventListItemPanelBrick bakeBrick(Request request, Event event) {
        EventListItemPanelBrick brick = new EventListItemPanelBrick();
        brick.event = event;
        brick.eventImageUrl = request.resolveAssetUrl(event.imageUrl);
        brick.eventEditUrl = request.resolve(EventEditPage.url(event.id));
        return brick;
    }
}

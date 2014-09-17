package org.mb4j.example.liferay.event.list;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewUrl4Response;
import org.mb4j.component.Request;
import org.mb4j.component.asset.AssetUrl4Response;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.liferay.event.edit.EventEditView;

@Singleton
public class EventListItemPanel {

    static class Brick extends MustacheBrick {

        Event event;
        AssetUrl4Response eventImageUrl;
        ViewUrl4Response eventEditUrl;
    }

    Brick bakeBrick(Request request, Event event) {
        Brick brick = new Brick();
        brick.event = event;
        brick.eventImageUrl = request.assetUrl(event.imageUrl);
        brick.eventEditUrl = request.resolve(EventEditView.url(event.id));
        return brick;
    }
}

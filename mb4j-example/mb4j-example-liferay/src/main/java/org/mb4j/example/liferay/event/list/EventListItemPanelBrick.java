package org.mb4j.example.liferay.event.list;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.AssetUrl;
import org.mb4j.component.ViewUrl;
import org.mb4j.example.domain.data.Event;

class EventListItemPanelBrick extends MustacheBrick {

    Event event;
    AssetUrl eventImageUrl;
    ViewUrl eventEditUrl;

}
